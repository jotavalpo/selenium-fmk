package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class ConexionUtil {
    private ConexionUtil() {}

    static {
        System.setProperty("webdriver.edge.silentOutput", "true");
        Logger seleniumLogger = Logger.getLogger("org.openqa.selenium");
        seleniumLogger.setLevel(Level.SEVERE);
    }

    public static WebDriver startDriverEdge() {
        Map<String, Object> preferencias = new HashMap<>();
        preferencias.put("safebrowsing.enabled", true);

        EdgeOptions opciones = new EdgeOptions();
        opciones.setExperimentalOption("prefs", preferencias);

        if (Boolean.getBoolean("headless") || Boolean.parseBoolean(System.getenv("HEADLESS"))) {
            System.out.println("Ejecución en modo headless");
            opciones.addArguments("--headless;");
            opciones.addArguments("--disable-gpu");
        }

        opciones.addArguments("--window-size=1920,1080");

        WebDriver driver = new EdgeDriver(opciones);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(240));

        return driver;
    }

    public static WebDriver startDriverChrome() {
        Map<String, Object> preferencias = new HashMap<>();
        preferencias.put("safebrowsing.enabled", true);

        ChromeOptions opciones = new ChromeOptions();
        opciones.setExperimentalOption("prefs", preferencias);

        if (Boolean.getBoolean("headless") || Boolean.parseBoolean(System.getenv("HEADLESS"))) {
            System.out.println("Ejecución en modo headless");
            opciones.addArguments("--headless");
            opciones.addArguments("--disable-gpu");
        }

        opciones.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(opciones);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(240));

        return driver;
    }

    public static WebDriver startDriverEdgeDos() {
        WebDriverManager.edgedriver().setup();

        Map<String, Object> preferencias = new HashMap<>();
        preferencias.put("safebrowsing.enabled", true);

        EdgeOptions opciones = new EdgeOptions();
        opciones.setExperimentalOption("prefs", preferencias);

        if (Boolean.getBoolean("headless") || Boolean.parseBoolean(System.getenv("HEADLESS"))) {
            System.out.println("Ejecución en modo headless");
            opciones.addArguments("--headless");
            opciones.addArguments("--disable-gpu");
        }

        opciones.addArguments("--window-size=1920,1080");

        WebDriver driver = new EdgeDriver(opciones);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(240));

        return driver;
    }
}