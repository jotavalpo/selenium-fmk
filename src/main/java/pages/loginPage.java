package pages;

import org.openqa.selenium.*;
import utils.BasePage;
import utils.Constants;

public class loginPage extends BasePage {
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
