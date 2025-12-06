package com.logging.microservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "backendApiRequestLogs")
public class RequestLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  String serviceType;
  String request_header;
  String api_url;
  String requestedBy;
  LocalDateTime requestTime;

}
