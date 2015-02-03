package taskstructure;

import java.io.IOException;

import googlesheetcontroller.SheetDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.gdata.util.ServiceException;

import botdriver.ControlCenter;

public class SendTroopsTask extends TaskNode{
	WebDriver driver;
	String x, y;
	public SendTroopsTask(WebDriver dv, String x, String y){
		this.driver = dv;
		this.x = x;
		this.y = y;
	}
	
	public void execute(){
		ControlCenter.openVillageCenter();
		try {
			System.out.println("Sending troops to: " + x + "," + y);
			String loc = SheetDriver.getBuildingById("Rally", "Village1");
			driver.navigate().to("ts2.travian.com/build.php?tt=2&id=" + loc);
			driver.findElement(By.id("troops")).findElement(By.className("line-first")).findElement(By.tagName("input")).sendKeys("5");
			driver.findElement(By.className("option")).findElements(By.tagName("label")).get(2).findElement(By.tagName("input")).click();
			driver.findElement(By.id("xCoordInput")).sendKeys(x);
			driver.findElement(By.id("yCoordInput")).sendKeys(y);
			driver.findElement(By.id("btn_ok")).click();
			driver.findElement(By.id("btn_ok")).click();
		} catch (IOException | ServiceException e) {e.printStackTrace();}
	}
	
	public double getPriority(){
		return 10.0;
	}
}