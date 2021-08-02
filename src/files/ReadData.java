package files;

import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadData {
	
	List<String> l = new ArrayList<>();
	HashMap<String,Object> map = new HashMap<String,Object>();
	//HashMap<String, Object>
	@Test(enabled=true)
	public  void data() throws Exception
	{
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\RestLearn\\addbook.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet =workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		while(rows.hasNext())
		{
			Row row = rows.next();
			map.put(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());	
		}
		
		
		map.forEach((k,v)->System.out.println(k+" "+v));
		workbook.close();
		//return map;
	}

}
