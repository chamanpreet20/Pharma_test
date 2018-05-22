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


public class Read_ClientExcel {

	int index1,index2,index3,index4,index5,index6,index7,index8,index9;
	static int input=9;
	//String[] arrr1=new String[144];
	/*static ArrayList<String> lists[]=new ArrayList[input];
	for(int i=0;i<input;i++){
		   lists[i]=new ArrayList<String>();
		}*/
	static ArrayList<String> arr1 = new ArrayList<String>();
	static ArrayList<String> arr2 = new ArrayList<String>();
	static ArrayList<String> arr3 = new ArrayList<String>();
	static ArrayList<String> arr4 = new ArrayList<String>();
	static ArrayList<String> arr5 = new ArrayList<String>();
	static ArrayList<String> arr6 = new ArrayList<String>();
	static ArrayList<String> arr7 = new ArrayList<String>();
	static ArrayList<String> arr8 = new ArrayList<String>();
	static ArrayList<String> arr9 = new ArrayList<String>();

//=========================Read data from client excel for columns you want to write to seperate excel=========================================
	 public void read_evidence_excel() throws IOException
	 {
		 File f = new File("C:\\Users\\chaman.preet\\Desktop\\NDMM-Epi.xlsx");
			FileInputStream in = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(in);
			XSSFSheet spreadsheet = workbook.getSheet("Articles");
			XSSFRow row1,row;
			
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
					else if(cell.getStringCellValue().trim().equalsIgnoreCase("Incidence"))
					{
						index4 = cell.getColumnIndex();
						System.out.println(index4);
					}
					else if(cell.getStringCellValue().trim().equalsIgnoreCase("Prevalence"))
					{
						index5 = cell.getColumnIndex();
						System.out.println(index5);
					}
					else if(cell.getStringCellValue().trim().equalsIgnoreCase("Mortality"))
					{
						index6 = cell.getColumnIndex();
						System.out.println(index6);
					}
					else if(cell.getStringCellValue().trim().equalsIgnoreCase("standardisedRateRatio"))
					{
						index7 = cell.getColumnIndex();
						System.out.println(index7);
					}
					else if(cell.getStringCellValue().trim().equalsIgnoreCase("StudyDuration"))
					{
						index8 = cell.getColumnIndex();
						System.out.println(index8);
					}
					else if(cell.getStringCellValue().trim().equalsIgnoreCase("Comments"))
					{
						index9 = cell.getColumnIndex();
						System.out.println(index9);
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
					String da = spreadsheet.getRow(z).getCell(x).getStringCellValue();
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
				int x4=index4;
				while(x4<=index4)
					{
						String value = format.formatCellValue(spreadsheet.getRow(z).getCell(x4));
						arr4.add(value);
					x4++;
			}
				int x5=index5;
				while(x5<=index5)
					{
						String value = format.formatCellValue(spreadsheet.getRow(z).getCell(x5));
						arr5.add(value);
					x5++;
			}
				int x6=index6;
				while(x6<=index6)
					{
						String value = format.formatCellValue(spreadsheet.getRow(z).getCell(x6));
						arr6.add(value);
					x6++;
			}
				int x7=index7;
				while(x7<=index7)
					{
						String value = format.formatCellValue(spreadsheet.getRow(z).getCell(x7));
						arr7.add(value);
					x7++;
			}
				int x8=index8;
				while(x8<=index8)
					{
						String value = format.formatCellValue(spreadsheet.getRow(z).getCell(x8));
						arr8.add(value);
					x8++;
			}
				int x9=index9;
				while(x9<=index9)
					{
						String value = format.formatCellValue(spreadsheet.getRow(z).getCell(x9));
						arr9.add(value);
					//	arr9.add(value);
					x9++;
			}
				}
			workbook.close();
		/*	System.out.println("key author array is: " +arr1 +"\n");
			System.out.println("Study title array is: " +arr2 +"\n");
			System.out.println("Study Population array is: " +arr3 +"\n");
			System.out.println("Incidence array is: " +arr4 +"\n");
			System.out.println("Prevelance array is: " +arr5 +"\n");
			System.out.println("Mortality array is: " +arr6 +"\n");
			System.out.println("standardisedRateRatio array is: " +arr7 +"\n");
			System.out.println("Study duration array is: " +arr8 +"\n");
			System.out.println("Comments array is: " +arr9 +"\n");*/
	 }
}

	 

