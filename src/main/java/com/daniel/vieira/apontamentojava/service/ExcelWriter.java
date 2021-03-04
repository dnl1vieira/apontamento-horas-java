package com.daniel.vieira.apontamentojava.service;

import com.daniel.vieira.apontamentojava.models.TimePoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
public class ExcelWriter {

   @Value("${app.excel.out}")
   protected String PATH_TO_SAVE_FILE;

   String[] header = {"DATE", "SERVICE", "CUSTOMER", "MANAGER", "PROJECT", "HOURS", "DESCRIPTIONS"};

   public String load(ArrayList<TimePoint> timePointObj) {
      try {
         Workbook wb = createWorbook();
         Sheet sh = createSheet(wb);
         Font headerFont = setHeaderFont(wb);
         CellStyle cs = setHeaderCellStyle(wb, headerFont);
         setHeaderRow(sh, cs);
         fillData(timePointObj, wb, sh);
         String path = finishProcess(wb);
         return path;
      } catch (Exception e) {
         log.error("Error while create excel.",
                 e);
      }
      return null;
   }

   public Workbook createWorbook() {
      return new XSSFWorkbook();
   }

   private Sheet createSheet(Workbook wb) {
      return wb.createSheet("Apontamentos");
   }

   private Font setHeaderFont(Workbook wb) {
      Font headerFont = wb.createFont();
      headerFont.setBold(true);
      headerFont.setFontHeightInPoints((short) 12);
      headerFont.setColor(IndexedColors.BLACK.index);
      return headerFont;
   }

   private CellStyle setHeaderCellStyle(Workbook wb, Font headerFont) {
      CellStyle headerStyle = wb.createCellStyle();
      headerStyle.setFont(headerFont);
      headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
      return headerStyle;
   }

   private void setHeaderRow(Sheet sh, CellStyle cs) {
      Row headerRow = sh.createRow(0);
      for (int i = 0; i < header.length; i++) {
         Cell cell = headerRow.createCell(i);
         cell.setCellValue(header[i]);
         cell.setCellStyle(cs);
      }
   }

   private void fillData(ArrayList<TimePoint> timePointObj, Workbook wb, Sheet sh) {
      ArrayList<TimePoint> listTimePoint = timePointObj;
      CreationHelper creationHelper = wb.getCreationHelper();
      CellStyle dateStyle = wb.createCellStyle();
      dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
      int rownum = 1;
      for (TimePoint i : listTimePoint) {
         Row row = sh.createRow(rownum++);
         Cell dateCell = row.createCell(0);
         dateCell.setCellValue(i.getDate());
         dateCell.setCellStyle(dateStyle);
         row.createCell(1).setCellValue(i.getService());
         row.createCell(2).setCellValue(i.getCustomer());
         row.createCell(3).setCellValue(i.getManager());
         row.createCell(4).setCellValue(i.getProject());
         row.createCell(5).setCellValue(i.getHour());
         row.createCell(6).setCellValue(i.getDescription());
      }
   }

   private String finishProcess(Workbook wb) throws IOException {
      FileOutputStream fileOut = new FileOutputStream(PATH_TO_SAVE_FILE);
      wb.write(fileOut);
      fileOut.close();
      wb.close();
      return PATH_TO_SAVE_FILE;
   }

   private String getCurrentDate() {
      return String.valueOf(LocalDateTime.now());
   }












}


