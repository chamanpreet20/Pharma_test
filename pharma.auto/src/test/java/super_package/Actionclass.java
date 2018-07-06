package super_package;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Test_cases.Login;
import Test_cases.ReadUITabs;
import Test_cases.Report_selection;
import Test_cases.Evidence_summary;
import POI_Readwrite.Read_ClientExcel;
import POI_Readwrite.Read_ClientExcel_Baseline_util;
import POI_Readwrite.Read_ClientExcel_Citation_util;
import POI_Readwrite.Read_ClientExcel_common;
import POI_Readwrite.Write_exceltocompare;
import POI_Readwrite.Write_exceltocompare_baseline_util;
import POI_Readwrite.Write_exceltocompare_cit_util;
import POI_Readwrite.Write_exceltocompare_common;
import Test_cases.Compare_UIwithexcel;
import Test_cases.Compare_UIwithexcel_baseline_util;
import Test_cases.Compare_UIwithexcel_cit_util;
import Test_cases.Compare_UIwithexcel_common;
import Test_cases.Evidence_Reference_Baseline;
import Test_cases.Evidence_Reference_Citation;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

//====================================This is action class where all test cases are called using TestNG===================================
public class Actionclass {
	public static WebDriver driver;
	public static Logger App_logs=Logger.getLogger("devpinoyLogger");
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	private static HashMap<String, WebDriver> driverObjMap=new HashMap<String, WebDriver>();
	
//==========================================Open browser before starting test============================================
	@BeforeTest
	public void open_browser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\chaman.preet\\Documents\\Desktop content\\Selenium jars\\ChromeDriver\\chromedriver.exe");
		driver=new ChromeDriver();
		App_logs.info("Open browser");
		driver.manage().window().maximize();
	}
	@DataProvider(name = "tabnameDP")
	public static String[] Tabnames(){
		return new String[]{"CitationDetails","BaselineStudyPopulationDetails"} ;
		
	}	
//==========================================Create object of login class and call methods===========================================
	@Test(description="Login to application")
	public void login_app() throws Exception
	{
		App_logs.info("Logging to application");
		Login lg=new Login(driver);
		lg.login_user();
	}
	
//==========================================Create object of Report_selection class and call methods===========================================
	@Test
	public void click_on_report() throws Exception
	{
		App_logs.info("Selecting report after logging to application");
		Report_selection rs=new Report_selection(driver);
		rs.Getreportname();
		rs.clickreport();
	}
//==========================================Create object of ReadUITabs class and call methods===========================================
	
		//@Parameters({ "tabName" })
		@Test(dataProvider = "tabnameDP")
		public void read_uievidence_common(String tabName) throws Exception
		{
		    ReadUITabs es=new ReadUITabs(driver);
			es.click_Tab(tabName);
			es.Read_Evidence(tabName);
			}		
//==========================================Create object of Evidence_summary class and call methods===========================================
	@Test
	public void read_uievidence() throws Exception
	{
		App_logs.info("Select Evidence table and read it");
		Evidence_summary es=new Evidence_summary(driver);
		es.click_Evidence();
		es.Read_Evidence();		
	}
//==========================================Create object of Evidence_CitationDetails class and call methods===========================================	
	@Test
	public void read_uicitation() throws Exception{
		Evidence_Reference_Citation ecit= new Evidence_Reference_Citation(driver);
		ecit.click_RefCit();
		ecit.Read_Citation();
	}	
//==========================================Create object of Evidence_BaselinestudyDetails class and call methods===========================================	
	@Test
	public void read_uibaselinestudy() throws Exception{
		Evidence_Reference_Baseline ebase= new Evidence_Reference_Baseline(driver);
		ebase.click_Refbaseline();
		ebase.Read_Baseline();
	}		
//==========================================Create object of Read_ClientExcel class and call methods===========================================
	@Test
	public void read_excelevidence() throws Exception
	{
		App_logs.info("Read excel file to write");
		Read_ClientExcel rc=new Read_ClientExcel();
		rc.read_evidence_excel();
				
	}
	//==========================================Create object of Read_ClientExcel_common class and call methods===========================================
			@Test(dataProvider = "tabnameDP")
			public void read_excelevidence_common(String tableName) throws Exception
			{
				Read_ClientExcel_common rc=new Read_ClientExcel_common();
				rc.read_evidence_excel(tableName);
				rc.write_evidence(tableName);
						
			}		
