package pom;

import data.GeneralParameters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private final WebDriver driver;
    private final String urlRegister = GeneralParameters.BASE_URI + "/register";
    //Поле ввода имени
    private final By nameField = By.xpath(".//label[text()='Имя']/parent::*/input");
    //Поле ввода email
    private final By emailField = By.xpath(".//label[text()='Email']/parent::*/input");
    //Поле ввода пароля
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    //Текст "Некорректный пароль"
    private final By incorrectPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    //Кнопка "Войти"
    private final By enterButtonRegister = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы регистрации")
    public void openRegisterPage() {
        driver.get(urlRegister);
    }

    @Step("Ожидание загрузки страницы")
    public void waitRegisterButton() {
        new WebDriverWait(driver, 6).until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }

    @Step("Заполнение поля «Имя»")
    public void inputNameField(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
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

    @Step("Получение текста \"Некорректный пароль\"")
    public String getIncorrectPasswordText() {
        return driver.findElement(incorrectPasswordText).getText();
    }

    @Step("Клик по кнопке \"Войти\"")
    public void clickEnterButton() {
        driver.findElement(enterButtonRegister).click();
    }
}
