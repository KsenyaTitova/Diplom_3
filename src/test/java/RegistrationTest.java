import data.UserGeneration;
import driver.Property;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.HomePageStellarBurgers;
import pom.LoginPage;
import pom.RegisterPage;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {

    private static WebDriver driver;
    private static final UserGeneration newUser = new UserGeneration();

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", Property.PATH_DRIVER_CHROME); //Для браузера Chrome
        //System.setProperty("webdriver.chrome.driver", Property.PATH_DRIVER_YANDEX); //Для браузера Yandex
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.openHomePage();
        objHomePage.waitPersonalAccountButton();
        objHomePage.clickPersonalAccountButton();
    }

    @Test
    @DisplayName("Успешная регистрация в приложении")
    public void registrationReturnSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.inputNameField(newUser.getName());
        registerPage.inputEmailField(newUser.getEmail());
        registerPage.inputPasswordField(newUser.getPassword());
        registerPage.clickRegisterButton();
        //Проверяем, что регистрация успешна=перекинуло на страницу с кнопкой "Войти"
        Assert.assertTrue("Ожидаемое значение не совпало с фактическим", loginPage.checkEnterButton());
    }

    @Test
    @DisplayName("Неуспешная регистрация в приложении: некорректный пароль")
    public void registrationIncorrectPasswordReturnError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.inputNameField(newUser.getName());
        registerPage.inputEmailField(newUser.getEmail());
        registerPage.inputPasswordField(newUser.getInvalidPassword());
        registerPage.clickRegisterButton();
        //Проверяем, что получили ошибку "Некорректный пароль"
        Assert.assertEquals("Ожидаемое значение не совпало с фактическим", "Некорректный пароль", registerPage.getIncorrectPasswordText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}