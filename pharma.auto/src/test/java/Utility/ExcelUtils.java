package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public FileInputStream fis = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    DataFormatter format = new DataFormatter();
	
    
    public ExcelUtils(String xlFilePath) throws IOException{
    	fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    public int getLastrow(String sheetname){
    	int lastrow;
    	lastrow=workbook.getSheet(sheetname).getLastRowNum();
    	return lastrow;
    	
    }
    
    public String getCellData(String sheetName,String colName, int rowNum){
    	try{
    	int col_num=-1;
    	sheet=workbook.getSheet(sheetName);
    	FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
    	row=sheet.getRow(0);
    	String celldata=null;
    	Hyperlink linkURL;
    	//System.out.println("Cell count || " + row.getLastCellNum());
    	for(int i = 0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                col_num = i;
        }
    	
    	row = sheet.getRow(rowNum);
        cell = row.getCell(col_num);
        
        switch(cell.getCellType()){
	        case Cell.CELL_TYPE_STRING:
	        	linkURL=cell.getHyperlink();
	        	if(linkURL!=null){
	        		celldata=linkURL.getAddress();
	        	}
	        	else{        	
	        		celldata=cell.getStringCellValue();
	        	}
	        	break;
	        case Cell.CELL_TYPE_FORMULA:
	        	celldata=format.formatCellValue(cell,evaluator);
	        	break;
	        case Cell.CELL_TYPE_NUMERIC:
	        	celldata=format.formatCellValue(cell);
	        	break;
	        case Cell.CELL_TYPE_BOOLEAN:
	        	celldata=String.valueOf(cell.getBooleanCellValue());
	        	break;
	        case Cell.CELL_TYPE_BLANK:
	        	celldata="";
	        	break;
        }
        System.out.println("value in getcelldata  |  " + celldata);
        return celldata;
    	       	    
         }
    	catch(Exception e){
    		System.out.println("Column  | " + colName + "not exists in sheet:  " + sheetName);
    		return null;
    	}
    }     
    
}
