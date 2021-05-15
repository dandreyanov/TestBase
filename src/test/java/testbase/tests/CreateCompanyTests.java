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
import static org.hamcrest.Matchers.notNullValue;
import static testbase.api.LogFilter.filters;

public class CreateCompanyTests extends TestBase {
    /*
    Входные параметры
    имя             тип     обязательность
    company_name	строка	да	Название компании
    company_type	строка	да	Тип компании. Возможные значения: ИП, ООО, ОАО
    company_users	массив	да	Сотрудники компании (указывается email сотрудника)
    email_owner	    строка	да	Автор

    Результирующие данные
    type	    Успешно ли прошел вызов метода? Принимает значения success или error
    id_company	Идентификатор компании
    company	    Информация по сохраненной компании
     */


    Faker faker = new Faker();

    String company_name = faker.company().name(),
            firstCompanyUsers = faker.internet().emailAddress(),
            secondCompanyUsers = faker.internet().emailAddress(),
            emailOwner = faker.internet().emailAddress(),
            name = faker.name().fullName(),
            password = faker.internet().password();

    @BeforeEach
    public void beforeFunction() {
        open("");
    }


    @Test
    @Description("Check createCompany method")
    @DisplayName("Test with all fields - success")
    void createCompanySuccess() {

        //выбираем рандомный тип компании
        final String[] companyTypeArray = {"ИП", "ООО", "ОАО"};
        Random random = new Random();
        int index = random.nextInt(companyTypeArray.length);
        String company_type = companyTypeArray[index];

        step("Create first user", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .contentType("application/json")
                    .body(JsonHelper.createJSONdoRegister(firstCompanyUsers, name, password))
                    .when()
                    .post("/tasks/rest/doregister")
                    .then()
                    .statusCode(200);
        });
        step("Create second user", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(JsonHelper.createJSONdoRegister(secondCompanyUsers, name, password))
                    .when()
                    .post("/tasks/rest/doregister")
                    .then()
                    .statusCode(200);
        });
        step("Create owner", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(JsonHelper.createJSONdoRegister(emailOwner, name, password))
                    .when()
                    .post("/tasks/rest/doregister")
                    .then()
                    .statusCode(200);
        });
        step("Send post with all fields", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(JsonHelper.createJSONcreateCompany(company_name,
                            company_type,
                            firstCompanyUsers,
                            secondCompanyUsers,
                            "mariano.wiza@gmail.com"))
                    .when()
                    .post("/tasks/rest/createcompany")
                    .then()
                    .statusCode(200)
                    .log().body();
        });
    }

}
