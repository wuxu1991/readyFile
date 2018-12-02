package com.wuxu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class IoTest {
	public static void main(String[] args) {
		Map mapa = getALista1();
		Map mapb = getAListb();
		Set keySeta = mapa.keySet();
		Set keySetb = mapb.keySet();
		boolean contains = keySeta.containsAll(keySetb);
		System.out.println(contains);
		System.out.println(keySeta);
		System.out.println(keySetb);
		List<String> aList = new ArrayList<>();
		for (Object a : keySeta) {
			aList.add(mapa.get(a).toString());
		}
		List<String> bList = new ArrayList<>();
		for (Object b : keySetb) {
			bList.add(mapb.get(b).toString());
		}
		
		boolean containsAll = aList.containsAll(bList);
		System.out.println(containsAll);
		System.out.println(aList);
		System.out.println(bList);
	}
	@SuppressWarnings("deprecation")
	private static Map<String,String> getALista1() {
		try {
			Map<String, String> map = new HashMap<>();
			InputStream is = new FileInputStream("C:\\Users\\wuxu\\Desktop\\a.xls");
			HSSFWorkbook excel = new HSSFWorkbook(is);
			for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
				
				HSSFSheet sheet = excel.getSheetAt(numSheet);
				System.out.println(sheet.getLastRowNum()+1);
				if (sheet == null)
	                continue;
				for (int rowNum = 0; rowNum <= sheet.getLastRowNum()+1; rowNum++) {
	                HSSFRow row = sheet.getRow(rowNum);
	                if (row == null)
	                    continue;
	                
	                HSSFCell cell = row.getCell(0);
	                HSSFCell cell2 = row.getCell(1);
	                String key = cell.getStringCellValue();
	                String value = cell2.getStringCellValue();
	                map.put(key, value);
				}
			}
			return map;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	private static Map<String,String> getALista() {
		try {
			Map<String, String> map = new HashMap<>();
			Properties p = new Properties();
			InputStream inputStream = IoTest.class.getClassLoader().getResourceAsStream("com/wuxu/io/a.properties");
			p.load(inputStream);
			/*String value = p.getProperty("wap.fouce");
			System.out.println(value);*/
			//p.list(System.out);
			Object[] array = p.keySet().toArray();
			for (int i = 0; i < array.length; i++) {
				
				
				map.put(array[i].toString(), p.get(array[i]).toString());
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static Map<String,String> getAListb() {
		try {
			Map<String, String> map = new HashMap<>();
			Properties p = new Properties();
			InputStream inputStream = IoTest.class.getClassLoader().getResourceAsStream("com/wuxu/io/b.properties");
			p.load(inputStream);
			/*String value = p.getProperty("wap.fouce");
			System.out.println(value);*/
			//p.list(System.out);
			Object[] array = p.keySet().toArray();
			for (int i = 0; i < array.length; i++) {
				
				
				map.put(array[i].toString(), p.get(array[i]).toString());
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
