package ru.yandex.praktikum;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class OrderPage {
    private final WebDriver webDriver;

    //Локатор ввода поля "Имя"
    private final By nameInputLocator = By.xpath(("//input[@placeholder='* Имя']"));
    //Локатор ввода поля "Фамилия"
    private final By lastnameInputLocator = By.xpath(("//input[@placeholder='* Фамилия']"));
    //Локатор ввода поля "Адрес: куда привезти заказ"
    private final By adressInputLocator = By.xpath(("//input[@placeholder='* Адрес: куда привезти заказ']"));
    //Локатор ввода поля "Станция метро"
    private final By subwayInputLocator = By.xpath(("//input[@placeholder='* Станция метро']"));
    //Локатор ввода поля "Телефон: на него позвонит курьер"
    private final By numberInputLocator = By.xpath(("//input[@placeholder='* Телефон: на него позвонит курьер']"));
    //Локатор кнопки "Далее"
    private final By nextButtonLocator = By.xpath(("//button[text()='Далее']"));
    //Локатор выбора станции
    private final String stationMenuItemLocator = "//div[text()='%s']";
    //Локатор окна с подветжденным заказом


    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Метод заполнения формы "Для кого самокат"

    public void fillCustomerInfo(String name, String lastname, String adress, String subway, String number) {


        WebElement inputName = webDriver
                .findElement(nameInputLocator);
        inputName.sendKeys(name);

        WebElement inputLastname = webDriver.findElement(lastnameInputLocator);
        inputLastname.sendKeys(lastname);

        WebElement inputAdress = webDriver.findElement(adressInputLocator);
        inputAdress.sendKeys(adress);

        WebElement inputSubway = webDriver.findElement(subwayInputLocator);
        inputSubway.sendKeys(subway);

        WebElement stationArbatskaya = webDriver.findElement(By.xpath(String.format(stationMenuItemLocator, subway)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", stationArbatskaya);
        stationArbatskaya.click();

        WebElement inputTelephoneNumber = webDriver.findElement(numberInputLocator);
        inputTelephoneNumber.sendKeys(number);


    }

    //Клик на кнопку "Далее"
    public void clickNextButton() {
        WebElement nextButton = webDriver
                .findElement(nextButtonLocator);
        nextButton.click();
    }

}
