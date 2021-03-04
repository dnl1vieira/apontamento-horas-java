package com.daniel.vieira.apontamentojava.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExcelWriteModel {

   private String customer;

   private String hour;

   private String project;

   private String service;

   private String description;

   private String manager;

   private LocalDateTime date;

   public ExcelWriteModel(String customer, String hour, String project, String service, String description, String manager, LocalDateTime date) {
      this.customer = customer;
      this.hour = hour;
      this.project = project;
      this.service = service;
      this.description = description;
      this.manager = manager;
      this.date = date;
   }
}
