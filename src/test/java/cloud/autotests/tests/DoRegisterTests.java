package cloud.autotests.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject;

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
    @DisplayName("TestBase Autotests")
    void doRegister() {
        step("send post", () -> {
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

//        step(" ", () -> {
//            // todo just add selenium action
//        });

    }
}