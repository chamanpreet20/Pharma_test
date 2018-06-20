package Test_cases;

import java.util.ArrayList;

import Object.Read_object;
import Utility.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Object.Read_object;
import Utility.constants;
import Utility.waits;

public class Evidence_summary {
	WebDriver driver;
	 Read_object ro= new Read_object(constants.property_path);
	 waits wi=new waits(driver);
	 static ArrayList<String> Headers = new ArrayList<String>();
	 static ArrayList<String> UIList = new ArrayList<String>();

//===================================================Constructor=========================================================
	 public Evidence_summary(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
		  }
	 
//==========================Open Evidence table ===================================================================
	 public void click_Evidence() throws Exception
	 {
		// wi.explicitwait(20, "Evidence.xpath");
		 WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("Evidence.xpath")));
		 element.click();
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("/html/body/section/div/div[2]/section/div/div[1]/section[2]/a[1]")).click();
		 //driver.findElement(By.className("size20 EpifilterClick1")).click();
      }
	 
//=========================Reading Evidence table from UI and storing in UIList==============================================
	 	 public void Read_Evidence() throws Exception
	 {
		// wi.explicitwait(20, "Summary.text.xpath");
		 WebDriverWait wait = new WebDriverWait(driver, 50);
			WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("Summary.text.xpath")));
		Thread.sleep(2000);
			int rowcount = driver.findElements(By.xpath("//*[@id='datatableDiv']/tbody/tr")).size();
		// int rowcount = driver.findElements(ro.getLocator("EPI_NDMM_evidence.table.xpath")).size();
			System.out.println("Row count is :" +rowcount);
			int colcount = driver.findElements(ro.getLocator("EPI_NDMM_evidence.col.xpath")).size();
			System.out.println("Col count is :" +colcount);
			
		for(int i=0;i<=rowcount;i++)
		{
			for(int j=1;j<=colcount-1;j++)
			{
				
			//	String table_data = driver.findElement(ro.getLocator("EPI_NDMM_evidence.contentinloop.xpath")).getText();
			if(i==0){
				WebElement headerrow =driver.findElement(By.xpath("//*[@id='datatableDiv_wrapper']/div[1]/div[2]/div[1]/div/table/thead/tr/th["+j+"]"));
			    String columnname = headerrow.getText().replaceAll("([^\\w]|\\s)", "");
			    Headers.add(columnname);
					
			}	
			else{	
	          String table_data = driver.findElement(By.xpath("//*[@id='datatableDiv']/tbody/tr[" +i+ "]/td[" +j+ "]")).getText();
	          table_data.trim();
	          UIList.add(table_data);
			}}
}
		System.out.println("UI List array values outside loop: " +UIList);
		System.out.println("Header row : " + Headers);
	 }
//================================Method to return arraylist==================================================================================
	   public ArrayList<String> getheaderList() {
	 		//System.out.println("In Return function excel list data-- " +excelList);
	 	    return Headers;
	 	}
}