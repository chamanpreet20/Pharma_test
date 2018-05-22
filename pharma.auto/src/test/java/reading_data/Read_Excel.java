package reading_data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class Read_Excel {
	/*String filePath="C:\\Users\\chaman.preet\\Desktop";
	String fileName="NDMM-Epi .xlsx";
	String sheetName="Articles";*/
	ArrayList excelList = new ArrayList();
@Test
public void readexcel(String filePath,String fileName,String sheetName) throws IOException
{
	
	File file=new File(filePath+"\\" +fileName);
	FileInputStream fs=new FileInputStream(file);
	XSSFWorkbook wb=new XSSFWorkbook(fs);
	XSSFSheet sheet=wb.getSheet(sheetName);
	int exrow_count = sheet.getLastRowNum()-sheet.getFirstRowNum();
	System.out.println("Row count of excel=" +(exrow_count+1));
	for(int i=1;i<exrow_count+1;i++)
	{
		XSSFRow row = sheet.getRow(i);
		for(int j=0;j<row.getLastCellNum();j++)
		{
			XSSFCell cell=row.getCell(j);
			
		//System.out.println("cell type is :" +sheet.getRow(1).getCell(26).getCellType());
			if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
			//	System.out.print(cell.getStringCellValue() + "|| ");
				excelList.add(cell.getStringCellValue().trim());
				
			}
			else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			//	System.out.print(cell.getNumericCellValue() + "|| ");
				excelList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
			}
			else if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			//	System.out.print(cell.getBooleanCellValue() + "|| ");
				excelList.add(cell.getBooleanCellValue());}
			else if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
				//System.out.print( "||");
				}
		
		/*	else if (cell.getCellType() == XSSFCell.CELL_TYPE_ERROR) {
				//System.out.print( "error ||");}
			else if(cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA)
			{
				System.out.println("formula");
			}
			//System.out.println(cell.getStringCellValue()+ "|| ");
		*/
		//System.out.println();
		//cell.setCellValue(tableData[j][i]);
		//	System.out.println(cell);
			//String path="C:\\Users\\chaman.preet\\Desktop\\pharma.xlsx";                
			FileOutputStream ofs=new FileOutputStream(new File("C:\\Users\\chaman.preet\\Desktop\\pharma.xlsx"));
			wb.write(ofs);
                 
		}
		}
			//wb.close();
			 fs.close();
	//	excelList.toString();
	
	//System.out.println(excelList);
		}

public ArrayList getList() {
    return excelList;
}

/*public void writedata()
{ 
	cell.setCellValue("Update");
	System.out.println(cell);
	
	FileOutputStream ofs=new FileOutputStream(new File("C:\\Users\\chaman.preet\\Desktop\\pharma-auto.xlsx"));
	work.write(ofs);
	fs.close();
	work.close();	
}*/

}

