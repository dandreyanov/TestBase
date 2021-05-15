package cloud.autotests.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cloud.autotests.api.LogFilter.filters;
import static cloud.autotests.helpers.JsonHelper.createJSON;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class DoRegisterTests extends TestBase {

    Faker faker = new Faker();

    String email = faker.internet().emailAddress(),
    name = faker.name().fullName(),
    password = faker.internet().password();

    @BeforeEach
    public void beforeFunction() {
        open("");
    }


    @Test
    @Description("Check doRegister method")
    @DisplayName("Test with all fields - success")
    void doRegisterSuccess() {
        step("Send post with all fields", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(createJSON(email, name, password))
            .when()
                    .post("/tasks/rest/doregister")
            .then()
                    .statusCode(200)
                    .log().body()
                    .body("name", is(name))
                    .body("email", is(email))
                    .body("avatar", is("http://users.bugred.ru//tmp/default_avatar.jpg"))
                    .body("password", is(notNullValue()));
        });
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test without email")
    void doRegisterWithoutEmail() {
        step("Send post without email", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(createJSON("", name, password))
            .when()
                    .post("/tasks/rest/doregister")
            .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is(" Некоректный  email "));
        });
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test without name")
    void doRegisterWithoutName() {
        step("Send post without name", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(createJSON(email, "", password))
            .when()
                    .post("/tasks/rest/doregister")
            .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is("поле фио является обязательным"));
        });
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test without password")
    void doRegisterWithoutPassword() {
        step("Send post without password", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(createJSON(email, name, ""))
            .when()
                    .post("/tasks/rest/doregister")
            .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is("поле пароль является обязательным"));
        });
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test empty json")
    void doRegisterEmptyJson() {
        step("Send post without json", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body("")
            .when()
                    .post("/tasks/rest/doregister")
            .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is("Параметр email является обязательным!"));
        });
    }

}