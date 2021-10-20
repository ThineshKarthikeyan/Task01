package com.flipkart.org;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrintingMobileNames {
			
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Thinesh\\eclipse-workspace\\Task01\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String url = "https://www.flipkart.com/";
		driver.get(url);
		driver.manage().window().maximize();
		String title = driver.getTitle();
		System.out.println(title);
		String cUrl = driver.getCurrentUrl();
		System.out.println(cUrl);
		if (url.equals(cUrl))
			System.out.println("Test passed");
		
		driver.findElement(By.xpath("//button[text()='✕']")).click();
			
		driver.findElement(By.name("q")).sendKeys("Mobiles");
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		List<WebElement> listOfMobiles = driver.findElements(By.className("_4rR01T"));
		int numberOfMobiles = listOfMobiles.size();
		for (int i = 0; i < listOfMobiles.size(); i++) {
			WebElement names = listOfMobiles.get(i);
			System.out.println();
			String name = names.getText();
			System.out.println(name);
			System.out.println();				
			if (i==0) {
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				
				driver.findElement(By.className("_4rR01T")).click();
				String parentWindow = driver.getWindowHandle();
				
				Set<String> allWindow = driver.getWindowHandles();
				for (String a : allWindow) {
					if (!a.equals(parentWindow)) {
						driver.switchTo().window(a);
						driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
						WebElement particularName = driver.findElement(By.xpath("//span[contains(@class,'NuC')]"));
						String mobileName = particularName.getText();
						System.out.println(mobileName);
						TakesScreenshot s = (TakesScreenshot)driver;
						File source = s.getScreenshotAs(OutputType.FILE);
						String title1 = driver.getTitle();
						File target = new File("C:\\Users\\Thinesh\\eclipse-workspace\\Task01\\images\\" + title1 + ".png");
						FileUtils.copyFile(source, target);
						if (mobileName.contains(name)) {
							System.out.println();
							System.out.println("Yes, It Matches");
							System.out.println();
						}
						else {
							System.out.println();
							System.out.println("It doesn't");
							System.out.println();
						}
						driver.switchTo().window(parentWindow);
						}
				}
			
			}
		}
		

	}
}
