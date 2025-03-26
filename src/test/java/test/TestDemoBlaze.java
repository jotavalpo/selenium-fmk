package test;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.Constants;

import org.openqa.selenium.WebDriver;

public class TestDemoBlaze {
    WebDriver driver;
    homePage homeP;
    loginPage loginP;

    @BeforeMethod
    public void Before() {
        loginP = new loginPage(driver);
        loginP.ConexionChromeDriver();
        loginP.getDriver();
        loginP.visitarSitio(Constants.URL_SITIO);
        loginP.maximizarVentanaBrowser();
        homeP = new homePage(loginP.getDriver());

    }

    @Test(testName = "Login Erróneo", priority = 1)
    public void loginErroneo() throws InterruptedException {
        homeP.ingresarAPaginaDeLogin();
        loginP.loginNOK();
        loginP.aceptarAlerta();
        boolean elLoginNoFueExitoso = loginP.estaDesplegado(loginP.modalLogin);
        Assert.assertTrue(elLoginNoFueExitoso, "Login sin éxito y modal aún visible esperando datos correctos");
    }

    @Test(testName = "Login Exitoso", priority = 2)
    public void loginExitoso() throws InterruptedException {
        homeP.ingresarAPaginaDeLogin();
        loginP.loginOK();
        homeP.validarBotonPostLogin();
        boolean elLoginFueExitoso = loginP.estaDesplegado(loginP.textoLoginExitoso);
        Assert.assertTrue(elLoginFueExitoso, "Login con éxito y texto con datos de cuenta visible");
    }

    @AfterMethod
    public void afterMethod() {
        loginP.cerrarBrowser();
    }

}
