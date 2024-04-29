package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver webDriver;

    private final By cookiesButtonLocator = By.xpath("//button[text()='да все привыкли']");
    private final String quastionLocator = "accordion__heading-%s";
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";


    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Клик по кнопке "Заказать" вверху страницы
    public void clickCreateOrderUpButton() {
        WebElement orderButtonUpPage = webDriver
                .findElement(By.xpath("//div[contains(@class, 'Header')]//button[text()='Заказать']"));
        orderButtonUpPage.click();
    }

    // Нажать на кнопку закрытия всплывающего окна с куки
    public void closeCookiesWindow() {
        WebElement cookieBtn = webDriver
                .findElement(cookiesButtonLocator);
        cookieBtn.click();
    }

    // Метод для скроллинга до списка «Вопросы о важном», и нажатие на стрелку выпадающего списка.
    public void expandQuestion(int index) {
        WebElement element = webDriver.findElement(By.id(String.format(quastionLocator, index)));
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // Метод для сравнения текста кнопки выдающего списка и текста в списке
    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }


    // Клик по кнопке "Заказать" внизу страницы
    public void clickCreateOrderDownButton() {
        WebElement orderButtonUpPage = webDriver
                .findElement(By.xpath("//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", orderButtonUpPage );
        orderButtonUpPage.click();

    }
}