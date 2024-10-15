package test;

import java.io.IOException;

import org.apache.commons.io.CopyUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v128.filesystem.model.File;
import org.openqa.selenium.support.ui.Sleeper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class url {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://googlechromelabs.github.io/chrome-for-testing/#stable");
		/*String originalwindow = driver.getWindowHandle();
		driver.get("https://qa-ma.perf.infaqa.com/identity-service/home");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.switchTo().window(originalwindow);
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://www.selenium.dev/selenium/docs/api/py/api.html");
		driver.switchTo().frame(null)
		driver.manage().window().getSize().getHeight();
		driver.manage().window().getSize().getWidth();
		Dimension size= driver.manage().window().getSize();
		System.out.println(size.getHeight());
		System.out.println(size.getWidth());
		driver.manage().window().setSize(new Dimension(800, 600));
		
		driver.manage().window().getPosition().getX();
		driver.manage().window().getPosition().getY();
		
		Point pos = driver.manage().window().getPosition();
		System.out.println(pos.getX());
		System.out.println(pos.getY());
		
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.manage().window().minimize();
		Thread.sleep(2000);
		driver.manage().window().fullscreen();
		Thread.sleep(2000);
		java.io.File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new java.io.File("./image.png"));
		WebElement element= driver.findElement(By.cssSelector(".lnxdpd"));
		java.io.File scrFile1 = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile1, new java.io.File("./image1.png"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement button = driver.findElement(By.name("btnI"));
		js.executeScript("arguments[0].click();", button);*/
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement extensionElement = driver.findElement(By.id("fdpohaocaechififmbbbbbknoalclacl"));
		extensionElement.click();
		//js.executeScript("arguments[0].click();", extensionElement);
		

		Thread.sleep(3000); 
		driver.close();
	}

}
