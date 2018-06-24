package com.qait.tatocmain;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TatocMain {

	public WebDriver driver = new ChromeDriver();

	public void openUrl() {
		driver.get("http://10.0.1.86/tatoc");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Basic Course")).click();		
	}

	public void clickGreenBox() {
		driver.findElement(By.className("greenbox")).click();
	}

	public void frameDungeon() {
		driver.switchTo().frame("main");
		String box1color = driver.findElement(By.id("answer")).getAttribute(
				"className"), box2color;
		while (true) {
			driver.switchTo().frame("child");
			box2color = driver.findElement(By.id("answer")).getAttribute(
					"className");
			if (box1color.equalsIgnoreCase(box2color)) {
				driver.switchTo().parentFrame();
				driver.findElement(By.linkText("Proceed")).click();
				break;
			}
			driver.switchTo().parentFrame();
			driver.findElement(By.linkText("Repaint Box 2")).click();
		}
	}

	public void dragAndDrop() {
		Actions act = new Actions(driver);
		WebElement from = driver.findElement(By.id("dragbox"));
		WebElement to = driver.findElement(By.id("dropbox"));
		act.dragAndDrop(from, to).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
	}

	public void popUpWindow() {
		driver.findElement(By.linkText("Launch Popup Window")).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {

			String ChildWindow = it.next();
			driver.switchTo().window(ChildWindow);
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.findElement(By.id("name")).sendKeys("Divyank Dawar");
				driver.findElement(By.id("submit")).click();
				break;
			}
		}
		driver.switchTo().window(MainWindow);
		driver.findElement(By.linkText("Proceed")).click();
	}

	public void cookie() {
		driver.findElement(By.linkText("Generate Token")).click();
		String Cookie_val = driver.findElement(By.id("token")).getText();
		String Cookie = Cookie_val.substring(7);
		Cookie cookie = new Cookie("Token", Cookie);
		driver.manage().addCookie(cookie);
		driver.findElement(By.linkText("Proceed")).click();
	}
}
