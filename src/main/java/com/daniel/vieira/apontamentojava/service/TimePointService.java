package com.daniel.vieira.apontamentojava.service;

import com.daniel.vieira.apontamentojava.dto.TimePointDTO;
import com.daniel.vieira.apontamentojava.models.TimePoint;
import com.daniel.vieira.apontamentojava.repository.TimePointRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class TimePointService {

   @Autowired
   TimePointRepository repositoryTimePoint;

   public Page<TimePoint> findAll(Pageable pageable) {
      Page<TimePoint> obj = repositoryTimePoint.getAll(pageable);
      return obj;
   }

   public TimePoint validateToSave(TimePointDTO objTimePointDto) throws ParseException {
      TimePoint objToSave = convertToEntity(objTimePointDto);
      return objToSave;
   }

   public TimePoint validateToUpdate(Long id, TimePoint objTimePoint) {
      TimePoint timePoint = repositoryTimePoint.findById(id).get();
      timePoint = objTimePoint;
      return timePoint;
   }

   public TimePoint validateToDelete(Long id) {
      Optional<TimePoint> timePoint = repositoryTimePoint.findById(id);
      return timePoint.orElse(null);
   }

   private TimePoint convertToEntity(TimePointDTO objDTO) throws ParseException {
      ModelMapper modelMapper = new ModelMapper();
      TimePoint timePoint = modelMapper.map(objDTO, TimePoint.class);
      if (timePoint.getId() != null) {
         Optional<TimePoint> oldTimePoint = repositoryTimePoint.findById(timePoint.getId());
         log.info("");
         oldTimePoint.ifPresent(point -> timePoint.setId(point.getId()));
      }
      return timePoint;
   }

}