package com.logging.microservice.repository;


import com.logging.microservice.entity.KafkaLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaLogEventRepository extends JpaRepository<KafkaLogEntity,Long> {

}
