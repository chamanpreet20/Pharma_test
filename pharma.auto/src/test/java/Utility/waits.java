package Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Object.Read_object;

public class waits {
	WebDriver driver;
	int index;
	String reportvalue;
	Read_object ro= new Read_object(constants.property_path);
	
	 public waits(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
		  }
	 
public void explicitwait(int time,String str) throws Exception
{
	 WebDriverWait wait = new WebDriverWait(driver, time);
//wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator(str)));
wait.until(ExpectedConditions.visibilityOf(driver.findElement(ro.getLocator(str))));

	}

public void implicitwait(int time)
{
	driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
}

public void driverwait(int timeins) throws InterruptedException
{
	 Thread.sleep(timeins*1000L);
	}
}
