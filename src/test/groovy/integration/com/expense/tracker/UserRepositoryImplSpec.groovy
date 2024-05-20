package integration.com.expense.tracker

import com.expense.tracker.entity.User
import com.expense.tracker.repository.UserRepositoryImpl
import util.TestContainersSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DuplicateKeyException
import spock.lang.Subject

@SpringBootTest
class UserRepositoryImplSpec extends TestContainersSpec {

    @Subject
    UserRepositoryImpl repository

    def username = 'testUser'
    def password = 'myPassword'
    def firstName = 'myName'
    def lastName = 'myLastName'
    def email = 'my.email@hotmail.com'

    def setup() {
        repository = new UserRepositoryImpl(dataSource)
    }

    def 'Successfully insert a new user in the database'() {
        given: 'a user'
        def user = new User(username: username, password: password, firstName: firstName,
                lastName: lastName, email: email)

        when: 'calling the insert method'
        def result = repository.insert(user)

        then: 'the user is saved'
        def dbUser = sql.firstRow("SELECT * FROM EXPENSE_TRACKER.USERS WHERE USERNAME = ?", [user.username])
        dbUser.get('ID') == result
        dbUser.get('USERNAME') == user.username
        dbUser.get('PASSWORD') == user.password
        dbUser.get('FIRSTNAME') == user.firstName
        dbUser.get('LASTNAME') == user.lastName
        dbUser.get('EMAIL') == user.email
    }

    def 'Insert users with same username or email in the database fails'() {
        given: 'a first user'
        def firstUser = new User(username: firstUsername, password: password, firstName: firstName,
                lastName: lastName, email: firstEmail)

        and: 'a second user'
        def secondUser = new User(username: secondUsername, password: password, firstName: firstName,
                lastName: lastName, email: secondEmail)

        when: 'inserting the users'
        repository.insert(firstUser)
        repository.insert(secondUser)

        then: 'an exception is thrown with appropriate messages'
        def ex = thrown(DuplicateKeyException)
        ex.message.contains(errorMessage)

        where: 'duplicate data is being provided for username and email'
        firstUsername | secondUsername | firstEmail                   | secondEmail                   || errorMessage
        'testUser'    | 'testUser'     | 'my.first.email@hotmail.com' | 'my.second.email@hotmail.com' || duplicateUsernameErrorMessage()
        'testUser1'   | 'testUser2'    | 'my.email@hotmail.com'       | 'my.email@hotmail.com'        || duplicateEmailErrorMessage()
    }

    def duplicateUsernameErrorMessage() {
        "Duplicate entry 'testUser' for key 'USERS.USERNAME'"
    }

    def duplicateEmailErrorMessage() {
        "Duplicate entry 'my.email@hotmail.com' for key 'USERS.EMAIL'"
    }
}
