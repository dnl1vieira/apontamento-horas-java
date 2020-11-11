package com.daniel.vieira.apontamentojava.service;

import com.daniel.vieira.apontamentojava.models.TimePoint;
import com.daniel.vieira.apontamentojava.repository.TimePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TimePointService {

   @Autowired
   TimePointRepository repositoryTimePoint;

   public TimePoint validateToSave(TimePoint objTimePoint) {
      return objTimePoint;
   }

   public TimePoint validateToUpdate(Long id, TimePoint objTimePoint) {
      TimePoint timePoint = repositoryTimePoint.findById(id).get();
      timePoint = objTimePoint;
      return timePoint;
   }

   public TimePoint validateToDelete(Long id) {
      TimePoint timePoint = repositoryTimePoint.findById(id).get();
      return timePoint;
   }

}