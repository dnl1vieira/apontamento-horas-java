package com.daniel.vieira.apontamentojava.controller;

import com.daniel.vieira.apontamentojava.models.TimePoint;
import com.daniel.vieira.apontamentojava.repository.TimePointRepository;
import com.daniel.vieira.apontamentojava.service.TimePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/time-point")
@Slf4j
public class TimePointController {

   @Autowired
   TimePointRepository repositoryTimePoint;

   @Autowired
   TimePointService serviceTimePoint;

   private final static String MSG_SAVE = "Save Successfully";
   private final static String MSG_UPDATE = "Update Successfully";
   private final static String MSG_DELETE = "Delete Successfully";

   @PostMapping("/save")
   public ResponseEntity<String> save(TimePoint objTimePoint) {
      log.info(objTimePoint.getCustomer());
      try{
         TimePoint timePoint = serviceTimePoint.validateToSave(objTimePoint);
         repositoryTimePoint.save(timePoint);
         return new ResponseEntity<String>(MSG_SAVE, HttpStatus.OK);
      }
      catch (IllegalArgumentException illegalArgumentExceptionError){
         String MSG_ERROR = "Error Found in Save TIME POINT \n ERROR: " + illegalArgumentExceptionError;
         return new ResponseEntity<String>(MSG_ERROR, HttpStatus.BAD_REQUEST);
      }
   }

   @PostMapping("/update")
   public ResponseEntity<String> update(Long id, TimePoint objTimePoint) {
      TimePoint timePoint = serviceTimePoint.validateToUpdate(id, objTimePoint);
      repositoryTimePoint.save(timePoint);
      return new ResponseEntity<String>(MSG_UPDATE, HttpStatus.OK);
   }

   @DeleteMapping("/delete")
   public ResponseEntity<String> delete(Long id) {
      TimePoint timePoint = serviceTimePoint.validateToDelete(id);
      repositoryTimePoint.delete(timePoint);
      return new ResponseEntity<String>(MSG_DELETE, HttpStatus.OK);
   }

   @GetMapping("/listAll")
   public List<TimePoint> listAll() {
      List<TimePoint> listTimePoint = new ArrayList<>();
      repositoryTimePoint.findAll().forEach(listTimePoint::add);
      return listTimePoint;
   }


}
