package com.expense.tracker.repository

import com.expense.tracker.entity.User
import com.expense.tracker.util.TestContainersSpec
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Subject

@SpringBootTest
class UserRepositoryImplSpec extends TestContainersSpec {

    @Subject
    UserRepositoryImpl repository

    def setup() {
        repository = new UserRepositoryImpl(dataSource)
    }

    def "Successfully insert a new user in the database"() {
        given: "a user"
        def user = new User(username: 'testUser', password: 'myPassword', firstName: 'myName',
                lastName: 'myLastName', email: 'my.email@hotmail.com')

        when: "calling the insert method"
        def result = repository.insert(user)

        then: "the user is saved"
        def dbUser = sql.firstRow("SELECT * FROM EXPENSE_TRACKER.USERS WHERE USERNAME = ?", [user.username])
        dbUser != null
        dbUser.get('ID') == result
        dbUser.get('USERNAME') == user.username
        dbUser.get('PASSWORD') == user.password
        dbUser.get('FIRSTNAME') == user.firstName
        dbUser.get('LASTNAME') == user.lastName
        dbUser.get('EMAIL') == user.email
    }
}
