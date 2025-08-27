package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    private WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement buscarElemento(By locator) {
        return driver.findElement(locator);
    }

    public void clic(By selector) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
            wait.until(ExpectedConditions.elementToBeClickable(selector)).click();
        }catch (Exception e) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(selector));
                js.executeScript("arguments[0].click;", element);
            } catch (Exception ex) {
                throw new NotFoundException(ex);
            }
        }
    }

    public void agregarTexto(By locator, String texto) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement elemento = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            elemento.clear();
            elemento.sendKeys(texto);
        } catch (Exception e) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement elemento = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                js.executeScript("arguments[0].value= '';", elemento);
                js.executeScript("arguments[0].value= arguments[1];", elemento, texto);
            } catch (Exception ex) {
                throw new NotFoundException(ex);
            }
        }
    }

    public void visitarSitio(String url) {
        driver.get(url);
    }

    public WebElement esperaExplicita(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public Boolean estaDesplegado(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void espera(int tiempo) throws InterruptedException {
        Thread.sleep(tiempo);
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

    public void scrolldown(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            if (driver instanceof JavascriptExecutor) {
                js.executeScript("arguments[0].scrollIntoView(true);", element);
            } else {
                throw new RuntimeException("El driver no soporta JavaScript");
            }
        } catch (Exception e) {
           throw new RuntimeException("No se pudo hacer scroll al elemento: ", e);
        }
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String mainTab() {
        return driver.getWindowHandle();
    }

    public void focus(String pestanaOriginal) {

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if (!pestanaOriginal.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    public void focusOldTab(String mainTab) {
        driver.switchTo().window(mainTab);
    }

    public void selectDropdownOptionByText(By locator, String visibleText) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    public void selectDropdownOptionByValue(By locator, String value) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }

    public void esperarCargaYDesaparicionLoader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By loader = By.cssSelector("div.loader");
        while (true) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(loader));
                break;
            } catch (TimeoutException e) {
                break;
            }
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

}