package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class automation {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		
		// getting properties from files based on environment
			String env = args[0], startTime = args[1], endTime = args[2];
			String	screenshotlocation = System.getProperty("user.dir") + "\\screenshot";

				String propFileName = env + ".properties"; // Replace with your actual properties file name
				ClassLoader loader = automation.class.getClassLoader();
				InputStream inputStream = loader.getResourceAsStream(propFileName);
				if (inputStream == null) {
					throw new RuntimeException("Cannot find properties file: " + propFileName);
				}

				Properties p = new Properties();
				p.load(inputStream);
				String deployment_context = p.getProperty("deployment_context"), namespace = p.getProperty("namespace");
		
	
		 
		 
			 //static String[] job = new String[]{"identity-service", "ma-service"};
			 String[] job=new String[]{"identity-service"};
	
			 String diffTime=calculateDiffTime(startTime,endTime);
			 for(int i =0; i<job.length;i++) {
				 
			
			 String grafanaUrl="";
			 String Panel1 = "https://prometheus.int-qaperfma.usw2.eksqa.cloudtrust.rocks/graph?\r\n"
			 		+ "g0.expr=(jvm_memory_bytes_used%7Bdeployment_ctx%3D%22"+deployment_context+"%22%2Carea%3D%22heap%22%2Cjob%3D%27\r\n"+job[i]
			 		+ "%27%7D%20%2F%20(jvm_memory_bytes_max%7Bdeployment_ctx%3D%22"+deployment_context+"%22%2Carea%3D%22heap%22%2Cjob%3D%27\r\n"+job[i]+"%27%7D))%20*%20100"
			 		+ "&g0.tab=0&g0.stacked=0&g0.show_exemplars=0&\r\n"
			 		+ "g0.range_input="+diffTime+"&\r\n"
			 		+ "g0.end_input="+endTime+"&\r\n"
			 		+ "g0.moment_input=2024-10-10%2008%3A36%3A40";
			grafanaUrl+=Panel1+"&";
			String Panel2="g1.expr=sum(node_namespace_pod_container%3Acontainer_cpu_usage_seconds_total%3Asum_irate%7Bnamespace%3D%22"+namespace+"%22%2Cpod%3D~%27\r\n"
			 		+ job[i]+".*%27%7D)%20by%20(pod)&\r\n"
			 		+ "g1.tab=0&g1.stacked=1&\r\n"
			 		+ "g1.show_exemplars=0&\r\n"
			 		+ "g1.range_input="+diffTime+"&\r\n"
			 		+ "g1.end_input="+endTime+"&\r\n";
			grafanaUrl+=Panel2;
			 driver.get(grafanaUrl);
			 captureScreenshot(driver,screenshotlocation,job[i]);
			 }
		Thread.sleep(1000);
		//driver.close();	 		
	 }

		 
private static String calculateDiffTime(String startTime, String endTime) {
		// TODO Auto-generated method stub
	String diffTime="";
	 // Input date strings
    String dateStr1 = startTime;
    String dateStr2 = endTime; // Another date for example

    // Define the date format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Parse the dates
    LocalDateTime dateTime1 = LocalDateTime.parse(dateStr1, formatter);
    LocalDateTime dateTime2 = LocalDateTime.parse(dateStr2, formatter);

    // Get the difference between two dates
    Duration duration = Duration.between(dateTime1, dateTime2);

    // Extract minutes, seconds, and milliseconds
    long minutes = duration.toMinutes();
    long seconds = duration.getSeconds() % 60;
    long milliseconds = duration.toMillis() % 1000;

    // Print the result in the desired format
    diffTime=String.format("%dm%ds%dms", minutes, seconds, milliseconds);
    System.out.printf("%dm%ds%dms\n", minutes, seconds, milliseconds);
		return diffTime;
	}


public static void captureScreenshot(WebDriver webdriver, String fileWithPath,String podname) throws Exception {
	 JavascriptExecutor js = (JavascriptExecutor) webdriver;
	 js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	 Thread.sleep(1000);
        // Capture a full-page screenshot using AShot
        Screenshot screenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ru.yandex.qatools.ashot.shooting.ShootingStrategies.viewportPasting(2000))
                .takeScreenshot(webdriver);
        
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String time = sdf1.format(timestamp);
		String filename = podname + "_" + time;
		// File outputFile = new File("C:/path/to/save/image.png");
System.out.println(fileWithPath+"/"+filename+".png");
        // Save the screenshot to a file
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(fileWithPath+"\\"+filename+".png"));
     
        } catch (IOException e) {
            e.printStackTrace();
        }
}


}


