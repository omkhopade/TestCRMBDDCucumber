package Utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {



	public static String path=".\\TestData\\TestData.xlsx";
	public static XSSFWorkbook workbook;
	public static  XSSFSheet sheetName;
	public static FileInputStream file;
	public static XSSFRow rows;
	public static XSSFCell cells;


	public static int lastCellNum(String SheetName)
	{
		try 
		{
			file= new FileInputStream(path);
			workbook = new XSSFWorkbook(file);
			sheetName = workbook.getSheet(SheetName);

		}

		catch (Exception e) {

			System.out.println("Exceoption occcure while file loading "+e.getMessage());
		}

		int cell=sheetName.getRow(0).getLastCellNum();

		return cell;

	}


	public static String getCellData(int row, int cell, String SheetName)
	{

		try 
		{
			file= new FileInputStream(path);
			workbook = new XSSFWorkbook(file);
			sheetName = workbook.getSheet(SheetName);

		}

		catch (Exception e) {

			System.out.println("Exceoption occcure while file loading "+e.getMessage());
		}


		rows=sheetName.getRow(row);
		cells=sheetName.getRow(row).getCell(cell);

		DataFormatter formatter = new DataFormatter();

		String data=formatter.formatCellValue(cells);

		return data;
	}

	public static  String headerName(String SheetName, int cell)
	{
		try 
		{
			file= new FileInputStream(path);
			workbook = new XSSFWorkbook(file);
			sheetName = workbook.getSheet(SheetName);

		}

		catch (Exception e) {

			System.out.println("Exceoption occcure while file loading "+e.getMessage());
		}

		cells=sheetName.getRow(0).getCell(cell);

		DataFormatter formatter = new DataFormatter();

		String header=formatter.formatCellValue(cells);

		return header;
	}


	public static HashMap<String, String> readDataFromPool(int rowNum){

		HashMap<String, String> map = new HashMap<String, String>();

		int x=lastCellNum("Sheet1");
		for (int i=0;i<x;i++)
		{
			String value =getCellData(rowNum, i, "Sheet1");
			if (!value.isEmpty())
			{
				map.put(headerName("Sheet1", i), value);
			}
			else
				continue;

		}

		return map;

	}

	public static void main(String[] args) {

		
		HashMap<String, String> map1= new HashMap<String, String>();
		map1=readDataFromPool(2);
		System.out.println("Address is : "+map1.get("Address"));
		
		
		for(Entry<String, String> entry : map1.entrySet())
		{
			System.out.print(entry.getKey() + " " + entry.getValue());
			System.out.println();
		}


	}


}
