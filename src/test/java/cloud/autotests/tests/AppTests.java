package cloud.autotests.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import static cloud.autotests.helpers.DriverHelper.getConsoleLogs;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class AppTests extends TestBase {
    @Test
    @Description("Soon to be implemented by QA.GURU engineers")
    @DisplayName("TestBase Autotests")
    void generatedTest() {
        step("check doRegister - all field", () -> {
            // todo just add selenium action
        });

        step("check doRegister - without email", () -> {
            // todo just add selenium action
        });

        step("check doRegister - without name", () -> {
            // todo just add selenium action
        });

        step("check doRegister - without password", () -> {
            // todo just add selenium action
        });

        step("check doRegister - all fields empty", () -> {
            // todo just add selenium action
        });
    }
}