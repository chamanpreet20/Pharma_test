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
import Utility.waits;

public class Evidence_Reference_Citation {
	WebDriver driver;
	Read_object ro= new Read_object(constants.property_path);
	static ArrayList<String> UIList = new ArrayList<String>();
	static ArrayList<String> Headers = new ArrayList<String>();
//===================================================Constructor=========================================================
	public Evidence_Reference_Citation(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
		  }
		 
//===================================================Open Citation Details=========================================================		 
public void click_RefCit() throws Exception{
	WebDriverWait wait = new WebDriverWait(driver, 50);
	WebElement element_evi=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("Evidence.xpath")));
	element_evi.click();
	
	WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("EPI_NDMM_RefDet.xpath")));
	Thread.sleep(2000);
	element.click();
	}
//=========================Reading Evidence table from UI and storing in UIList==============================================
public void Read_Citation() throws Exception{
	WebDriverWait wait = new WebDriverWait(driver, 50);
	WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("EPI_NDMM_evidence.cittable.xpath")));
	int rowcount = driver.findElements(ro.getLocator("EPI_NDMM_citation.row.xpath")).size();
	System.out.println("Citation Table Row count is :" +rowcount);
	int colcount = driver.findElements(ro.getLocator("EPI_NDMM_citation.col.xpath")).size();
	System.out.println("Citation Table Col count is :" +colcount);
	
	for(int i=0;i<=rowcount;i++)
	{
		for(int j=1;j<=colcount-1;j++)
		{
			if(i==0){
				WebElement headerrow =driver.findElement(By.xpath("//*[@id='dtTable_wrapper']/div[2]/div[2]/div[1]/div/table/thead/tr/th["+j+"]"));
			    String columnname = headerrow.getText().replaceAll("([^\\w]|\\s)", "");
			    Headers.add(columnname);
			}	
			else{
		//	String table_data = driver.findElement(ro.getLocator("EPI_NDMM_evidence.contentinloop.xpath")).getText();
			if(j==5){
				WebElement link=driver.findElement(By.xpath("//*[@id='dtTable']/tbody/tr["+i+"]/td[5]/div/a[@href]"));
				String table_data =link.getAttribute("href").toString();
				//System.out.println("link value at :" + i +" is: " + table_data);
				if(!table_data.contains("javascript:void(0);")){
				UIList.add(table_data);
				}
				else{
					UIList.add("");
				}
				//UIList.add(table_data);
			}
			else{
			String table_data = driver.findElement(By.xpath("//*[@id='dtTable']/tbody/tr["+i+"]/td["+j+"]")).getText();
			table_data.trim();
			UIList.add(table_data);
			}
			//UIList.add(table_data);
			} 
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
