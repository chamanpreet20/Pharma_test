package Test_cases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import Object.Read_object;
import Utility.constants;
import Utility.waits;

public class Report_selection {

	WebDriver driver;
	int index;
	String reportvalue;
	
	 waits wi=new waits(driver);
	
//======================================Constructor=========================================================
	 public Report_selection(WebDriver driver) {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
		  }
	 
//================================Read excel to get report names and store report name with Y========================================
	 public void Getreportname() throws IOException
	 {
	 File f=new File(constants.reportselect_sheet);
	 FileInputStream in = new FileInputStream(f);
		XSSFWorkbook book = new XSSFWorkbook(in);
		XSSFSheet sheet = book.getSheet(constants.report_sheetname);
		int lastRow = sheet.getLastRowNum();
		XSSFRow row1;
		 row1 = sheet.getRow(1);
		 XSSFRow row = null;
		
		/*for(int j=0;j<=row1.getLastCellNum();j++)
		{*/
		 for(int rowno=1;rowno<=row1.getLastCellNum()+1;rowno++)
			{
					XSSFCell cell=sheet.getRow(rowno).getCell(1);
					System.out.println("get cell value " +cell);
					if(cell.toString().equals("Y"))
					{
						int Row_Y = cell.getRow().getRowNum();
						
						this.reportvalue = sheet.getRow(Row_Y).getCell(0).getStringCellValue();
					
						System.out.println(reportvalue);
					}
					else if(cell.toString().equals("N"))
		        	{
		        		System.out.println("Skip testcase");
		        		//throw new SkipException("test cases skipped ");
		        	}
					else
					{
						throw new SkipException("test cases skipped ");
					}
	 }
		 System.out.println("Outside for " +reportvalue);
	 }
	 
//=================================Pick report name saved and click on UI to open==============================================
	 public void clickreport() throws Exception
	 {
		 Read_object ro= new Read_object(constants.property_path);
		 System.out.println(reportvalue.toUpperCase());
		 Thread.sleep(2000);
	//wi.explicitwait(20,"home.title.xpath");
		// wi.implicitwait(200);
		 /*WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ro.getLocator("home.title.xpath")));*/
		
		 switch (reportvalue.toUpperCase()) {
			case "EPI-NDMM":
		 driver.findElement(ro.getLocator("EPI_NDMM_click.xpath")).click();
		 break;
			case "EPI-SOFT TISSUE SARCOMA":
				driver.findElement(ro.getLocator("EPI_Softtissue_click.xpath")).click();
		 break;
			case "HOC- OVARIAN CANCER":
				driver.findElement(ro.getLocator("HOC_Ovarion.xpath")).click();
				break;
				
			default:
				System.out.println("Report not found in loop");
				break;
		 
	 }
		}
}

