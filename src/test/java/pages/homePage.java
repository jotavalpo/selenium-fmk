package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ClaseBase;

import java.time.Duration;

public class homePage extends ClaseBase{
    public homePage(WebDriver driver) {
        super(driver);
    }

    By btnLogin = By.xpath("//a[text()='Log in']");
    By textoUsuarioAutenticado = By.xpath("//a[text()='Welcome demo@test.com']");

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

    public void ingresarAPaginaDeLogin(){

        WebElement botonLogin = wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        botonLogin.click();
    }

    public void validarBotonPostLogin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(textoUsuarioAutenticado));
    }

}
