package by.w7745.api.loginForm;

import by.w7745.api.expectedMessages.ExpectedMessages;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginPage {
    private final Response response;

    public LoginPage(String prefix, String number, String password) {
        response = getResponse(prefix, number, password);
    }

    private Response getResponse(String prefix, String number, String password) {
        return given()
                .header("x-csrf-token", ExpectedMessages.HEADER_PARAM_X_CSRF_TOKEN)
                .header("Cookie", ExpectedMessages.HEADER_PARAM_COOKIE)
                .formParam("prefix", prefix)
                .formParam("login", number)
                .formParam("password", password)
                .formParam("remember", "on")
                .formParam("target", "")
                .formParam("looking_page", "Nzc0NS5ieS8=")
                .when().post("https://7745.by/login");
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public Boolean getErrorText() {
        return response.path("status");
    }

    public String getContextErrorEmail() {
        return response.path("errors.login[0]");
    }
}
