package Test_cases;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import POI_Readwrite.Write_exceltocompare_baseline;
import POI_Readwrite.Write_exceltocompare_baseline_util;
import POI_Readwrite.Write_exceltocompare_cit;

public class Compare_UIwithexcel_baseline_util {
	WebDriver driver;
	Write_exceltocompare_baseline_util Rcexcel_baseline=new Write_exceltocompare_baseline_util();
	Evidence_Reference_Baseline Ec=new Evidence_Reference_Baseline(driver);
//====================================================Get UI and excel array list and compare==========================================
		public void compareUInExcel()
		{
			//ArrayList exlist = Rcexcel.getList();
			ArrayList exlist = Rcexcel_baseline.getList();
			//int array1Size = Es.UIList.size();
			int array1Size = Ec.UIList.size();
			int array2Size = exlist.size();
			
			System.out.println("UI size is: " +array1Size+ " ,Excel size is: " +array2Size); 
			
			if (Ec.UIList.size() > exlist.size())
			{
			    int k = 0;
			    
			    for (int x = 0; x < exlist.size(); x++)
			    {
			    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s", "").toLowerCase();
			    	String UIwospaces = ((String)Ec.UIList.get(x)).replaceAll("\\s", "").toLowerCase();
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
			    for (int x = k; x < Ec.UIList.size(); x++)
			    {
			    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s", "").toLowerCase();
			    	String UIwospaces = ((String)Ec.UIList.get(x)).replaceAll("\\s", "").toLowerCase();
			    	System.out.println("UI list has: " +UIwospaces);
			       // System.out.println((String)Es.UIList.get(x));
			    }
			}
			else
			{
			   int k = 0;
			    for (int x = 0; x < Ec.UIList.size(); x++)
			    {
			    	//System.out.println("UI String value at k location" + Ec.UIList.get(x));
			    	//System.out.println("excel String value at k location" + exlist.get(x));
			    	String Excelwospaces = (exlist.get(x)).toString().replaceAll("\\s", "").toLowerCase();
			    	String UIwospaces = ((String)Ec.UIList.get(x)).replaceAll("\\s", "").toLowerCase();
			    	//System.out.println("with spaces: " + Ec.UIList.get(x));
			    	
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
			    	//System.out.println("UI String value at k location" + Ec.UIList.get(x));
			    	//System.out.println("excel String value at k location" + exlist.get(x));
			    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s ", "").toLowerCase();
			    	String UIwospaces = ((String)Ec.UIList.get(x)).replaceAll("\\s ", "").toLowerCase();
			    	System.out.println("Excel list has: " +Excelwospaces);
			    //   System.out.println(exlist.get(x));
			    }
			}
		}

}
