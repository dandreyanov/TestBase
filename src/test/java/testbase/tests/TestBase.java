package testbase.tests;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import testbase.helpers.DriverHelper;

import static testbase.helpers.AttachmentsHelper.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

@ExtendWith({AllureJunit5.class})
public class TestBase {
    @BeforeAll
    static void setUp() {
        DriverHelper.configureDriver();
    }

    @AfterEach
    public void addAttachments(){
        String sessionId = DriverHelper.getSessionId();

        attachScreenshot("Last screenshot");
        attachPageSource();
//        attachNetwork(); // todo
        attachAsText("Browser console logs", DriverHelper.getConsoleLogs());

        closeWebDriver();

        if (DriverHelper.isVideoOn()) attachVideo(sessionId);
    }
}
