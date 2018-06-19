package Utility;

import java.io.File;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import super_package.Actionclass;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Listener_test extends Actionclass implements ITestListener {
	
	private Map<Object, String> sysprop;
	public static ExtentTest childTest;
	String methodname;
	String report;

	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		methodname= result.getName();
		report=methodname;
		test = extent.createTest(methodname,"Test Case: "+methodname+ " Running on Browser: Chrome" );
		System.out.println(result.getName()+ " has executed successfully*****");
		App_logs.info("\n" +result.getName()+ " is PASS");
		test.pass(MarkupHelper.createLabel(report+ " Test Step PASSED", ExtentColor.GREEN));
			}
		

	@Override
	public void onTestFailure(ITestResult result){
		methodname= result.getName();
		report=methodname;
		test = extent.createTest(methodname,"Test Case: "+methodname+ " Running on Browser: Chrome" );
		test.fail(MarkupHelper.createLabel(report+" Test Step failed", ExtentColor.RED));
		
				String screenShotPath = null;
				try {
					screenShotPath = capture(driver, methodname);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				test.addScreenCaptureFromPath(screenShotPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		
        System.out.println("****Error***"+result.getName()+ " has failed*****");
        Throwable cause = result.getThrowable();
        if (null != cause) {
	    App_logs.error("\n\n" +result.getName()+ "*****FAIL - " +cause.getMessage());
	    }
      String methodname=result.getName().toString().trim();
      takescreenshot(methodname,constants.screenshot_filepath);
     
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		App_logs.info(result.getName()+ "is Skipped");
		methodname= result.getName();
		report=methodname;
		test = extent.createTest(methodname,"Test Case: "+methodname+ " Running on Browser: Chrome" );
		test.skip(MarkupHelper.createLabel(report+" Test Step SKIPPED", ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void takescreenshot(String methodname, String screenshot_filepath)
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(src,new File(screenshot_filepath+methodname+".png"));
			System.out.println("Screenshot is placed in " +screenshot_filepath+ "***");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
		
		public static String capture(WebDriver driver,String screenShotName) throws IOException 
	    {
	        TakesScreenshot ts = (TakesScreenshot)driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String dest = System.getProperty("user.dir") +"\\ErrorScreenshots\\"+screenShotName+".png";
	        File destination = new File(dest);
	        FileUtils.copyFile(source, destination);        
	                     
	        return dest;
	    }
		
		
		}

