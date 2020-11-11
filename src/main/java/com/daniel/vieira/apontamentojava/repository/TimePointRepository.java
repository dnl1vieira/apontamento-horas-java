package com.daniel.vieira.apontamentojava.repository;

import com.daniel.vieira.apontamentojava.models.TimePoint;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TimePointRepository extends PagingAndSortingRepository<TimePoint, Long>{

}