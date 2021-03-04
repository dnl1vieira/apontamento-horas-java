package com.daniel.vieira.apontamentojava.repository;

import com.daniel.vieira.apontamentojava.models.TimePoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
@CrossOrigin("*")
@RepositoryRestResource(path = "time-point", collectionResourceRel = "time-points", itemResourceRel = "time-point")
public interface TimePointRepository extends PagingAndSortingRepository<TimePoint, Long>{

   @Query("select t from TimePoint t")
   Page<TimePoint> getAll(Pageable pageable);

   @Query("select t from TimePoint t where" +
           " t.date between :dateFrom and :dateTo or (:dateFrom = null and :dateTo = null) order by t.createDate asc")
   Page<TimePoint> findByFilters(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo, Pageable pageable);

   @Query("select t from TimePoint t where" +
           " t.date between :dateFrom and :dateTo or (:dateFrom = null and :dateTo = null) order by t.createDate asc")
   ArrayList<TimePoint> findByFiltersToDownload(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

   TimePoint getTimePointById(Long id);
}