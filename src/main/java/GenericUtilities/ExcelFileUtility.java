package GenericUtilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author Vamsi This class contains the reusable methods for Excel
 */

public class ExcelFileUtility {

	public Workbook wb;

	/**
	 * This method is used to fetch data from Excel sheet
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */

	public String fetchDataFromExcelFile(String sheetname, int rowindex, int cellindex)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_OCData.xlsx");

		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row r = sh.getRow(rowindex);
		Cell c = r.getCell(cellindex);
		String data = c.toString();
		return data;

	}

	/**
	 * This method is used to write back data to excel sheet
	 * 
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */

	public void writeBackDatatoExcelFile(String sheetname, int rowindex, int cellindex, String data)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/Vtiger_CRM data.xlsx");

		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row r = sh.createRow(rowindex);
		Cell c = r.createCell(cellindex);
		c.setCellValue(data);

		FileOutputStream fos = new FileOutputStream("./src/test/resources/Vtiger_CRM data.xlsx");
		wb.write(fos);

	}

	/**
	 * this method is used to close the excel work book
	 * 
	 * @throws IOException
	 */

	public void closeExcelFile() throws IOException {
		wb.close();
	}

	public String fetchDataFromMultipleLines(String sheetname) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/Vtiger_CRM data.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);

		String data = null;
		for (int i = 0; i < sh.getLastRowNum(); i++) {

			for (int j = 0; j <= sh.getRow(i).getLastCellNum(); j++) {
				data = sh.getRow(i).getCell(j).toString();
			}

		}
		return data;

	}

}

