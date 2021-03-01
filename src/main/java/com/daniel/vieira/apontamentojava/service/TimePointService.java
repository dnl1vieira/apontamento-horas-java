package com.daniel.vieira.apontamentojava.service;

import com.daniel.vieira.apontamentojava.dto.TimePointDTO;
import com.daniel.vieira.apontamentojava.models.TimePoint;
import com.daniel.vieira.apontamentojava.repository.TimePointRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class TimePointService {

   @Autowired
   TimePointRepository repositoryTimePoint;

   public Page<TimePoint> findByFilter(String dateFrom, String dateTo, Pageable pageable) {
      Page<TimePoint> obj = repositoryTimePoint.findByFilters(convertStringToDate(dateFrom), convertStringToDate(dateTo), pageable);
      return obj;
   }

   public TimePoint validateToSave(TimePointDTO objTimePointDto) throws ParseException {
      TimePoint obj = convertToEntity(objTimePointDto);
      return obj;
   }

   public TimePoint validateToDelete(Long id) {
      Optional<TimePoint> timePoint = repositoryTimePoint.findById(id);
      return timePoint.orElse(null);
   }

   private TimePoint convertToEntity(TimePointDTO objDTO) throws ParseException {
      if (objDTO.getId() != null) {
         TimePoint timePointEdit = repositoryTimePoint.findById(objDTO.getId()).get();
         timePointEdit.setCustomer(objDTO.getCustomer());
         timePointEdit.setService(objDTO.getService());
         timePointEdit.setProject(objDTO.getProject());
         timePointEdit.setManager(objDTO.getManager());
         timePointEdit.setDescription(objDTO.getDescription());
         timePointEdit.setHour(objDTO.getHour());
         timePointEdit.setDate(objDTO.getDate());
         return timePointEdit;
      }else{
         TimePoint timePointSave = new TimePoint();
         timePointSave.setCustomer(objDTO.getCustomer());
         timePointSave.setService(objDTO.getService());
         timePointSave.setProject(objDTO.getProject());
         timePointSave.setManager(objDTO.getManager());
         timePointSave.setDescription(objDTO.getDescription());
         timePointSave.setHour(objDTO.getHour());
         timePointSave.setDate(objDTO.getDate());
         return timePointSave;
      }
   }

   public LocalDateTime convertStringToDate(String date){
      if(date.equals(""))
         return null;

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
      return dateTime;
   }

}