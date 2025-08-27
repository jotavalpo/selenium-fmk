package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class TestUtil {

    public static void imprimirNombrePrueba(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test testAnotation = method.getAnnotation(Test.class);
        String testName = (testAnotation != null && !testAnotation.testName().isEmpty())
                            ? new String(testAnotation.testName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8)
                            : result.getMethod().getMethodName();

        System.out.println("\u001B[32mIniciando prueba: " + testName + "\u001B[0m");
    }

    public static void imprimirResultadoPrueba(ITestResult result, WebDriver driver) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test testAnotation = method.getAnnotation(Test.class);
        String testName = (testAnotation != null && !testAnotation.testName().isEmpty())
                            ? new String(testAnotation.testName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8)
                            : result.getMethod().getMethodName();
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Prueba exitosa: " + testName);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Prueba fallida: " + testName);
            System.out.println("Razón: " + result.getThrowable());
            new ScreenshotUtil(driver).tomarScreenshot(result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("\u001B[33mPrueba omitida: " + testName);
        }

    }
}
