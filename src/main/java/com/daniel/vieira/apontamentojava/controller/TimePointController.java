package com.daniel.vieira.apontamentojava.controller;

import com.daniel.vieira.apontamentojava.dto.TimePointDTO;
import com.daniel.vieira.apontamentojava.models.TimePoint;
import com.daniel.vieira.apontamentojava.repository.TimePointRepository;
import com.daniel.vieira.apontamentojava.service.TimePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/time-point")
@Slf4j
public class TimePointController {

   @Autowired
   TimePointRepository repositoryTimePoint;

   @Autowired
   TimePointService serviceTimePoint;

   private final static String MSG_SAVE = "Registro Salvo com Sucesso!";
   private final static String MSG_UPDATE = "Registro Alterado com Sucesso!";
   private final static String MSG_DELETE = "Registro Deletado com Sucesso!";

   @GetMapping("/findAll")
   public ResponseEntity<Page<TimePoint>> findAll(@Param("dateFrom") String dateFrom, @Param("dateFrom") String dateTo, Pageable pageable) {
      Page<TimePoint> timePoint = serviceTimePoint.findByFilter(dateFrom, dateTo, pageable);
      return new ResponseEntity<Page<TimePoint>>(timePoint, HttpStatus.OK);
   }

   @PostMapping("/saveOrUpdate")
   public ResponseEntity<String> saveOrUpdate(@RequestBody TimePointDTO obj) {
      try {
         String MSG = Objects.isNull(obj.getId()) ? MSG_SAVE : MSG_UPDATE;
         TimePoint timePoint = serviceTimePoint.validateToSave(obj);
         repositoryTimePoint.save(timePoint);
         return new ResponseEntity<String>(MSG, HttpStatus.OK);
      } catch (IllegalArgumentException | ParseException illegalArgumentExceptionError) {
         String MSG_ERROR = "Error Found in Save TIME POINT \n ERROR: " + illegalArgumentExceptionError;
         return new ResponseEntity<String>(MSG_ERROR, HttpStatus.BAD_REQUEST);
      }
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> delete(@PathVariable String id) {
      TimePoint timePoint = serviceTimePoint.validateToDelete(Long.valueOf(id));
      repositoryTimePoint.deleteById(timePoint.getId());
      return new ResponseEntity<String>(MSG_DELETE, HttpStatus.OK);
   }


}
