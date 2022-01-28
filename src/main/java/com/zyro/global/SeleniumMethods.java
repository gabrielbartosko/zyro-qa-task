package com.zyro.global;

import com.zyro.page.AbstractPageObject;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.zyro.driver.DriverManager.getDriver;

public class SeleniumMethods extends AbstractPageObject {

    public Actions action() {
        return new Actions(getDriver());
    }

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public void navigateToURL(String baseURL, String endURL) {
        getDriver().navigate().to(baseURL + endURL);
    }

    @SafeVarargs
    private <T> By extractBy(String elementLocator, T... args) {
        String locator = String.format(elementLocator, args);
        if (locator.startsWith("//") || locator.startsWith("(//")) {
            return By.xpath(locator);
        } else {
            return By.cssSelector(locator);
        }
    }

    @SafeVarargs
    public final <T> WebElement findElement(String elementLocator, T... args) {
        return getDriver().findElement(extractBy(String.format(elementLocator, args)));
    }

    @SafeVarargs
    public final <T> void deleteText(String elementLocator, T... args) {
        waitForElementToBeClickable(elementLocator, args);
        findElement(elementLocator, args).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        findElement(elementLocator, args).sendKeys(Keys.DELETE);
    }

    @SafeVarargs
    public final <T> void waitForElementToBeClickable(String elementLocator, T... args) {
        getWebDriverWait().until(
                ExpectedConditions.elementToBeClickable(extractBy(String.format(elementLocator, args))));
    }

    @SafeVarargs
    public final <T> void enterText(String elementLocator, String keysToSend, T... args) {
        waitForElementToBeClickable(elementLocator, args);
        deleteText(elementLocator, args);
        findElement(elementLocator, args).sendKeys(keysToSend);
    }

    @SafeVarargs
    public final <T> void waitForElementToBeVisible(String elementLocator, T... args) {
        getWebDriverWait().until(
                ExpectedConditions.visibilityOfElementLocated(extractBy(String.format(elementLocator, args))));
    }

    @SafeVarargs
    public final <T> void waitForElementToBeInvisible(String elementLocator, T... args) {
        getWebDriverWait().until(
                ExpectedConditions.invisibilityOfElementLocated(extractBy(String.format(elementLocator, args))));
    }

    @SafeVarargs
    public final <T> void click(String elementLocator, T... args) {
        waitForElementToBeVisible(elementLocator, args);
        waitForElementToBeClickable(elementLocator, args);
        action().click(findElement(elementLocator, args)).perform();
    }

    @SafeVarargs
    public final <T> String getText(String elementLocator, T... args) {
        waitForElementToBeVisible(elementLocator, args);
        return findElement(elementLocator, args).getText();
    }

    @SafeVarargs
    public final <T> String getAttributeValue(String elementLocator, String attributeName, T... args) {
        waitForElementToBeVisible(elementLocator, args);
        return findElement(elementLocator, args).getAttribute(attributeName);
    }

    public void waitForPageToLoad() {
        getWebDriverWait().until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals(
                        "complete"));
    }
}
