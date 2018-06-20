package POI_Readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import Test_cases.Evidence_Reference_Baseline;
import Test_cases.Evidence_Reference_Citation;
import Utility.ExcelUtils;

public class Read_ClientExcel_Baseline_util {
	WebDriver driver;
	Evidence_Reference_Baseline Eb=new Evidence_Reference_Baseline(driver);
	ArrayList<String> columns = Eb.getheaderList();
	
	static ArrayList<String> arr[] =new ArrayList[6];
	
	
//=========================Read data from client excel for columns you want to write to separate excel=========================================
	 public void read_evidence_excel() throws IOException
	 {
		 	ExcelUtils xlread =new ExcelUtils("C:/Users/mala.gupta/Desktop/NDMM-Epi.xlsx");
		    String[] sheetname= {"Articles","EpiDataBaselineStudy"};
		    int[] index = new int[columns.size()];
			String value,flag;
						
			//------Initialzing arraylist---------
			for(int i=0;i<arr.length;i++){
		 		arr[i]=new ArrayList<String>();
		 	   }
			
			
			//************Read column from EpiDataBaselineStudy**********
			
			for(int fi=0;fi<sheetname.length;fi++){
				int lastRow = xlread.getLastrow(sheetname[fi]) ;
				for(int i=0;i<columns.size();i++){
					for (int z=0;z<lastRow+1;z++){
						if(sheetname[fi]=="Articles"){
							if(z!=0){
								  flag=xlread.getCellData(sheetname[fi],"ArticleonBS" , z);
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
			System.out.println("StudyPopulation is: " +arr[1] +"\n");
			System.out.println("Characteristics array is: " +arr[2] +"\n");
			System.out.println("MeanAge array is: " +arr[3] +"\n");
			System.out.println("Male array is: " +arr[4] +"\n");
			System.out.println("Female array is: " +arr[5] +"\n");
			
	 }

}

