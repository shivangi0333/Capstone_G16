package com.wipro.capstoneshopfrohome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstoneshopfrohome.entity.SalesLog;


@Repository
public interface ISalesLogRepository extends JpaRepository<SalesLog, Integer> {

}
