package POI_Readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Object.Read_object;
import Test_cases.ReadUITabs;
import Utility.ExcelUtils;
import Utility.constants;

public class Read_ClientExcel_common {
	WebDriver driver;
	ReadUITabs Et=new ReadUITabs(driver);
	HashMap<String,ArrayList<String>> columns = Et.getheaderList();
	static ArrayList<String> arr[] =new ArrayList[12];
	 ArrayList<String> Headers = new ArrayList<String>();
	Read_object ro= new Read_object(constants.property_path);
	
	public void read_evidence_excel(String tableName) throws Exception
	 {
		ExcelUtils xlread =new ExcelUtils("C:/Users/mala.gupta/Desktop/NDMM-Epi.xlsx");
		String[] sheetname ={"Articles"} ;
		//int[] index = new int[Headers.size()];
		String value,flag,flagsheetname=null;
		int rowcount,colcount;
		for(int i=0;i<arr.length;i++){
	 		arr[i]=new ArrayList<String>();
	 	   }
		
		switch(tableName){
		case "Summary":
			//read summary grid
			sheetname = new String[] {"Articles"};
			flagsheetname="articleOnSummary";
			break;
		case "CitationDetails":
			sheetname = new String[] {"Articles"};
			flagsheetname="AticleOnCD";
					
			break;
		case "BaselineStudyPopulationDetails":
			sheetname = new String[] {"Articles","EpiDataBaselineStudy"};
			flagsheetname="ArticleonBS";
			break;
		}
	 
		
		for(int fi=0;fi<sheetname.length;fi++){
			int lastRow = xlread.getLastrow(sheetname[fi]) ;
			System.out.println("====Reading sheet==== "+ sheetname[fi]);
			for(int i=0;i<Headers.size();i++){
				for (int z=0;z<lastRow+1;z++){
					if(sheetname[fi]=="Articles"){
						if(z!=0){
							  flag=xlread.getCellData(sheetname[fi],flagsheetname , z);
							  if(flag.equalsIgnoreCase("true")){
								  value=xlread.getCellData(sheetname[fi],Headers.get(i),z);
								  if(value!=null){
									arr[i].add(value);
							        }
							      }
							   }
						else{
							value=xlread.getCellData(sheetname[fi],Headers.get(i),z);
							if(value!=null){
							arr[i].add(value);
							}
							}
					}
					else{
						value=xlread.getCellData(sheetname[fi],Headers.get(i),z);
						if(value!=null){
						arr[i].add(value);
						}
						}
					}
				}  
			}
           ////////print extracted values
             for(int i=0;i<arr.length;i++){
            	 System.out.println("Array values in tab | " + tableName + arr[i]);
             }
	   
	 
	 }
	
	public void write_evidence(String tableName) throws IOException
	{
	    //ExcelUtils xlwrite =new ExcelUtils("Testfiles/EPINDMM_Clientxl.xlsx");
	    
	   // xlwrite.setCellData(tableName, arr, Headers.size());
		File excelFile = new File("Testfiles/EPINDMM_Clientxl.xlsx");
		XSSFWorkbook wb = null;
		if(excelFile.exists()==false){
			//Creating a new file
			//FileOutputStream fos = new FileOutputStream(excelFile);
			wb = new XSSFWorkbook();	
	     	}
		else{
			FileInputStream fis = new FileInputStream(excelFile);
			wb = new XSSFWorkbook(fis);
		}
		XSSFSheet spreadsheet2 = wb.createSheet(tableName);
		for (int i = 0; i < arr[0].size(); i++) {
		XSSFRow row2 = spreadsheet2.createRow(i);
		for(int j=0;j<Headers.size();j++)
		{
		row2.createCell(j).setCellValue(arr[j].get(i));	
		}
		//FileOutputStream out = new FileOutputStream(new File("C:/Users/mala.gupta/Desktop/test_excel_write_base.xlsx"));
		FileOutputStream out = new FileOutputStream(excelFile);
		wb.write(out);
		out.flush();
		out.close();
	    }
	}
}
