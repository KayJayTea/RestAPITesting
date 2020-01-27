import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class data_driven {
	
	public ArrayList<String> getData(String testcaseName) throws IOException {
		
		ArrayList<String> data_array = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Book1.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int number_of_sheets = workbook.getNumberOfSheets();
		
		for(int i = 0; i < number_of_sheets; i++) {
			
			if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				Iterator<Row> rows = sheet.iterator();
				Row first_row = rows.next();
				Iterator<Cell> cell = first_row.cellIterator();
				
				int k = 0;
				int column = 0;
				
				while(cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column = k;
					}
					
					k++;
				}
				
				// System.out.println(column);
				
				while(rows.hasNext()) {
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cell_value = r.cellIterator();
						
						while(cell_value.hasNext()) {
							
							Cell c = cell_value.next();
							
							if(c.getCellType() == CellType.STRING) {
								data_array.add(c.getStringCellValue());
							} 
							
							else {
								data_array.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
							
						}
					}
				}
			}
		}
		
		return data_array;
	}
	
	public static void main(String[] args) throws IOException {
		
	}

}
