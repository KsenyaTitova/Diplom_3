import data.UserGeneration;
import driver.Property;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.ForgotPasswordPage;
import pom.HomePageStellarBurgers;
import pom.LoginPage;
import pom.RegisterPage;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class LoginToAccountTest {
    private static WebDriver driver;
    private static final UserGeneration newUser = new UserGeneration();
    private final String nameEnterButton;

    public LoginToAccountTest(String nameEnterButton) {
        this.nameEnterButton = nameEnterButton;
    }

    @Parameterized.Parameters(name = "{index} - Вход по кнопке {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {"logInToYourAccountButton", "«Войти в аккаунт» на главной"},
                {"enterButtonLogin", "«Войти» через Личный кабинет"},
                {"enterButtonRegister", "«Войти» в форме регистрации"},
                {"enterButtonForgotPage", "«Войти»  в форме восстановления пароля"},
        };
    }

    @Before
    public void setup () {
         System.setProperty("webdriver.chrome.driver", Property.PATH_DRIVER_CHROME); //Для браузера Chrome
         //System.setProperty("webdriver.chrome.driver", Property.PATH_DRIVER_YANDEX); //Для браузера Yandex
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     }

     @Test
     @DisplayName("Проверка входа")
     public void checkEnter() {
         HomePageStellarBurgers homePage = new HomePageStellarBurgers(driver);
         RegisterPage registerPage = new RegisterPage(driver);
         ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
         LoginPage loginPage = new LoginPage(driver);
         registerPage.openRegisterPage();
         registerPage.waitRegisterButton();
         String name = newUser.getName();
         String email = newUser.getEmail();
         String password = newUser.getPassword();
         registerPage.inputNameField(name);
         registerPage.inputEmailField(email);
         registerPage.inputPasswordField(password);
         registerPage.clickRegisterButton();
         switch (nameEnterButton) {
             case "logInToYourAccountButton":
                 homePage.openHomePage();
                 homePage.waitPersonalAccountButton();
                 homePage.clickLogInToYourAccountButton();
                 break;
             case "enterButtonLogin":
                 homePage.openHomePage();
                 homePage.waitPersonalAccountButton();
                 homePage.clickPersonalAccountButton();
                 break;
             case "enterButtonRegister":
                 registerPage.openRegisterPage();
                 registerPage.waitRegisterButton();
                 registerPage.clickEnterButton();
                 break;
             case "enterButtonForgotPage":
                 forgotPasswordPage.openForgotPassword();
                 forgotPasswordPage.waitEnterButton();
                 forgotPasswordPage.clickEnterButton();
                 break;
         }
         loginPage.checkEnterButton();
         loginPage.inputEmailField(email);
         loginPage.inputPasswordField(password);
         loginPage.clickEnterButton();
         Assert.assertTrue("Ожидаемое значение не совпало с фактическим", homePage.checkPlaceAnOrderButton());
     }

    @After
    public void tearDown() {
        driver.quit();
    }
}
