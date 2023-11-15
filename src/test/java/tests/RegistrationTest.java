package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.CalendarComponent;
import pages.components.CheckResultComponent;

import static io.qameta.allure.Allure.step;

@Tag("demoqa_m")
public class RegistrationTest extends TestBase {
    TestData testData = new TestData();
    RegistrationPage registrationPage = new RegistrationPage();

    CalendarComponent calendar = new CalendarComponent();
    CheckResultComponent checkResult = new CheckResultComponent();

    @Test
    @Feature("Форма регистрации")
    @Story("Добавление информации о студенте")
    @Owner("a.moskotina")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Test case", url = "https://allure.autotests.cloud/")
    @DisplayName("Successful fill form registration")
    void successfulFillFormTest() {
        step("Открыть форму", () -> {
            registrationPage.openPage()
                    .deleteBanners();
        });
        step("Заполнить форму", () -> {
            registrationPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setUserEmail(testData.email)
                    .setGender(testData.gender)
                    .setUserNumber(testData.mobile)
                    .setDateOfBirth(testData.day, testData.month, testData.year)
                    .setSubjects(testData.subject)
                    .setHobbies(testData.hobbies)
                    .uploadPicture(testData.picture)
                    .setCurrentAddress(testData.address)
                    .setStateSelect(testData.stateRandom)
                    .setCitySelect(testData.cityRandom)
                    .submit();
        });
        step("Проверить результат", () -> {
            registrationPage.checkTitle()
                    .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Student Email", testData.email)
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.mobile)
                    .checkResult("Date of Birth", checkResult.addZeroWithDateWithOneChar(testData.daystr) + " " + testData.month + "," + testData.year)
                    .checkResult("Subjects", testData.subject)
                    .checkResult("Hobbies", testData.hobbies)
                    .checkResult("Picture", testData.picture)
                    .checkResult("Address", testData.address)
                    .checkResult("State and City", testData.stateRandom + " " + testData.cityRandom);
        });
    }

    @Test
    @Feature("Форма регистрации")
    @Story("Добавление информации о студенте")
    @Owner("a.moskotina")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Test case", url = "https://allure.autotests.cloud/")
    @DisplayName("Submitting an empty form - Negative")
    void emptyFillFormTest() {

        step("Открыть форму", () -> {
            registrationPage.openPage()
                    .deleteBanners();
        });
        step("Отправить пустую форму", () -> {
            registrationPage.openPage()
                    .deleteBanners()
                    .submit();
        });
        step("Проверить результат", () -> {
            registrationPage.checkNotTitle();
        });
    }

    @Test
    @Feature("Форма регистрации")
    @Story("Добавление информации о студенте")
    @Owner("a.moskotina")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Test case", url = "https://allure.autotests.cloud/")
    @DisplayName("Fill form with invalid email - Negative")
    void invalidFillFormTest() {
        step("Открыть форму", () -> {
            registrationPage.openPage()
                    .deleteBanners();
        });
        step("Заполнить форму", () -> {
            registrationPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setUserEmail(testData.emailInvalid)
                    .setGender(testData.gender)
                    .setUserNumber(testData.mobile)
                    .setDateOfBirth(testData.day, testData.month, testData.year)
                    .setSubjects(testData.subject)
                    .setHobbies(testData.hobbies)
                    .uploadPicture(testData.picture)
                    .setCurrentAddress(testData.address)
                    .setStateSelect(testData.stateRandom)
                    .setCitySelect(testData.cityRandom)
                    .submit();
        });
        step("Проверить результат c невалидным email", () -> {
            registrationPage.checkInvalidUserEmail(testData.emailInvalid)
                    .checkNotTitle();
        });
    }
}
