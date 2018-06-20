package POI_Readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import Test_cases.Evidence_Reference_Baseline;
import Test_cases.Evidence_Reference_Citation;

public class Write_exceltocompare_baseline_util {
	WebDriver driver;
	Evidence_Reference_Baseline Eb=new Evidence_Reference_Baseline(driver);
	Read_ClientExcel_Baseline_util Rbe=new Read_ClientExcel_Baseline_util();
	static ArrayList excelList = new ArrayList();
	ArrayList<String> columns = Eb.getheaderList();
	//===========================Write stored array columns data in a new spreadsheet====================================================
	public void write_evidence() throws IOException
	{
	  XSSFWorkbook workbook2 = new XSSFWorkbook();
	  XSSFSheet spreadsheet2 = workbook2.createSheet("test");
	  for (int i = 0; i < Read_ClientExcel_Baseline_util.arr[0].size(); i++) {
		XSSFRow row2 = spreadsheet2.createRow(i);
		for(int j=0;j<columns.size();j++)
		{
		row2.createCell(j).setCellValue(Read_ClientExcel_Baseline_util.arr[0].get(i));
		row2.createCell(1).setCellValue(Read_ClientExcel_Baseline_util.arr[1].get(i));
		row2.createCell(2).setCellValue(Read_ClientExcel_Baseline_util.arr[2].get(i));
		row2.createCell(3).setCellValue(Read_ClientExcel_Baseline_util.arr[3].get(i));
		row2.createCell(4).setCellValue(Read_ClientExcel_Baseline_util.arr[4].get(i));
		row2.createCell(5).setCellValue(Read_ClientExcel_Baseline_util.arr[5].get(i));
		}
		FileOutputStream out = new FileOutputStream(new File("C:/Users/mala.gupta/Desktop/test_excel_write_base.xlsx"));
		workbook2.write(out);
	  }
	}
	
	//================================Read formatted excel and store data in an arraylist======================================================
		 public void read_formatted_excel() throws IOException
		 {
			 File file=new File("C:/Users/mala.gupta/Desktop/test_excel_write_base.xlsx");	
			 FileInputStream fs=new FileInputStream(file);
			 XSSFWorkbook wb=new XSSFWorkbook(fs);
			 XSSFSheet sheet=wb.getSheet("test");
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
