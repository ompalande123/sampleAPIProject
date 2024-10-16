package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private static final String filePath=".//testData//myData.xlsx";
	
	public static Map<String, String> getTestData(String sheetName, String testID) throws IOException
	{
		FileInputStream fis=new FileInputStream(filePath);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet(sheetName);
		
		Map<String, String> data=new HashMap<>();
		
		boolean testIDfound=false;
		
		for(Row row:sheet)
		{
			XSSFCell firstCell=(XSSFCell) row.getCell(0);
			if(firstCell !=null && firstCell.getStringCellValue().equals(testID))
			{
				testIDfound=true;
				for(Cell cell:row)
				{
					String header=sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
					String value=cell.getStringCellValue();
					data.put(header, value);
				}
				break;
			}
		}
		workbook.close();
		fis.close();
		
		if(!testIDfound)
		{
			throw new IllegalArgumentException("Test ID not found : "+testID);
		}
		
		return data;
		
		
		
	}
	
	
	

}
