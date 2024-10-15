package test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ashot {

	public static void main(String[] args) throws InterruptedException {
		 //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

	        // Create a new Chrome driver instance
		 WebDriverManager.chromedriver().setup();
		 WebDriver driver = new ChromeDriver();
		 driver.manage().window().maximize();

	        // Open the webpage
	        //driver.get("https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager/5.9.2");
		 driver.get("https://prometheus.int-qaperfma.usw2.eksqa.cloudtrust.rocks/graph?g0.expr=(jvm_memory_bytes_used%7Bjob%3D%22identity-service%22%2Cdeployment_ctx%3D%22iics-qa-aws-perf-global%22%2Cinstance%3D~%22100%5C%5C.80%5C%5C.194%5C%5C.117%3A8443%22%2C%20area%3D%22heap%22%7D%20%2F%20(jvm_memory_bytes_max%7Bjob%3D%22identity-service%22%2Cdeployment_ctx%3D%22iics-qa-aws-perf-global%22%2Cinstance%3D~%22100%5C%5C.80%5C%5C.194%5C%5C.117%3A8443%22%2C%20area%3D%22heap%22%7D))%20*%20100&g0.tab=0&g0.stacked=0&g0.show_exemplars=0&g0.range_input=1h&g1.expr=(jvm_memory_bytes_used%7Bjob%3D%22identity-service%22%2Cdeployment_ctx%3D%22iics-qa-aws-perf-global%22%2Cinstance%3D~%22100%5C%5C.80%5C%5C.194%5C%5C.117%3A8443%22%2C%20area%3D%22heap%22%7D%20%2F%20(jvm_memory_bytes_max%7Bjob%3D%22identity-service%22%2Cdeployment_ctx%3D%22iics-qa-aws-perf-global%22%2Cinstance%3D~%22100%5C%5C.80%5C%5C.194%5C%5C.117%3A8443%22%2C%20area%3D%22heap%22%7D))%20*%20100&g1.tab=0&g1.stacked=0&g1.show_exemplars=0&g1.range_input=1h&g2.expr=(jvm_memory_bytes_used%7Bjob%3D%22identity-service%22%2Cdeployment_ctx%3D%22iics-qa-aws-perf-global%22%2Cinstance%3D~%22100%5C%5C.80%5C%5C.194%5C%5C.117%3A8443%22%2C%20area%3D%22heap%22%7D%20%2F%20(jvm_memory_bytes_max%7Bjob%3D%22identity-service%22%2Cdeployment_ctx%3D%22iics-qa-aws-perf-global%22%2Cinstance%3D~%22100%5C%5C.80%5C%5C.194%5C%5C.117%3A8443%22%2C%20area%3D%22heap%22%7D))%20*%20100&g2.tab=0&g2.stacked=0&g2.show_exemplars=0&g2.range_input=1h");
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		 Thread.sleep(1000);
	        // Capture a full-page screenshot using AShot
	        Screenshot screenshot = new AShot()
	                .coordsProvider(new WebDriverCoordsProvider())
	                .shootingStrategy(ru.yandex.qatools.ashot.shooting.ShootingStrategies.viewportPasting(2000))
	                .takeScreenshot(driver);

	        // Save the screenshot to a file
	        try {
	            ImageIO.write(screenshot.getImage(), "PNG", new File("full_page_screenshot.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Close the browser
	        driver.quit();

	}

}
