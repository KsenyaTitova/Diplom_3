package pom;

import data.GeneralParameters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageStellarBurgers {

    private final WebDriver driver;
    private final String url = GeneralParameters.BASE_URI;
    //Локатор кнопки "Войти в аккаунт"
    private final By logInToYourAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    //Локатор кнопки "Личный кабинет"
    private final By personalAccountButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    //Локатор кнопки "Оформить заказ"
    private final By placeAnOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    //Локатор текста "Соберите бургер"
    private final By assembleTheBurgerText = By.xpath(".//h1[text() = 'Соберите бургер']");
    //Локатор кнопки "Булки"
    private final By sectionBunButton = By.xpath(".//span[text() = 'Булки']");
    //Локатор кнопки "Соусы"
    private final By sectionSaucesButton = By.xpath(".//span[text() = 'Соусы']");
    //Локатор кнопки "Начинки"
    private final By sectionIngredientsButton = By.xpath(".//span[text() = 'Начинки']");
    //Локатор заголовка раздела "Булки"
    private final By sectionTitleBunText = By.xpath(".//h2[text() = 'Булки']");
    //Локатор заголовка раздела "Соусы"
    private final By sectionTitleSaucesText = By.xpath(".//h2[text() = 'Соусы']");
    //Локатор заголовка раздела "Начинки"
    private final By sectionTitleIngredientsText = By.xpath(".//h2[text() = 'Начинки']");

    // конструктор класса
    public HomePageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы приложения")
    public void openHomePage() {
        driver.get(url);
    }

    @Step("Ожидание загрузки страницы")
    public void waitPersonalAccountButton() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
    }

    @Step("Клик по кнопке \"Личный кабинет\"")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Клик по кнопке \"Войти в аккаунт\"")
    public void clickLogInToYourAccountButton() {
        driver.findElement(logInToYourAccountButton).click();
    }

    @Step("Проверка появления кнопки \"Оформить заказ\"")
    public boolean checkPlaceAnOrderButton() {
        WebElement element = driver.findElement(placeAnOrderButton);
        return element.isEnabled();
    }

    @Step("Получение текста \"Соберите бургер\"")
    public String getAssembleTheBurgerText() {
        return driver.findElement(assembleTheBurgerText).getText();
    }

    @Step("Клик по разделу конструктора")
    public void clickSectionButton(String nameButton) {
        switch (nameButton) {
            case "Булки":
                driver.findElement(sectionSaucesButton).click();
                driver.findElement(sectionBunButton).click();
                break;
            case "Соусы":
                driver.findElement(sectionSaucesButton).click();
                break;
            case "Начинки":
                driver.findElement(sectionIngredientsButton).click();
                break;
        }
    }

    @Step("Получение текста заголовка раздела")
    public String getSectionTitleText(String nameTitle) {
        switch (nameTitle) {
            case "Булки":
                return driver.findElement(sectionTitleBunText).getText();
            case "Соусы":
                return driver.findElement(sectionTitleSaucesText).getText();
            case "Начинки":
                return driver.findElement(sectionTitleIngredientsText).getText();
        }
        return "Такого раздела нет!";
    }
}
