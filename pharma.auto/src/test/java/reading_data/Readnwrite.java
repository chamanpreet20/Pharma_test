package reading_data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Readnwrite {
	static String celldata;
    static int index1,index2, index3;
	public static void main(String[] args) throws IOException {
		
//		Reading the data from existing excel sheet and adding it in an array
		File f = new File("C:\\Users\\chaman.preet\\Desktop\\NDMM-Epi.xlsx");
		FileInputStream in = new FileInputStream(f);
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet spreadsheet = workbook.getSheet("Articles");
		XSSFRow row1,row;
		ArrayList<String> arr1 = new ArrayList<String>();
		ArrayList<String> arr2 = new ArrayList<String>();
		ArrayList<String> arr3 = new ArrayList<String>();
		DataFormatter format = new DataFormatter();
		int lastRow = spreadsheet.getLastRowNum();
		System.out.println(lastRow);
	     row1 = spreadsheet.getRow(0);
	     for(int j=0;j<row1.getLastCellNum();j++)
			{
				XSSFCell cell=row1.getCell(j);
				if(cell.getStringCellValue().trim().equalsIgnoreCase("KeyAuthor"))
				{
					index1 = cell.getColumnIndex();
					System.out.println(index1);
				}
				else if(cell.getStringCellValue().trim().equalsIgnoreCase("StudyTitle"))
				{
					index2 = cell.getColumnIndex();
					System.out.println(index2);
				}
				else if(cell.getStringCellValue().trim().equalsIgnoreCase("StudyPopulation"))
				{
					index3 = cell.getColumnIndex();
					System.out.println(index3);
				}
				else
				{
					System.out.println("These columns are not present in this sheet");
				}
			}
	     for(int z=0;z<lastRow+1;z++)
			{
	    	 row = spreadsheet.getRow(z);
			int x=index1;
			while(x<=index1)
			//	for(int x=index1;x<=index1;x++)
				{
			//	XSSFCell getcell = spreadsheet.getRow(z).getCell(x);
				arr1.add(spreadsheet.getRow(z).getCell(x).getStringCellValue());
				x++;
		}
			int x1=index2;
			while(x1<=index2)
			//	for(int x=index2;x<=index2;x++)
				{
			//	XSSFCell getcell2 = spreadsheet.getRow(z).getCell(x);
				arr2.add(spreadsheet.getRow(z).getCell(x1).getStringCellValue());
				x1++;
		}
			int x3=index3;
			while(x3<=index3)
				//for(int x=index3;x<=index3;x++)
				{
					String value = format.formatCellValue(spreadsheet.getRow(z).getCell(x3));
			//	XSSFCell getcell = spreadsheet.getRow(z).getCell(x);
				arr3.add(value);
				x3++;
		}
			}
		workbook.close();
		System.out.println("key author array is: " +arr1 +"\n");
		System.out.println("Study title array is: " +arr2 +"\n");
		System.out.println("Study Population array is: " +arr3 +"\n");
		
//		Writing the data (captured from above sheet) in a new excel sheet 		 
		XSSFWorkbook workbook2 = new XSSFWorkbook();
		XSSFSheet spreadsheet2 = workbook2.createSheet("test");
		for (int i = 0; i < arr1.size(); i++) {
			XSSFRow row2 = spreadsheet2.createRow(i);
			row2.createCell(0).setCellValue(arr1.get(i));
			row2.createCell(1).setCellValue(arr2.get(i));
			row2.createCell(2).setCellValue(arr3.get(i));
		}
		/*for (int i = 0; i < arr2.size(); i++) {
			XSSFRow row3 = spreadsheet2.createRow(i);
			row3.createCell(1).setCellValue(arr2.get(i));
		}*/
		
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\chaman.preet\\Desktop\\test_excel_write.xlsx"));
		workbook2.write(out);
		out.close();
		workbook2.close();

	}
	}

