package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AboutRent {

    private final WebDriver webDriver;
    // Локатор поля "Когда привезти самокат"
    private final By timeToDelivaryLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    // Локатор поля "Срок аренды"
    private final By rentDeliveryLocator = By.xpath("//div[text()='* Срок аренды']");

    // Локатор для кол-ва дней при выборе "Срока аренды"
    private final By rentPeriodMenuItemLocator = By.xpath("//div[text()='сутки']");

    // Локатор кнопки "Заказать"
    private final By buttonSelectRentLocator = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор кнопки "Да" во всплывающем окне подтверждения заказа
    private final By confirmationLocator = By.xpath("//button[text()='Да']");

    private final By orderCompleteLocator = By.xpath("//div[text()='Заказ оформлен']");

    public AboutRent(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Метод для поля выбора даты "Когда привезти самокат?"
    public void selectTimeToDelivery(String date) {
        WebElement dateInput = webDriver
                .findElement(timeToDelivaryLocator);
        dateInput.sendKeys(date, Keys.ENTER);
    }


    // Нажатие на поле аренды
    public void selectRentPeriodArend() {
        WebElement rentPeriodArend = webDriver
                .findElement(rentDeliveryLocator);
        rentPeriodArend.click();
    }
    // Клик на выбор "Cрока аренды"
    public void selectRentPeriodMenuItem(){
        WebElement rentPeriodMenuItem = webDriver.findElement(rentPeriodMenuItemLocator);
        rentPeriodMenuItem.click();

    }
    // Клик на кнопку "Заказать"
    public void buttonSelectRent(){
        WebElement buttonRent = webDriver.findElement(buttonSelectRentLocator);
        buttonRent.click();
    }

    //Клик на подтверждение заказа во всплывающем окне "Хотите оформить заказ?"
    public void buttonSelectConfirmation(){
        webDriver.findElement(confirmationLocator).click();
    }

    //Проверка появления всплывающего окна с подтвержденным заказом
    public boolean orderCompleteIsDisplayed(){
        return webDriver.findElement(orderCompleteLocator).isDisplayed();
    }


}


