package POI_Readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import Test_cases.Evidence_summary;
import Utility.ExcelUtils;


public class Read_ClientExcel_util {
	WebDriver driver;
	Evidence_summary Es =new Evidence_summary(driver);
	ArrayList<String> columns = Es.getheaderList();
	int index1,index2,index3,index4,index5,index6,index7,index8,index9;
	static int input=9;
	//String[] arrr1=new String[144];
	/*static ArrayList<String> lists[]=new ArrayList[input];
	for(int i=0;i<input;i++){
		   lists[i]=new ArrayList<String>();
		}*/
	static ArrayList<String> arr[] =new ArrayList[9];
	/*static ArrayList<String> arr1 = new ArrayList<String>();
	static ArrayList<String> arr2 = new ArrayList<String>();
	static ArrayList<String> arr3 = new ArrayList<String>();
	static ArrayList<String> arr4 = new ArrayList<String>();
	static ArrayList<String> arr5 = new ArrayList<String>();
	static ArrayList<String> arr6 = new ArrayList<String>();
	static ArrayList<String> arr7 = new ArrayList<String>();
	static ArrayList<String> arr8 = new ArrayList<String>();
	static ArrayList<String> arr9 = new ArrayList<String>();*/

//=========================Read data from client excel for columns you want to write to seperate excel=========================================
	 public void read_evidence_excel() throws IOException
	 {
		 //File f = new File("C:\\Users\\chaman.preet\\Desktop\\NDMM-Epi.xlsx");
		 ExcelUtils xlread =new ExcelUtils("C:/Users/mala.gupta/Desktop/NDMM-Epi.xlsx");
		 String[] sheetname= {"Articles"};
		 String value,flag;
		 for(int i=0;i<arr.length;i++){
		 		arr[i]=new ArrayList<String>();
		 	   }
		 for(int fi=0;fi<sheetname.length;fi++){
				int lastRow = xlread.getLastrow(sheetname[fi]) ;
				for(int i=0;i<columns.size();i++){
				for (int z=0;z<lastRow+1;z++){
					if(z!=0){
						flag=xlread.getCellData(sheetname[fi], "articleOnSummary", z);
						if(flag=="true"){
							 value=xlread.getCellData(sheetname[fi],columns.get(i),z);
							 if(columns.get(i)=="StudyDesign"){
								 arr[i].add("Not reported");
								  }
							 else{
							  if(value!=null){
								arr[i].add(value);
						          }}		
					}
					else{
					value=xlread.getCellData(sheetname[fi],columns.get(i),z);
					if(value!=null){
					arr[i].add(value);
					}
					}
				}
					}
				}}
			
		 	System.out.println("key author array is: " +arr[0] +"\n");
			System.out.println("Study title array is: " +arr[1] +"\n");
			System.out.println("Study Population array is: " +arr[2] +"\n");
			System.out.println("Incidence array is: " +arr[3] +"\n");
			System.out.println("Prevelance array is: " +arr[4] +"\n");
			System.out.println("Mortality array is: " +arr[5] +"\n");
			System.out.println("standardisedRateRatio array is: " +arr[6] +"\n");
			System.out.println("Study duration array is: " +arr[7] +"\n");
			System.out.println("Comments array is: " +arr[8] +"\n");
	 }
}

	 

