package ru.yandex.praktikum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)

public class OrderTest {

    private WebDriver webDriver;
    private final String urlStartPage = "https://qa-scooter.praktikum-services.ru/";

    private String name;
    private String lastname;
    private String adress;
    private String subway;
    private String number;

    public OrderTest(String name, String lastname, String adress,String subway,String number){
        this.name=name;
        this.lastname=lastname;
        this.adress=adress;
        this.subway=subway;
        this.number=number;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Маша", "Иванова", "Москва, ул. Комсомольская 57", "Римская", "79999999999"},
                {"Иван", "Иванович", "ул. Огородная 9", "Смоленская", "70000000000"},
        };
    }

    @Before

        public void setup() {
       webDriver = WebDriverFactory.getWebDriver( System.getProperty("browser", "firefox"));
        webDriver.get(urlStartPage);
    }

    @Test
    public void createOrderClickUpButton() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrderUpButton();
        mainPage.closeCookiesWindow();


        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo(name, lastname, adress, subway, number);
        orderPage.clickNextButton();




        AboutRent aboutRent = new AboutRent(webDriver);
        aboutRent.selectTimeToDelivery("21.01.2024");
        aboutRent.selectRentPeriodArend();
        aboutRent.selectRentPeriodMenuItem();
        aboutRent.buttonSelectRent();
        aboutRent.buttonSelectConfirmation();
        aboutRent.orderCompleteIsDisplayed();

    }

    @Test
    public void createOrderClickDownButton() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrderDownButton();
        mainPage.closeCookiesWindow();


        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo(name, lastname, adress, subway, number);
        orderPage.clickNextButton();


        AboutRent aboutRent = new AboutRent(webDriver);
        aboutRent.selectTimeToDelivery("21.01.2024"); // выбор даты в поле "Когда привезти самокат"
        aboutRent.selectRentPeriodArend(); // нажатие на поле с выбором "Скрока аренды"
        aboutRent.selectRentPeriodMenuItem(); // выбор срока аренды из представенного списка
        aboutRent.buttonSelectRent(); // нажатие на кнопку "Заказать"
        aboutRent.buttonSelectConfirmation(); // нажатие на кнопку "Да" в окне подтверждения заказа
        aboutRent.orderCompleteIsDisplayed(); //Проверка появления всплывающего окна с подтвержденным заказом

    }

    @After
    public void tearDown() {

        webDriver.quit();
    }
}