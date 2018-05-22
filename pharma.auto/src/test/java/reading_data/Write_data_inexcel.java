package reading_data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Write_data_inexcel {
	static String[][] tableData;
	static int index1;
	static XSSFCell cell;
	XSSFRow row;
	XSSFSheet sheet;
	String celldata;
	XSSFWorkbook wb;
	FileOutputStream fos=null;
	ArrayList<String> writeList = new ArrayList<String>();
	@Test
	public void readexcel_col(String filePath,String fileName,String sheetName) throws IOException
	{
		File file=new File(filePath+"\\" +fileName);
		FileInputStream fs=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fs);
		XSSFSheet sheet=wb.getSheet(sheetName);
		int exrow_count = sheet.getLastRowNum()-sheet.getFirstRowNum();
		System.out.println("Row count of excel= " +(exrow_count+1));
		
		XSSFRow row = sheet.getRow(0);
			String[] Headers = new String[row.getLastCellNum()];
			DataFormatter format = new DataFormatter();
			
			for(int j=0;j<row.getLastCellNum();j++)
			{
				XSSFCell cell=row.getCell(j);
				if(cell.getStringCellValue().trim().equalsIgnoreCase("KeyAuthor"))
				{
					index1 = cell.getColumnIndex();
					//System.out.println(index1);
				}
				else
				{
					System.out.println("Keyauthor not present");
				}
			}
					for(int z=0;z<exrow_count;z++)
					{
						for(int x=0;x<=index1;x++)
						{
						XSSFCell getcell = sheet.getRow(z).getCell(x);
						//System.out.println(getcell);
						getcell.setCellType(cell.CELL_TYPE_STRING);
						//String value = format.formatCellValue(cell);
						//String[][] tableData = new String[z][x];
					//	cell.setCellType(cell.CELL_TYPE_STRING);
						celldata = getcell.getStringCellValue();
						writeList.add(celldata);
						//System.out.println("Inside loop" +celldata);
					}
						
					//index = cell.getColumnIndex();
				}
					fs.close();
					System.out.println("Array is :" +writeList);
	}
			public void setdata() throws IOException
			{
				FileInputStream fiis=new FileInputStream("C:\\Users\\chaman.preet\\Desktop\\NDMM-Epi .xlsx");
				XSSFWorkbook workb= new XSSFWorkbook(fiis);
				readexcel_col("C:\\Users\\chaman.preet\\Desktop", "NDMM-Epi .xlsx", "Test_sheet");
				//fos=new FileOutputStream(new File("C:\\Users\\chaman.preet\\Desktop\\write2.xlsx"));
			//	row=sheet.getRow(1);
			//	wb.createSheet();
			Sheet sh = workb.getSheet("Sheet1");
	            row = (XSSFRow) sh.getRow(0);
			if(row==null)
				{
					row=(XSSFRow) sh.createRow(0);
				}
			  for(int y = 0; y < writeList.size(); y++){

		//		cell=row.getCell(index);
				if(cell==null)
					cell=row.createCell(y);
	//			System.out.println(writeList.get(y).toString());
			cell.setCellValue(writeList.get(y).toString());
			  }
				//cell.setCellType(cell.CELL_TYPE_STRING);
				/*for(int z=0;z<exrow_count+1;z++)
				{
				 String[][] tableData = new String[1][exrow_count+1];
			tableData[1][z]=sheet.getRow(z).getCell(index).getStringCellValue();
				System.out.println(tableData[1][z]);
				cell.setCellValue(tableData[1][z]);}*/
			//	cell.setCellValue("test");
				fos=new FileOutputStream("C:\\Users\\chaman.preet\\Desktop\\writing.xlsx");
				workb.write(fos);
				fos.close(); }}
		/*                 cell=row.getCell(j);
				Headers[j] = sheet.getRow(0).getCell(j).getStringCellValue();
				System.out.println("Header values are " +Headers[j]);
				if(Headers[j].equalsIgnoreCase("KeyAuthor")){
					int index = cell.getColumnIndex();*/
					//System.out.println("Index is " +index);}
				
			/*		for(int z=0;z<exrow_count+1;z++)
					{
					 String[][] tableData = new String[1][exrow_count+1];;
				tableData[1][z]=sheet.getRow(z).getCell(index).getStringCellValue();
					System.out.println(tableData[1][z]);
					//writeList.add(sheet.getRow(z).getCell(index).getStringCellValue();}}
			//System.out.println("cell type is :" +sheet.getRow(1).getCell(26).getCellType());
		
			
			//System.out.println();
				//cell.setCellValue(writeList);
			cell.setCellValue(tableData[1][z]);}
			//	System.out.println(cell);
				//String path="C:\\Users\\chaman.preet\\Desktop\\pharma.xlsx";                
				FileOutputStream ofs=new FileOutputStream(new File("C:\\Users\\chaman.preet\\Desktop\\pharma2.xlsx"));
				wb.write(ofs);
					}
			}
		//	}
		//	}
				//wb.close();
	//	}
		//		 fs.close();
		//	excelList.toString();
		
		//System.out.println(excelList);

	
	/*public void writedata()
	{ 
		cell.setCellValue("Update");
		System.out.println(cell);
		
		FileOutputStream ofs=new FileOutputStream(new File("C:\\Users\\chaman.preet\\Desktop\\pharma-auto.xlsx"));
		work.write(ofs);
		fs.close();
		work.close();	
	}*/

