package com.daniel.vieira.apontamentojava.service;

import com.daniel.vieira.apontamentojava.exceptions.RequiredFiledsIsMissing;
import com.daniel.vieira.apontamentojava.exceptions.TimeTrackingNotFound;
import com.daniel.vieira.apontamentojava.models.TimeTracking;
import com.daniel.vieira.apontamentojava.repository.TimeTrackingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Slf4j
@Service
public class TimeTrackingService {

   @Autowired
   TimeTrackingRepository repository;

   public Page<TimeTracking> findAll(Pageable pageable) {
      return repository.getAll(pageable);
   }

   public void saveOrUpdate(TimeTracking obj){
      repository.save(obj);
   }

   public TimeTracking validateToDelete(Long id) throws TimeTrackingNotFound {
      Optional<TimeTracking> timeTracking = repository.findById(id);
      if(timeTracking.isPresent()){
         repository.deleteById(timeTracking.get().getId());
         return timeTracking.get();
      }else{
         throw new TimeTrackingNotFound("Fail to delete TimeTracking, ID NOT FOUND");
      }
   }

}