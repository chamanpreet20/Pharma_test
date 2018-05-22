package super_package;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Test_cases.Login;
import Test_cases.Report_selection;
import Test_cases.Evidence_summary;
import POI_Readwrite.Read_ClientExcel;
import POI_Readwrite.Write_exceltocompare;
import Test_cases.Compare_UIwithexcel;

//====================================This is action class where all test cases are called using TestNG===================================
public class Actionclass {
	static WebDriver driver;
	
//==========================================Open browser before starting test============================================
	@BeforeTest
	public void open_browser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\chaman.preet\\Documents\\Desktop content\\Selenium jars\\ChromeDriver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
//==========================================Create object of login class and call methods===========================================
	@Test(description="Login to application")
	public void login_app() throws Exception
	{
		Login lg=new Login(driver);
		lg.login_user();
	}
	
//==========================================Create object of Report_selection class and call methods===========================================
	@Test
	public void click_on_report() throws Exception
	{
		Report_selection rs=new Report_selection(driver);
		rs.Getreportname();
		rs.clickreport();
	}
	
//==========================================Create object of Evidence_summary class and call methods===========================================
	@Test
	public void read_uievidence() throws Exception
	{
		Evidence_summary es=new Evidence_summary(driver);
		es.click_Evidence();
		es.Read_Evidence();		
	}
	
//==========================================Create object of Read_ClientExcel class and call methods===========================================
	@Test
	public void read_excelevidence() throws Exception
	{
		Read_ClientExcel rc=new Read_ClientExcel();
		rc.read_evidence_excel();
				
	}
	
//==========================================Create object of Write_exceltocompare class and call methods===========================================
	@Test
	public void Write_exceltocompare() throws IOException
	{
		Write_exceltocompare we=new Write_exceltocompare();
		we.write_evidence();
		we.read_formatted_excel();
		we.getList();
	}
	
//==========================================Create object of Compare_UIwithexcel class and call methods===========================================
	@Test
	public void compareUI_Excel()
	{
		Compare_UIwithexcel Ce=new Compare_UIwithexcel();
		Ce.compareUInExcel();
	}
	
//=======================================Close browser after test=============================================================
	@AfterTest
	public void close_browser()
	{
		driver.quit();
	}
}
