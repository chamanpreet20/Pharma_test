package Test_cases;
import Utility.constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Object.Read_object;


public class Login {
	WebDriver driver;
	
//======================================Constructor=========================================================================
	 public Login(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
		  }
	 
//===============================================Login to application====================================================
public void login_user() throws Exception {

	Read_object ro= new Read_object(constants.property_path);
	driver.get(constants.URL);
	driver.findElement(ro.getLocator("username.id")).sendKeys(constants.username);
	driver.findElement(ro.getLocator("password.id")).sendKeys(constants.password);
	driver.findElement(ro.getLocator("login.xpath")).click();
	}
	}
