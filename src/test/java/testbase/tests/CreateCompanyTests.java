package testbase.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testbase.helpers.JsonHelper;

import java.util.Random;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static testbase.api.LogFilter.filters;

public class CreateCompanyTests extends TestBase {

    Faker faker = new Faker();

    String company_name = faker.company().name(),
            firstCompanyUsers = faker.internet().emailAddress(),
            secondCompanyUsers = faker.internet().emailAddress(),
            emailOwner = faker.internet().emailAddress(),
            name = faker.name().fullName(),
            password = faker.internet().password();

    //выбираем рандомный тип компании
    final String[] companyTypeArray = {"ИП", "ООО", "ОАО"};
    Random random = new Random();
    int index = random.nextInt(companyTypeArray.length);
    String company_type = companyTypeArray[index];
    String requestFirst[][] = {{"email", firstCompanyUsers}, {"name", name}, {"password", password}};
    String requestSecond[][] = {{"email", secondCompanyUsers}, {"name", name}, {"password", password}};
    String requestOwner[][] = {{"email", emailOwner}, {"name", name}, {"password", password}};

    @BeforeEach
    public void beforeFunction() {
        open("");
        step("Create first user", () -> {
            given().urlEncodingEnabled(true)
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json;charset=UTF-8")
                    .body(JsonHelper.createJSONdoRegister(requestFirst))
                    .when()
                    .post("/tasks/rest/doregister")
                    .then()
                    .statusCode(200);
        });
        step("Create second user", () -> {
            given().urlEncodingEnabled(true)
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json;charset=UTF-8")
                    .body(JsonHelper.createJSONdoRegister(requestSecond))
                    .when()
                    .post("/tasks/rest/doregister")
                    .then()
                    .statusCode(200);
        });
        step("Create owner", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json;charset=UTF-8")
                    .body(JsonHelper.createJSONdoRegister(requestOwner))
                    .when()
                    .post("/tasks/rest/doregister")
                    .then()
                    .statusCode(200);
        });
    }


    @Test
    @Description("Check createCompany method")
    @DisplayName("Test with all fields - success")
    void createCompanySuccess() {
        step("Send post with all fields", () -> {
            given().urlEncodingEnabled(true)
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json;charset=UTF-8")
                    .body(JsonHelper.createJSONcreateCompany(company_name,
                            company_type,
                            firstCompanyUsers,
                            secondCompanyUsers,
                            "mariano.wiza@gmail.com"))
                    .when()
                    .post("/tasks/rest/createcompany")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("success"))
                    .body("company.name", is(company_name))
                    .body("company.type", is(company_type));
        });
    }

    @Test
    @Description("Check createCompany method")
    @DisplayName("Test without company name")
    void createCompanyWithoutCompanyName() {
        step("Send post without name", () -> {
            given().urlEncodingEnabled(true)
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json;charset=UTF-8")
                    .body(JsonHelper.createJSONcreateCompany("",
                            company_type,
                            firstCompanyUsers,
                            secondCompanyUsers,
                            "mariano.wiza@gmail.com"))
                    .when()
                    .post("/tasks/rest/createcompany")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is(" company_name некорректный"));
        });
    }

    @Test
    @Description("Check createCompany method")
    @DisplayName("Test without company type")
    void createCompanyWithoutCompanyType() {
        step("Send post without company type", () -> {
            given().urlEncodingEnabled(true)
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json;charset=UTF-8")
                    .body(JsonHelper.createJSONcreateCompany(company_name,
                            "",
                            firstCompanyUsers,
                            secondCompanyUsers,
                            "mariano.wiza@gmail.com"))
                    .when()
                    .post("/tasks/rest/createcompany")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is(" company_type  некорректный"));
        });
    }

    @Test
    @Description("Check createCompany method")
    @DisplayName("Test without company users")
    void createCompanyWithoutCompanyUsers() {
        step("Send post without company users", () -> {
            given().urlEncodingEnabled(true)
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json;charset=UTF-8")
                    .body(JsonHelper.createJSONcreateCompany(company_name,
                            company_type,
                            "",
                            "",
                            "mariano.wiza@gmail.com"))
                    .when()
                    .post("/tasks/rest/createcompany")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is(" company_users  не указаны сотрудники"));
        });
    }

    @Test
    @Description("Check createCompany method")
    @DisplayName("Test without company owner")
    void createCompanyWithoutCompanyOwner() {
        step("Send post without company owner", () -> {
            given().urlEncodingEnabled(true)
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json;charset=UTF-8")
                    .body(JsonHelper.createJSONcreateCompany(company_name,
                            company_type,
                            firstCompanyUsers,
                            secondCompanyUsers,
                            ""))
                    .when()
                    .post("/tasks/rest/createcompany")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is("Пользователь не найден c email_owner "));
        });
    }


}
