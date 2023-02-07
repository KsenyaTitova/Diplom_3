package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private final WebDriver driver;
    //Локатор кнопки "Зарегистрироваться"
    private final By registerButton = By.xpath(".//a[text() = 'Зарегистрироваться']");
    //Локатор кнопки "Войти"
    private final By enterButtonLogin = By.xpath(".//button[text() = 'Войти']");
    //Поле ввода email
    private final By emailField = By.xpath(".//label[text()='Email']/parent::*/input");
    //Поле ввода пароля
    private final By passwordField = By.xpath(".//label[text()='Пароль']/parent::*/input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание появления кнопки \"Войти\"")
    public boolean checkEnterButton() {
        WebElement element = driver.findElement(enterButtonLogin);
        return element.isEnabled();
    }

    @Step("Клик по кнопке \"Войти\"")
    public void clickEnterButton() {
        driver.findElement(enterButtonLogin).click();
    }

    @Step("Заполнение поля «email»")
    public void inputEmailField(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение поля «Пароль»")
    public void inputPasswordField(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке \"Зарегистрироваться\"")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
}
