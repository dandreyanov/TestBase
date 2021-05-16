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

public class CreateUserTests extends TestBase {

    Faker faker = new Faker();

    String email = faker.internet().emailAddress(),
            name = faker.name().fullName(),
            hobby = faker.job().position(),
            adres = faker.address().fullAddress(),
            name1 = faker.name().firstName(),
            surname1 = faker.name().lastName(),
            fathername1 = faker.name().nameWithMiddle(),
            cat = faker.cat().name(),
            dog = faker.dog().name(),
            parrot = faker.dog().name(),
            cavy = faker.cat().name(),
            hamster = faker.name().name(),
            squirrel = faker.name().username(),
            phone = faker.phoneNumber().phoneNumber(),
            birthday = faker.date().birthday().toString(),
            date_start = faker.date().birthday().toString();

    Integer task1 = faker.number().numberBetween(2, 10),
            task2 = faker.number().numberBetween(2, 10),
            companies1 = faker.number().numberBetween(11, 99),
            companies2 = faker.number().numberBetween(11, 99),
            tasks[] = {task1, task2},
            companies[] = {companies1, companies2};

    Long innNumber = faker.number().randomNumber(12, true);
    String inn = innNumber.toString();

    //Выбираем пол рандомно
    final String[] genderArray = {"m", "f"};
    Random random = new Random();
    int index = random.nextInt(genderArray.length);
    String gender = genderArray[index];

    @BeforeEach
    public void beforeFunction() {
        open("");
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test with all fields - success")
    void createUserSuccess() {
        step("Send post with all fields", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(JsonHelper.createJSONcreateUser(email,
                            name,
                            tasks,
                            companies,
                            hobby,
                            adres,
                            name1,
                            surname1,
                            fathername1,
                            cat,
                            dog,
                            parrot,
                            cavy,
                            hamster,
                            squirrel,
                            phone,
                            inn,
                            gender,
                            birthday,
                            date_start))
                    .when()
                    .post("/tasks/rest/createuser")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("name", is(name))
                    .body("email", is(email))
                    .body("surname1", is(surname1))
                    .body("fathername1", is(fathername1))
                    .body("cat", is(cat))
                    .body("dog", is(dog))
                    .body("parrot", is(parrot))
                    .body("cavy", is(cavy))
                    .body("hamster", is(hamster))
                    .body("squirrel", is(squirrel))
                    .body("phone", is(phone))
                    .body("adres", is(adres))
                    .body("gender", is(gender))
                    .body("hobby", is(hobby));
        });
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test without email")
    void createUserWithoutEmail() {
        step("Send post without email", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(JsonHelper.createJSONcreateUser("",
                            name,
                            tasks,
                            companies,
                            hobby,
                            adres,
                            name1,
                            surname1,
                            fathername1,
                            cat,
                            dog,
                            parrot,
                            cavy,
                            hamster,
                            squirrel,
                            phone,
                            inn,
                            gender,
                            birthday,
                            date_start))
                    .when()
                    .post("/tasks/rest/createuser")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is("email неправильный!"));
        });
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test without name")
    void createUserWithoutName() {
        step("Send post without name", () -> {
            given()
                    .filter(filters().withCustomTemplates())
                    .body(JsonHelper.createJSONcreateUser(email,
                            "",
                            tasks,
                            companies,
                            hobby,
                            adres,
                            name1,
                            surname1,
                            fathername1,
                            cat,
                            dog,
                            parrot,
                            cavy,
                            hamster,
                            squirrel,
                            phone,
                            inn,
                            gender,
                            birthday,
                            date_start))
                    .when()
                    .post("/tasks/rest/createuser")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is(" имя  уже есть в БД"));
        });
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test without tasks")
    void createUserWithoutTasks() {
        step("Send post without tasks", () -> {
            Integer emptyTasks[] = {};
            given()
                    .filter(filters().withCustomTemplates())
                    .body(JsonHelper.createJSONcreateUser(email,
                            name,
                            emptyTasks,
                            companies,
                            hobby,
                            adres,
                            name1,
                            surname1,
                            fathername1,
                            cat,
                            dog,
                            parrot,
                            cavy,
                            hamster,
                            squirrel,
                            phone,
                            inn,
                            gender,
                            birthday,
                            date_start))
                    .when()
                    .post("/tasks/rest/createuser")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is("параметр tasks должен быть в виде массива"));
        });
    }

    @Test
    @Description("Check doRegister method")
    @DisplayName("Test without companies")
    void createUserWithoutCompanies() {
        step("Send post without companies", () -> {
            Integer emptyCompanies[] = {};
            given()
                    .filter(filters().withCustomTemplates())
                    .body(JsonHelper.createJSONcreateUser(email,
                            name,
                            tasks,
                            emptyCompanies,
                            hobby,
                            adres,
                            name1,
                            surname1,
                            fathername1,
                            cat,
                            dog,
                            parrot,
                            cavy,
                            hamster,
                            squirrel,
                            phone,
                            inn,
                            gender,
                            birthday,
                            date_start))
                    .when()
                    .post("/tasks/rest/createuser")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("type", is("error"))
                    .body("message", is("параметр companies должен быть в виде массива"));
        });
    }


}
