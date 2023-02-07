package pom;

import data.GeneralParameters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {

    private final WebDriver driver;
    private final String urlForgotPassword = GeneralParameters.BASE_URI + "/forgot-password";
    //Локатор кнопки "Войти"
    private final By enterButtonForgotPage = By.xpath(".//a[text() = 'Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы восстановления пароля")
    public void openForgotPassword() {
        driver.get(urlForgotPassword);
    }

    @Step("Ожидание загрузки страницы")
    public void waitEnterButton() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(enterButtonForgotPage));
    }

    @Step("Клик по кнопке \"Войти\"")
    public void clickEnterButton() {
        driver.findElement(enterButtonForgotPage).click();
    }
}
