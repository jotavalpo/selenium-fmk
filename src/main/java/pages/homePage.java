package pages;

import org.openqa.selenium.*;
import utils.BasePage;

public class homePage extends BasePage {
    public homePage(WebDriver driver) {
        super(driver);
    }

    By btnLogin = By.xpath("//a[text()='Log in']");
    public By textoUsuarioAutenticado = By.xpath("//a[text()='Welcome demo@test.com']");

    public void ingresarAPaginaDeLogin(){
        clic(btnLogin);
    }

}