//==========================================Create utility object of Read_ClientExcel_Citation class and call methods===========================================
	@Test
	public void read_excelevidence_cit_util() throws Exception
	{
		Read_ClientExcel_Citation_util rc=new Read_ClientExcel_Citation_util();
		rc.read_evidence_excel();
					
	}		
//==========================================Create object of utility  Read_ClientExcel_BaselineStudy class and call methods===========================================
	@Test
	public void read_excelevidence_baseline_util() throws Exception
	{
		Read_ClientExcel_Baseline_util rc=new Read_ClientExcel_Baseline_util();
		rc.read_evidence_excel();
							
		}		
//==========================================Create object of Write_exceltocompare class and call methods===========================================
	@Test
	public void Write_exceltocompare() throws IOException
	{
		App_logs.info("Write excel file in format you need");
		Write_exceltocompare we=new Write_exceltocompare();
		we.write_evidence();
		we.read_formatted_excel();
		we.getList();
	}
	//==========================================Create object of Write_exceltocompare_common class and call methods===========================================
	
			@Test(dataProvider = "tabnameDP",dependsOnMethods = { "read_excelevidence_common" })
			public void Write_excel_common(String tableName) throws IOException
				{
				Write_exceltocompare_common we=new Write_exceltocompare_common();
				we.write_evidence(tableName);
				we.read_formatted_excel(tableName);
				//we.getList();
							}	
//==========================================Create object of util Write_exceltocompare_cit class and call methods===========================================
	@Test
	public void Write_excel_cit_util() throws IOException
	{
		Write_exceltocompare_cit_util we=new Write_exceltocompare_cit_util();
		we.write_evidence();
		we.read_formatted_excel();
		we.getList();
		}	
//==========================================Create object of utility Write_exceltocompare_baseline class and call methods===========================================
	@Test
	public void Write_excel_baseline_util() throws IOException
	{
		Write_exceltocompare_baseline_util we=new Write_exceltocompare_baseline_util();
		we.write_evidence();
		we.read_formatted_excel();
		we.getList();
	}	
//==========================================Create object of Compare_UIwithexcel class and call methods===========================================
	@Test
	public void compareUI_Excel()
	{
		App_logs.info("Compare data from UI with excel");
		Compare_UIwithexcel Ce=new Compare_UIwithexcel();
		Ce.compareUInExcel();
	}
	//==========================================Create object of Compare_UIwithexcel_common class and call methods===========================================
			@Test(dataProvider = "tabnameDP")
			public void compareUI_Excel_common(String tableName) throws IOException
				{
					Compare_UIwithexcel_common Ce=new Compare_UIwithexcel_common();
					Ce.compareUInExcel(tableName);
				}					
		
//==========================================Create object of Compare_UIwithexcel_cit class and call methods===========================================
	@Test
	public void compareUI_Excel_cit_util()
		{
			Compare_UIwithexcel_cit_util Ce=new Compare_UIwithexcel_cit_util();
			Ce.compareUInExcel();
			}
//==========================================Create object of utility Compare_UIwithexcel_baseline class and call methods===========================================
	@Test
	public void compareUI_Excel_baseline_util()
	{
		Compare_UIwithexcel_baseline_util Ce=new Compare_UIwithexcel_baseline_util();
		Ce.compareUInExcel();
		}							
			
//=======================================Close browser after test=============================================================
	@AfterTest
	public void close_browser()
	{
		App_logs.info("Closing browser");
		driver.quit();
	}
	
	public static WebDriver gerDriverDetails(String className){
		return driverObjMap.get(className);
	}
	public static HashMap<String, WebDriver> gerDriverObjMap(){
		return driverObjMap;
	}
	

	@BeforeSuite
	public void beforesuite() throws UnknownHostException
	{
		String username = System.getProperty("user.name");
		String OS=System.getProperty("os.name");
		String Hostname=InetAddress.getLocalHost().getHostName();
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.loadConfig((System.getProperty("user.dir") +"/extent-config.xml"));
		extent.setSystemInfo("OS", OS);
		extent.setSystemInfo("Host Name",Hostname);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", username);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Pharma Automation Report ");
		htmlReporter.config().setReportName("Pharma Testing Report ");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	@AfterSuite
	public void aftersuite()
	{
		extent.flush();
	}
	
}
