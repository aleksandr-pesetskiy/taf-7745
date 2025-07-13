package by.w7745.api;

import by.w7745.api.expectedMessages.ExpectedMessages;
import by.w7745.api.loginForm.LoginPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    @Test
    public void testLogin() {
        Faker faker = new Faker();
        LoginPage loginPage = new LoginPage("375",faker.numerify("(##) ###-##-##"), faker.internet().password());

        assertAll(
                "Grouped Assertions of User",
                () -> assertEquals(200, loginPage.getStatusCode(), "StatusCode should be 200"),
                () -> assertEquals(false, loginPage.getErrorText(), "Status in response should be false"),
                () -> assertEquals(ExpectedMessages.INVALID_PHONE_OR_PASSWORD, loginPage.getContextErrorEmail(), "Text match")
        );
    }
}
