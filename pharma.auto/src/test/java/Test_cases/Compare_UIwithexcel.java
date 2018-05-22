package Test_cases;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import POI_Readwrite.Read_ClientExcel;
import Test_cases.Evidence_summary;
import POI_Readwrite.Write_exceltocompare;

public class Compare_UIwithexcel {
		WebDriver driver;
	//Read_ClientExcel Rcexcel=new Read_ClientExcel();
	Write_exceltocompare Rcexcel=new Write_exceltocompare();
	Evidence_summary Es=new Evidence_summary(driver);
	
//====================================================Get UI and excel array list and compare==========================================
	public void compareUInExcel()
	{
			ArrayList exlist = Rcexcel.getList();
		int array1Size = Es.UIList.size();
		int array2Size = exlist.size();
		
		System.out.println("UI size is: " +array1Size+ " ,Excel size is: " +array2Size); 
		
		if (Es.UIList.size() > exlist.size())
		{
		    int k = 0;
		    
		    for (int x = 0; x < exlist.size(); x++)
		    {
		    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s", "").toLowerCase();
		    	String UIwospaces = ((String)Es.UIList.get(x)).replaceAll("\\s", "").toLowerCase();
		    //	if (!((String)Es.UIList.get(x)).replaceAll(" ", "").equalsIgnoreCase(((String)exlist.get(x)).replaceAll(" ", "")))
		    	if (!UIwospaces.equalsIgnoreCase(Excelwospaces))
		    	
		   //     if (!((String)Es.UIList.get(x)).equals((String)exlist.get(x)))
		        {
		    		System.out.println("Excel list has: " +Excelwospaces);
		         //   System.out.println((String)exlist.get(x));
		        }
		        k = x;
		    }
		    k++;
		    for (int x = k; x < Es.UIList.size(); x++)
		    {
		    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s", "").toLowerCase();
		    	String UIwospaces = ((String)Es.UIList.get(x)).replaceAll("\\s", "").toLowerCase();
		    	System.out.println("UI list has: " +UIwospaces);
		       // System.out.println((String)Es.UIList.get(x));
		    }
		}
		else
		{
		   int k = 0;
		    for (int x = 0; x < Es.UIList.size(); x++)
		    {
		    	String Excelwospaces = (exlist.get(x)).toString().replaceAll("\\s", "").toLowerCase();
		    	String UIwospaces = ((String)Es.UIList.get(x)).replaceAll("\\s", "").toLowerCase();
		    	
		      if (!UIwospaces.equalsIgnoreCase(Excelwospaces))
		   // 	if (!(Es.UIList.get(x)).equals(exlist.get(x)))
		        {
		    	  System.out.println("Excel list has: " + Excelwospaces+ " >>>>>>> while UI list has: " +UIwospaces+  "\n");
		         //   System.out.println("Excel list has: " +(exlist.get(x))+ " >>>>>>> while UI list has: " +((String)Es.UIList.get(x)));
		        }
		        k = x;
		    }
		    k++;
		    for (int x = k; x < exlist.size(); x++)
		    {
		    	String Excelwospaces = ((String)exlist.get(x)).replaceAll(" ", "").toLowerCase();
		    	String UIwospaces = ((String)Es.UIList.get(x)).replaceAll(" ", "").toLowerCase();
		    	System.out.println("Excel list has: " +Excelwospaces);
		    //   System.out.println(exlist.get(x));
		    }
		}
	}
}
