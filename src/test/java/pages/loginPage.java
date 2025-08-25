package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ClaseBase;
import utils.Constants;

import java.time.Duration;

public class loginPage extends ClaseBase{
    public loginPage(WebDriver driver) {
        super(driver);
    }

    public By modalLogin = By.xpath("(//div[@class='modal-content'])[3]");
    By inputEmail = By.xpath("//div[@class='modal-content']//input[@id='loginusername']");
    By inputPassword = By.xpath("//div[@class='modal-content']//input[@id='loginpassword']");
    By btnLogin = By.xpath("//div[@class='modal-content']//button[text()='Log in']");

    public void loginOK() throws InterruptedException {

        estaDesplegado(modalLogin);
        agregarTexto(inputEmail, Constants.USUARIO_VALIDO);
        agregarTexto(inputPassword, Constants.CLAVE_VALIDA);
        clic(btnLogin);
    }


    public void loginNOK() throws InterruptedException {
        estaDesplegado(modalLogin);
        agregarTexto(inputEmail, Constants.USUARIO_INVALIDO);
        agregarTexto(inputPassword, Constants.CLAVE_INVALIDA);
        clic(btnLogin);
        aceptarAlerta();
    }

}
