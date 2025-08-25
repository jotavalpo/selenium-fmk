package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ClaseBase;

import java.time.Duration;

public class homePage extends ClaseBase{
    public homePage(WebDriver driver) {
        super(driver);
    }

    By btnLogin = By.xpath("//a[text()='Log in']");
    public By textoUsuarioAutenticado = By.xpath("//a[text()='Welcome demo@test.com']");

    public void ingresarAPaginaDeLogin(){
        clic(btnLogin);
    }

}
