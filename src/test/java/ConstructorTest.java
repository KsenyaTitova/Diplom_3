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
import pom.HomePageStellarBurgers;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class ConstructorTest {

    private static WebDriver driver;
    private final String nameSectionButton;
    private final String expectedTitle;

    public ConstructorTest(String nameSectionButton, String expectedTitle) {
        this.nameSectionButton = nameSectionButton;
        this.expectedTitle = expectedTitle;
    }

    @Parameterized.Parameters(name = "{index} - Раздел конструктора:{0}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Булки", "Булки"},
                {"Соусы", "Соусы"},
                {"Начинки", "Начинки"},
                {"Зелень", "Такого раздела нет!"},
        };
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", Property.PATH_DRIVER_CHROME); //Для браузера Chrome
        //System.setProperty("webdriver.chrome.driver", Property.PATH_DRIVER_YANDEX); //Для браузера Yandex
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Проверка перехода по разделам конструктора")
    public void checkingTheTransitionThroughTheSectionsOfTheConstructor() {
        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.openHomePage();
        objHomePage.waitPersonalAccountButton();
        objHomePage.clickSectionButton(nameSectionButton);
        Assert.assertEquals("Ожидаемое значение не совпало с фактическим", expectedTitle, objHomePage.getSectionTitleText(nameSectionButton));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


