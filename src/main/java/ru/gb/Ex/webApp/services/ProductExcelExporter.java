package ru.gb.Ex.webApp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.gb.Ex.webApp.entities.Product;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Getter
public class ProductExcelExporter {



    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private List<Product> products;



    public ProductExcelExporter(List<Product> products) {
        this.products = products;
        workbook = new XSSFWorkbook();
    }


    public void writeHeaderLine(){
        sheet = workbook.createSheet("Products");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);


        createCell(row, 0, "Product ID", style);
        createCell(row, 1, "Title", style);
        createCell(row, 2, "Price", style);
        createCell(row, 3, "Category", style);


    }



    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Product product : products) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, product.getId().intValue(), style);
            createCell(row, columnCount++, product.getTitle(), style);
            createCell(row, columnCount++, product.getPrice(), style);
            createCell(row, columnCount++, product.getCategories().getTitle(), style);
        }
    }





}
