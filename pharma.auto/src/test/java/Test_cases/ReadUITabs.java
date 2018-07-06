package Test_cases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Object.Read_object;
import Utility.constants;

public class ReadUITabs {
	WebDriver driver;
	Read_object ro= new Read_object(constants.property_path);
	 ArrayList<String> UIList = new ArrayList<String>();
	 ArrayList<String> Headers = new ArrayList<String>();
	static HashMap<String,ArrayList<String>> Headershash = new HashMap<String,ArrayList<String>>();
	static HashMap<String,ArrayList<String>> UIhash = new HashMap<String,ArrayList<String>>();
	//===================================================Constructor=========================================================
	public ReadUITabs(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
		  }
	//===================================================Open tabs Details=========================================================
	public void click_Tab(String tabName) throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("Evidence.xpath")));
		//Headers.clear();
		switch(tabName){
		case "Summary":
			//WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("Evidence.xpath")));
			element.click();
			break;
		case "CitationDetails":
			//WebElement element_evi=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("Evidence.xpath")));
			element.click();
			WebElement element_cit=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("EPI_NDMM_RefDet.xpath")));			
			Thread.sleep(2000);
			
			element_cit.click();
			Thread.sleep(2000);
			break;
		case "BaselineStudyPopulationDetails":
			//WebElement element_evi=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("Evidence.xpath")));
			element.click();
			
			WebElement element_re=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("EPI_NDMM_RefDet.xpath")));
			Thread.sleep(2000);
			element_re.click();
			
			WebElement element_base=wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("EPI_NDMM_Baselinestudy.xpath")));
			Thread.sleep(2000);
			element_base.click();
			Thread.sleep(2000);
			break;
		}
		}
		
		//=========================Reading Evidence table from UI and storing in UIList==============================================
		public void Read_Evidence(String tableName) throws Exception{
			int rowcount,colcount;
			switch(tableName){
			case "Summary":
				//read summary grid
				
				break;
			case "CitationDetails":
				//read citation grid
				rowcount = driver.findElements(ro.getLocator("EPI_NDMM_citation.row.xpath")).size();
				colcount = driver.findElements(ro.getLocator("EPI_NDMM_citation.col.xpath")).size();
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
				break;
			case "BaselineStudyPopulationDetails":
				//read baseline grid
				rowcount = driver.findElements(ro.getLocator("EPI_NDMM_baseline.row.xpath")).size();
				colcount = driver.findElements(ro.getLocator("EPI_NDMM_baseline.col.xpath")).size();
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

				  //System.out.println("Captured Baseline from UI: " + UIList);
				  //System.out.println("Header row : " + Headers);	
				   
				break;
			}
			Headershash.put(tableName, Headers);
			UIhash.put(tableName, UIList);
			System.out.println("Captured data from UI: " + UIList);
			System.out.println("Header row : " + Headers);	
			System.out.println("=============================================");
						
		}
		//================================Method to return arraylist==================================================================================
		public HashMap<String,ArrayList<String>> getheaderList() {
			
		 return Headershash;
		}	
        
		
}
