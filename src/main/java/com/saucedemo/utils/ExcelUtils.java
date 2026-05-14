package com.saucedemo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;

public class ExcelUtils {

	private static final Logger logger = LogManager.getLogger(ExcelUtils.class);

	public static Object[][] getTestData(String sheetName) {

		try {

			logger.info("Reading Excel File Data");

			InputStream inputStream = ExcelUtils.class.getClassLoader().getResourceAsStream("loginData.xlsx");

			if (inputStream == null) {

				logger.error("Excel File Not Found");

				throw new RuntimeException("Excel file not found");
			}

			Workbook workbook = WorkbookFactory.create(inputStream);

			Sheet sheet = workbook.getSheet(sheetName);

			logger.info("Reading Sheet: {}", sheetName);

			int rows = sheet.getPhysicalNumberOfRows();

			int cols = sheet.getRow(0).getPhysicalNumberOfCells();

			Object[][] data = new Object[rows - 1][cols];

			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i < rows; i++) {

				for (int j = 0; j < cols; j++) {

					data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
				}
			}

			workbook.close();

			logger.info("Excel Data Read Successfully");

			return data;

		} catch (Exception e) {

			logger.error("Failed To Read Excel File", e);

			throw new RuntimeException("Unable to read Excel file", e);
		}
	}
}