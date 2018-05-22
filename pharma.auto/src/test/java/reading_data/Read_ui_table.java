package reading_data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.poi.xssf.usermodel.*;

import reading_data.Read_Excel;

public class Read_ui_table {
	 SoftAssert softAssert = new SoftAssert();
	 
	@Test
public void comparison() throws IOException
{
//	Read_Excel ex=new Read_Excel();
//	ex.readexcel("C:\\Users\\chaman.preet\\Desktop", "NDMM-Epi .xlsx", "Sheet1");
//ArrayList exlist = ex.getList();
	
	Write_data_inexcel we=new Write_data_inexcel();
	we.setdata();
	//we.readexcel_col("C:\\Users\\chaman.preet\\Desktop", "NDMM-Epi .xlsx", "Test_sheet");
	
	System.out.println("----------------------------------------------------------");
	System.setProperty("webdriver.chrome.driver","C:\\Users\\chaman.preet\\Documents\\Desktop content\\Selenium jars\\ChromeDriver\\chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://evs01tst05/Pharmafusion/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.id("Email")).sendKeys("mala.gupta@evalueserve.com");
	driver.findElement(By.id("Password")).sendKeys("evs1234$");
	driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	driver.findElement(By.xpath("//a[text()=' Epidemiology Research']")).click();
WebDriverWait wait = new WebDriverWait(driver, 10);
	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Evidence Table']")));
	driver.findElement(By.xpath("//a[text()='Evidence Table']")).click();
		
	int rowcount = driver.findElements(By.xpath("//*[@id='datatableDiv']/tbody/tr")).size();
		System.out.println("Row count is :" +rowcount);
		int colcount = driver.findElements(By.xpath("//*[@id='datatableDiv']/tbody/tr[1]/td")).size();
		System.out.println("Col count is :" +colcount);
		ArrayList UIList = new ArrayList();
	for(int i=1;i<=rowcount;i++)
	{
		for(int j=1;j<=colcount;j++)
		{
String table_data = driver.findElement(By.xpath("//*[@id='datatableDiv']/tbody/tr[" +i+ "]/td[" +j+ "]")).getText();
	table_data.trim();
UIList.add(table_data);
//String second_cell = driver.findElement(By.xpath("//*[@id='datatableDiv']/tbody/tr[1]/td[2]")).getText();
	
	}
	}
	//System.out.println(UIList +" ");
	driver.close();
	
	//----------- Use verify for match and mismatch text-------------
	
/*	try
	{
		for(int x=0;x<=UIList.size();x++)
		{
		if((exlist.get(x)).equals(UIList.get(x)))
		{
			System.out.println("Matched");
		}
		else
		{System.out.println("Not matched: Excel has" +(exlist.get(x))+ "UI has: " +(UIList.get(x)));
		}
		}
	}
		catch(Throwable e){
		    System.err.println("Lists are not equal. "+e.getMessage());}*/
	
	//------------- Get Yes, No for match and No match----------------
	
	/* ArrayList<String> al3= new ArrayList<String>();
     for (Object temp : UIList)
         al3.add(exlist.contains(temp) ? "Yes" : "No");
     System.out.println(al3);*/

	
	//----------- Use assertion for getting message for first mismatch----------
	
	/* softAssert.assertEquals(exlist, UIList);
	 softAssert.assertAll();
		System.out.println(" Equal lists ");
		assertEquals(exlist,UIList,java.lang.String message);
		Assert.assertNotEquals(exlist,UIList," There is mismatch ");
	
	catch(Throwable e){
	    System.err.println("Lists are not equal. " );
		
	}*/
	
	
	//------------ removing spaces and convert lowercase to compare UI List and Excel List
	
/*	int array1Size = UIList.size();
	int array2Size = exlist.size();
	
	System.out.println("UI size is: " +array1Size+ " ,Excel size is: " +array2Size); 
	
	if (UIList.size() > exlist.size())
	{
	    int k = 0;
	    
	    for (int x = 0; x < exlist.size(); x++)
	    {
	    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s", "").toLowerCase();
	    	String UIwospaces = ((String)UIList.get(x)).replaceAll("\\s", "").toLowerCase();
	    //	if (!((String)UIList.get(x)).replaceAll(" ", "").equalsIgnoreCase(((String)exlist.get(x)).replaceAll(" ", "")))
	    	if (!UIwospaces.equalsIgnoreCase(Excelwospaces))
	    	
	   //     if (!((String)UIList.get(x)).equals((String)exlist.get(x)))
	        {
	    		System.out.println(Excelwospaces);
	         //   System.out.println((String)exlist.get(x));
	        }
	        k = x;
	    }
	    k++;
	    for (int x = k; x < UIList.size(); x++)
	    {
	    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s", "");
	    	String UIwospaces = ((String)UIList.get(x)).replaceAll("\\s", "");
	    	System.out.println(UIwospaces);
	       // System.out.println((String)UIList.get(x));
	    }
	}
	else
	{
	   int k = 0;
	    for (int x = 0; x < UIList.size(); x++)
	    {
	    	String Excelwospaces = (exlist.get(x)).toString().replaceAll("\\s", "").toLowerCase();
	    	String UIwospaces = ((String)UIList.get(x)).replaceAll("\\s", "").toLowerCase();
	    	
	      if (!UIwospaces.equalsIgnoreCase(Excelwospaces))
	   // 	if (!(UIList.get(x)).equals(exlist.get(x)))
	        {
	    	  System.out.println("Excel list has: " + Excelwospaces+ " >>>>>>> while UI list has: " +UIwospaces+  "\n");
	         //   System.out.println("Excel list has: " +(exlist.get(x))+ " >>>>>>> while UI list has: " +((String)UIList.get(x)));
	        }
	        k = x;
	    }
	    k++;
	    for (int x = k; x < exlist.size(); x++)
	    {
	    	String Excelwospaces = ((String)exlist.get(x)).replaceAll(" ", "");
	    	String UIwospaces = ((String)UIList.get(x)).replaceAll(" ", "");
	    	System.out.println(Excelwospaces);
	    //   System.out.println(exlist.get(x));
	    }
	}*/
	}
}

