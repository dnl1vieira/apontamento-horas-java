package com.daniel.vieira.apontamentojava.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimePointDTO {

   private String customer;
   private String hour;
   private String project;
   private String service;
   private String description;
   private String manager;
   private LocalDateTime date;

}
