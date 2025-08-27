package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

public class ScreenshotUtil {

    WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void tomarScreenshot(String nombreArchivo) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            nombreArchivo = nombreArchivo + LocalDateTime.now().toString().replace(":", "-").replace(".", "-");
            try {
                File destino = new File(System.getProperty("user.dir") + "/src/test/resources/screenshots/" + nombreArchivo + ".png");
                destino.getParentFile().mkdirs();
                Files.copy(screenshot.toPath(), destino.toPath());
                System.out.println("Screenshot guardado en: " + destino.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error al guardar el screenshot, mensaje: " + e.getMessage());
                System.out.println("Error al guardar el screenshot, causa: " + e.getCause());
            }
    }
}
