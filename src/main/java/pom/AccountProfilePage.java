package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountProfilePage {
    private final WebDriver driver;
    //Кнопка "Профиль"
    private final By profileButton = By.xpath(".//a[text()='Профиль']");
    //Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //Логотип
    private final By logoButton = By.xpath("//a[.//*[@id='filter0_f']]");
    //Кнопка "Выход"
    private final By logOutButton = By.xpath(".//button[text()='Выход']");
    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка появления кнопки \"Профиль\"")
    public boolean checkProfileButton() {
        WebElement element = driver.findElement(profileButton);
        return element.isEnabled();
    }

    @Step("Клик по кнопке \"Конструктор\"")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по логотипу")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Клик по кнопке \"Выход\"")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}
