import data.UserGeneration;
import driver.Property;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.AccountProfilePage;
import pom.HomePageStellarBurgers;
import pom.LoginPage;
import pom.RegisterPage;
import java.util.concurrent.TimeUnit;

public class AccountProfileTest {

    private static WebDriver driver;
    private static final UserGeneration newUser = new UserGeneration();
    private String name;
    private String email;
    private String password;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", Property.PATH_DRIVER_CHROME); //Для браузера Chrome
        //System.setProperty("webdriver.chrome.driver", Property.PATH_DRIVER_YANDEX); //Для браузера Yandex
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.waitRegisterButton();
        name = newUser.getName();
        email = newUser.getEmail();
        password = newUser.getPassword();
        registerPage.inputNameField(name);
        registerPage.inputEmailField(email);
        registerPage.inputPasswordField(password);
        registerPage.clickRegisterButton();
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void checkTheTransitionToPersonalAccountButton() {
        HomePageStellarBurgers homePage = new HomePageStellarBurgers(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        homePage.openHomePage();
        homePage.waitPersonalAccountButton();
        homePage.clickLogInToYourAccountButton();
        loginPage.checkEnterButton();
        loginPage.inputEmailField(email);
        loginPage.inputPasswordField(password);
        loginPage.clickEnterButton();
        homePage.clickPersonalAccountButton();
        Assert.assertTrue("Ожидаемое значение не совпало с фактическим", accountProfilePage.checkProfileButton());
    }

    @Test
    @DisplayName("Переход из личного кабинета кликом на конструктор")
    public void checkSwitchingFromPersonalAccountToConstructor() {
        HomePageStellarBurgers homePage = new HomePageStellarBurgers(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        homePage.openHomePage();
        homePage.waitPersonalAccountButton();
        homePage.clickLogInToYourAccountButton();
        loginPage.checkEnterButton();
        loginPage.inputEmailField(email);
        loginPage.inputPasswordField(password);
        loginPage.clickEnterButton();
        homePage.clickPersonalAccountButton();
        accountProfilePage.clickConstructorButton();
        Assert.assertEquals("Ожидаемое значение не совпало с фактическим", "Соберите бургер", homePage.getAssembleTheBurgerText());
    }


    @Test
    @DisplayName("Переход из личного кабинета кликом на логотип")
    public void checkSwitchingFromPersonalAccountToStellarBurgersLogo() {
        HomePageStellarBurgers homePage = new HomePageStellarBurgers(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        homePage.openHomePage();
        homePage.waitPersonalAccountButton();
        homePage.waitPersonalAccountButton();
        homePage.clickLogInToYourAccountButton();
        loginPage.checkEnterButton();
        loginPage.inputEmailField(email);
        loginPage.inputPasswordField(password);
        loginPage.clickEnterButton();
        homePage.clickPersonalAccountButton();
        accountProfilePage.clickLogoButton();
        Assert.assertEquals("Ожидаемое значение не совпало с фактическим", "Соберите бургер", homePage.getAssembleTheBurgerText());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void checkLogOutFromPersonalAccount() {
        HomePageStellarBurgers homePage = new HomePageStellarBurgers(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        homePage.openHomePage();
        homePage.waitPersonalAccountButton();
        homePage.clickLogInToYourAccountButton();
        loginPage.checkEnterButton();
        loginPage.inputEmailField(email);
        loginPage.inputPasswordField(password);
        loginPage.clickEnterButton();
        homePage.clickPersonalAccountButton();
        accountProfilePage.clickLogOutButton();
        Assert.assertTrue("Ожидаемое значение не совпало с фактическим", loginPage.checkEnterButton());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
