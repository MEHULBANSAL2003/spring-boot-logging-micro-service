package com.logging.microservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "api_logs")

public class KafkaLogEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String serviceName;
  private String fullUrl;
  private String apiEndpoint;
  private String requestMethod;

  @Column(columnDefinition = "TEXT")
  private String requestHeaders;

  @Column(columnDefinition = "TEXT")
  private String requestQueryParams;

  private long requestTime;
  private long responseTime;
  private int apiResponseTime;
  private String apiResponseTimeUnits;

  @Column(columnDefinition = "TEXT")
  private String requestBody;

  @Column(columnDefinition = "TEXT")
  private String responseBody;

  private String apiResponseStatus;
  private int responseStatusCode;
  private String responseStatusCodeType;

  @Column(columnDefinition = "TEXT")
  private String errorMessage;

  @Column(columnDefinition = "TEXT")
  private String exceptionType;

  @Column(columnDefinition = "TEXT")
  private String exceptionStackTrace;

  private String traceId;
  private String clientIp;
  private String userAgent;
  private String contentType;
  private String acceptType;

  private Integer requestBodySize;
  private Integer responseBodySize;

  private String httpVersion;
  private String requestUserId;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
