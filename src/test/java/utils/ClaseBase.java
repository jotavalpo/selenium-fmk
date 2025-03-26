package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ClaseBase {
    private WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public ClaseBase(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement buscarElemento(By locator) {
        return driver.findElement(locator);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void doubleClick(By locator) {
        Actions action = new Actions(driver);
        WebElement elemento = driver.findElement(locator);
        action.doubleClick(elemento).perform();
    }

    public void visitarSitio(String url) {
        driver.get(url);
    }

    public WebElement esperaExplicita(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebDriver ConexionChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(240, TimeUnit.SECONDS);

        return driver;
    }

    public Boolean estaDesplegado(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
        return false;
        }
    }

    public void espera(int tiempo) throws InterruptedException {
        Thread.sleep(tiempo);
    }

    public void maximizarVentanaBrowser() {
        driver.manage().window().maximize();
    }

    public void aceptarAlerta() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cerrarBrowser() {
        this.driver.close();
    }

}

