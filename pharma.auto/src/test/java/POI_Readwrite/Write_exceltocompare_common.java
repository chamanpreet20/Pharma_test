package POI_Readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import POI_Readwrite.Read_ClientExcel;
import Test_cases.Evidence_Reference_Citation;
import Test_cases.ReadUITabs;

public class Write_exceltocompare_common {
	WebDriver driver;
	//Evidence_Reference_Citation Ec=new Evidence_Reference_Citation(driver);
	ReadUITabs Et=new ReadUITabs(driver);
	HashMap<String,ArrayList<String>> columns = Et.getheaderList();
	Read_ClientExcel_Citation_util Rce=new Read_ClientExcel_Citation_util();
	static ArrayList excelList = new ArrayList();
	ArrayList<String> Headers = new ArrayList<String>();
	//ArrayList<String> columns = Ec.getheaderList();
	 
//===========================Write stored array columns data in a new spreadsheet====================================================
	public void write_evidence(String tableName) throws IOException
	{
		//ExcelUtils xlwrite =new ExcelUtils("Testfiles/EPINDMM_Clientxl.xlsx");
	    
		   // xlwrite.setCellData(tableName, arr, Headers.size());
			File excelFile = new File("Testfiles/Testing1.xlsx");
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
			if(columns.containsKey(tableName)){
				Headers=columns.get(tableName);
			}
			XSSFSheet spreadsheet2 = wb.createSheet(tableName);
			for (int i = 0; i < Read_ClientExcel_common.arr[0].size(); i++) {
			XSSFRow row2 = spreadsheet2.createRow(i);
			for(int j=0;j<Headers.size();j++)
			{
			row2.createCell(j).setCellValue(Read_ClientExcel_common.arr[j].get(i));	
			
			}
			//FileOutputStream out = new FileOutputStream(new File("C:/Users/mala.gupta/Desktop/test_excel_write_base.xlsx"));
			FileOutputStream out = new FileOutputStream(excelFile);
			wb.write(out);
			out.flush();
			out.close();
		    }
	
	}
//================================Read formatted excel and store data in an arraylist======================================================
	 public void read_formatted_excel(String tableName) throws IOException
	 {
		 //File file=new File("C:/Users/chaman.preet/Desktop/test_excel_write.xlsx");
		 //File file=new File("C:/Users/mala.gupta/Desktop/test_excel_write_cit.xlsx");	
		 File file = new File("Testfiles/Testing1.xlsx");
		 FileInputStream fs=new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(fs);
			XSSFSheet sheet=wb.getSheet(tableName);
			int exrow_count = sheet.getLastRowNum()-sheet.getFirstRowNum();
			for(int i=1;i<exrow_count+1;i++)
			{
				XSSFRow row = sheet.getRow(i);
				for(int j=0;j<row.getLastCellNum();j++)
				{
					XSSFCell cell=row.getCell(j);
					
				//System.out.println("cell type is :" +sheet.getRow(1).getCell(26).getCellType());
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					//System.out.print(cell.getStringCellValue() + "|| ");
						excelList.add(cell.getStringCellValue().trim());
						
					}
					else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
						//System.out.print(cell.getNumericCellValue() + "|| ");
						excelList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
					}
					else if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
						//System.out.print(cell.getBooleanCellValue() + "|| ");
						excelList.add(cell.getBooleanCellValue());}
					else if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
					//System.out.print( "||");
					
						}
				}
			}
			wb.close();
	 }
	 
//================================Method to return arraylist==================================================================================
				public ArrayList getList() {
					//System.out.println("In Return function excel list data-- " +excelList);
				    return excelList;
				}
}
