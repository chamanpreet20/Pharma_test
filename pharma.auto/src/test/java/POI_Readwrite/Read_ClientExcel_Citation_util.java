package POI_Readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import Test_cases.Evidence_Reference_Citation;
import Utility.ExcelUtils;

public class Read_ClientExcel_Citation_util {
	WebDriver driver;
	Evidence_Reference_Citation Ec=new Evidence_Reference_Citation(driver);
	ArrayList<String> columns = Ec.getheaderList();
	
	static ArrayList<String> arr[] =new ArrayList[5];
	
	
//=========================Read data from client excel for columns you want to write to separate excel=========================================
	 public void read_evidence_excel() throws IOException,NullPointerException
	 {
		    ExcelUtils xlread =new ExcelUtils("C:/Users/mala.gupta/Desktop/NDMM-Epi.xlsx");
		    String[] sheetname= {"Articles"};
			
			//ArrayList<String> columns = Ec.getheaderList();
			//Iterator<String> itr= columns.iterator();
			int[] index = new int[columns.size()];
			String value,flag;
			
			for(int i=0;i<arr.length;i++){
		 		arr[i]=new ArrayList<String>();
		 	   }
			for(int fi=0;fi<sheetname.length;fi++){
				int lastRow = xlread.getLastrow(sheetname[fi]) ;
				for(int i=0;i<columns.size();i++){
				for (int z=0;z<lastRow+1;z++){
					System.out.println("Reading Sheet  | " + sheetname[fi]);
					System.out.println("Reading column  |  " + columns.get(i));
					if(z!=0){
					  flag=xlread.getCellData(sheetname[fi],"AticleOnCD" , z);
					  if(flag.equalsIgnoreCase("true")){
						  value=xlread.getCellData(sheetname[fi],columns.get(i),z);
						  if(value!=null){
							arr[i].add(value);
					    }
					}
					}
					else{
					value=xlread.getCellData(sheetname[fi],columns.get(i),z);
					if(value!=null){
					arr[i].add(value);
					}
					}
				}
					
				}
				}
			
						
		     
				System.out.println("key author array is: " +arr[0] +"\n");
				System.out.println("JournalDetails is: " +arr[1] +"\n");
				System.out.println("PublicationYear array is: " +arr[2] +"\n");
				System.out.println("Authors array is: " +arr[3] +"\n");
				System.out.println("LinkURL array is: " +arr[4] +"\n");
		     
	      
		       }
}
