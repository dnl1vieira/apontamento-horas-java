package com.daniel.vieira.apontamentojava.controller;

import com.daniel.vieira.apontamentojava.exceptions.RequiredFiledsIsMissing;
import com.daniel.vieira.apontamentojava.exceptions.TimeTrackingNotFound;
import com.daniel.vieira.apontamentojava.models.TimeTracking;
import com.daniel.vieira.apontamentojava.service.TimeTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/time-point")
@Slf4j
public class TimePointController {

   private final static String MSG_SAVE = "Saved Successfully";
   private final static String MSG_DELETE = "Deleted Successfully";

   @Autowired
   TimeTrackingService timeTrackingService;

   @GetMapping("/findAll")
   public ResponseEntity<Page<TimeTracking>> findAll(Pageable pageable) {
      Page<TimeTracking> timePoint = timeTrackingService.findAll(pageable);
      return new ResponseEntity<Page<TimeTracking>>(timePoint, HttpStatus.OK);
   }

   @PostMapping("/saveOrUpdate")
   public ResponseEntity<String> saveOrUpdate(@RequestBody TimeTracking obj) {
      try {
         if(obj.validate()){
            timeTrackingService.saveOrUpdate(obj);
            return new ResponseEntity<String>(MSG_SAVE, HttpStatus.OK);
         }else {
            String MSG = "Error found! Please contact us";
            return new ResponseEntity<String>(MSG, HttpStatus.BAD_REQUEST);
         }
      } catch (RequiredFiledsIsMissing exception) {
         String MSG_ERROR = "Error Found in Save TIME POINT \n ERROR: " + exception;
         return new ResponseEntity<String>(MSG_ERROR, HttpStatus.FORBIDDEN);
      }
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deleteTimeTrackingById(@PathVariable String id) {
      try{
         TimeTracking timeTracking = timeTrackingService.validateToDelete(Long.valueOf(id));
         return new ResponseEntity<String>(MSG_DELETE, HttpStatus.OK);
      } catch (TimeTrackingNotFound timeTrackingNotFound) {
         return new ResponseEntity<String>(timeTrackingNotFound.getMessage(), HttpStatus.NOT_FOUND);
      }  catch (Exception ex) {
         String MSG_ERROR = "Error Found in delete TIME TRACKING";
         return new ResponseEntity<String>(MSG_ERROR, HttpStatus.BAD_REQUEST);
      }
   }

}
