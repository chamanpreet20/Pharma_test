package Test_cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import org.openqa.selenium.WebDriver;


import Utility.ExcelUtils;


public class Compare_UIwithexcel_common {
		WebDriver driver;
	
	 ArrayList excelList = new ArrayList();
	ReadUITabs Et=new ReadUITabs(driver);
	HashMap<String,ArrayList<String>> columns = Et.getheaderList();
	
//====================================================Get UI and excel array list and compare==========================================
	public void compareUInExcel(String tableName) throws IOException
	{
		
		ArrayList exlist,UIlist=null;
		
		exlist=read_formatted_excel(tableName);
		columns=Et.UIhash;
		if(columns.containsKey(tableName)){
			UIlist=columns.get(tableName);
			System.out.println("Table   |  " + tableName + "data  |  " + UIlist);
		}
		
		int array1Size = UIlist.size();
		int array2Size = exlist.size();
		
		System.out.println("UI size is: " +array1Size+ " ,Excel size is: " +array2Size); 
		
		if (UIlist.size() > exlist.size())
		{
		    int k = 0;
		    
		    for (int x = 0; x < exlist.size(); x++)
		    {
		    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s", "").toLowerCase();
		    	String UIwospaces = ((String)UIlist.get(x)).replaceAll("\\s", "").toLowerCase();
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
		    for (int x = k; x < UIlist.size(); x++)
		    {
		    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s", "").toLowerCase();
		    	String UIwospaces = ((String)UIlist.get(x)).replaceAll("\\s", "").toLowerCase();
		    	System.out.println("UI list has: " +UIwospaces);
		       // System.out.println((String)Es.UIList.get(x));
		    }
		}
		else
		{
		   int k = 0;
		    for (int x = 0; x < UIlist.size(); x++)
		    {
		    	
		    	String Excelwospaces = (exlist.get(x)).toString().replaceAll("\\s", "").toLowerCase();
		    	String UIwospaces = ((String)UIlist.get(x)).replaceAll("\\s", "").toLowerCase();
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
		    	
		    	String Excelwospaces = ((String)exlist.get(x)).replaceAll("\\s ", "").toLowerCase();
		    	String UIwospaces = ((String)UIlist.get(x)).replaceAll("\\s ", "").toLowerCase();
		    	System.out.println("Excel list has: " +Excelwospaces);
		    //   System.out.println(exlist.get(x));
		    }
		}
	}
	
	//================================Read formatted excel and store data in an arraylist======================================================
		 public ArrayList read_formatted_excel(String tableName) throws IOException
		 {
			 ExcelUtils xlread =new ExcelUtils("Testfiles/EPINDMM_Clientxl.xlsx");
			 int exrow_count = xlread.getLastrow(tableName)-xlread.getFirstrow(tableName);		 
			 String value;
			 
			 for(int i=1;i<exrow_count+1;i++)
				{
				 for(int j=0;j<xlread.getLastcellnum(tableName, i);j++){
					 value=xlread.getCellData(tableName, j, i);
					 excelList.add(value);
				 }
				}
			 	 			 
			   return excelList;
		 }
	
	
}
