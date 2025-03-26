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
    public By textoLoginExitoso = By.xpath("//a[text()='Welcome demo@test.com']");

    public void loginOK() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalLogin));

            if (estaDesplegado(inputEmail)) {
                WebElement txtEmail = this.buscarElemento(inputEmail);
                txtEmail.clear();
                txtEmail.sendKeys(Constants.USUARIO_VALIDO);
            }

            if (estaDesplegado(inputPassword)) {
                WebElement txtPassword = this.buscarElemento(inputPassword);
                txtPassword.clear();
                txtPassword.sendKeys(Constants.CLAVE_VALIDA);
            }

            if (estaDesplegado(btnLogin)) {
                try {
                    WebElement botonLogin = this.buscarElemento(this.btnLogin);
                    wait.until(ExpectedConditions.elementToBeClickable(botonLogin));
                    botonLogin.click();

                } catch (NoSuchElementException e) {
                    throw e;
                }
            }
    }


    public void loginNOK() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalLogin));

        if (estaDesplegado(inputEmail)) {
            WebElement txtEmail = this.buscarElemento(inputEmail);
            txtEmail.clear();
            txtEmail.sendKeys(Constants.USUARIO_INVALIDO);
        }

        if (estaDesplegado(inputPassword)) {
            WebElement txtPassword = this.buscarElemento(inputPassword);
            txtPassword.clear();
            txtPassword.sendKeys(Constants.CLAVE_INVALIDA);
        }

        if (estaDesplegado(btnLogin)) {
            try {
                WebElement botonLogin = this.buscarElemento(this.btnLogin);
                wait.until(ExpectedConditions.elementToBeClickable(botonLogin));
                botonLogin.click();

            } catch (NoSuchElementException e) {
                throw e;
            }
        }
    }

}
