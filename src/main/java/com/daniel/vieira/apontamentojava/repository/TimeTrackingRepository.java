package com.daniel.vieira.apontamentojava.repository;

import com.daniel.vieira.apontamentojava.models.TimeTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
@RepositoryRestResource(path = "time-point", collectionResourceRel = "time-points", itemResourceRel = "time-point")
public interface TimeTrackingRepository extends PagingAndSortingRepository<TimeTracking, Long>{

   @Query("select t from TimeTracking t")
   Page<TimeTracking> getAll(Pageable pageable);

   TimeTracking getTimePointById(Long id);
}