package test;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.Constants;

import org.openqa.selenium.WebDriver;
import utils.TestUtil;
import org.testng.ITestResult;

import static utils.ConexionUtil.*;

public class TestDemoBlaze {
    WebDriver driver;
    homePage homeP;
    loginPage loginP;

    @BeforeMethod
    public void Before(ITestResult result) {
        TestUtil.imprimirNombrePrueba(result);
        driver = startDriverChrome();
        loginP = new loginPage(driver);
        loginP.visitarSitio(Constants.URL_SITIO);
        homeP = new homePage(loginP.getDriver());

    }

    @Test(testName = "Login Erróneo", priority = 1)
    public void loginErroneo() throws InterruptedException {
        homeP.ingresarAPaginaDeLogin();
        loginP.loginNOK();
        boolean elLoginNoFueExitoso = loginP.estaDesplegado(loginP.modalLogin);
        Assert.assertTrue(elLoginNoFueExitoso, "Login sin éxito y modal aún visible esperando datos correctos");
    }

    @Test(testName = "Login Exitoso", priority = 2)
    public void loginExitoso() throws InterruptedException {
        homeP.ingresarAPaginaDeLogin();
        loginP.loginOK();
        boolean elLoginFueExitoso = homeP.estaDesplegado(homeP.textoUsuarioAutenticado);
        Assert.assertTrue(elLoginFueExitoso, "Login con éxito y texto con datos de cuenta visible");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        TestUtil.imprimirResultadoPrueba(result, driver);
        loginP.cerrarBrowser();
    }

}
