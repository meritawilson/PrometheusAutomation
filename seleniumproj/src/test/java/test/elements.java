package test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class elements {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://google.co.in");
		driver.navigate().to("https://trytestingthis.netlify.app/");
		//WebElement search = driver.findElement(By.name("q"));
		//search.sendKeys("Whatever",Keys.ENTER);
		List<WebElement> options = driver.findElements(By.name("Optionwithcheck[]"));
		for(WebElement element : options) {
			System.out.println(element.getText());
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.close();

	}

}
