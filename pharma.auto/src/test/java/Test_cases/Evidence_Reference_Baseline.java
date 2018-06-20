package Test_cases;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Object.Read_object;
import Utility.constants;

public class Evidence_Reference_Baseline {
	WebDriver driver;
	Read_object ro= new Read_object(constants.property_path);
	static ArrayList<String> UIList = new ArrayList<String>();
	static ArrayList<String> Headers = new ArrayList<String>();
//===================================================Constructor=========================================================
	public Evidence_Reference_Baseline(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
		  }
		 
//===================================================Open Baseline Study Details=========================================================		 
public void click_Refbaseline() throws Exception{
	WebDriverWait wait = new WebDriverWait(driver, 50);
	WebElement element_evi=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("Evidence.xpath")));
	element_evi.click();
	
	WebElement element_re=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("EPI_NDMM_RefDet.xpath")));
	Thread.sleep(2000);
	element_re.click();
	
	WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("EPI_NDMM_Baselinestudy.xpath")));
	Thread.sleep(2000);
	element.click();
	Thread.sleep(2000);
}
//=========================Reading Baseline table from UI and storing in UIList==============================================
public void Read_Baseline() throws Exception{
	WebDriverWait wait = new WebDriverWait(driver, 50);
	WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("EPI_NDMM_evidence.baselinetable.xpath")));
	int rowcount = driver.findElements(ro.getLocator("EPI_NDMM_baseline.row.xpath")).size();
	System.out.println("Baseline Table Row count is :" +rowcount);
	int colcount = driver.findElements(ro.getLocator("EPI_NDMM_baseline.col.xpath")).size();
	System.out.println("Baseline Table Col count is :" +colcount);
	
	////header list
	for(int h=1;h<=2;h++){
	 for(int c=1;c<=3;c++){
		 		WebElement headerrow =driver.findElement(By.xpath("//*[@id='dtTable_wrapper']/div[2]/div[2]/div[1]/div/table/thead/tr["+h+"]/th["+c+"]"));
				String columnname = headerrow.getText().replaceAll("([^\\w]|\\s)", "");
			    Headers.add(columnname);
			 }
	}
	
	
	
	for(int i=1;i<=rowcount;i++)
	{
		for(int j=1;j<=colcount-1;j++)
		{
			
		//	String table_data = driver.findElement(ro.getLocator("EPI_NDMM_evidence.contentinloop.xpath")).getText();
			String table_data = driver.findElement(By.xpath("//*[@id='dtTable']/tbody/tr["+i+"]/td["+j+"]")).getText();
			table_data.trim();
			UIList.add(table_data);
			}
		}

  System.out.println("Captured Citation from UI: " + UIList);
  System.out.println("Header row : " + Headers);	
}
//================================Method to return arraylist==================================================================================
public ArrayList<String> getheaderList() {
	//System.out.println("In Return function excel list data-- " +excelList);
 return Headers;
}


}
