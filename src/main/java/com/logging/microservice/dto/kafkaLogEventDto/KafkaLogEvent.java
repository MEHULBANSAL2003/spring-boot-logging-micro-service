package com.logging.microservice.dto.kafkaLogEventDto;

import lombok.Data;
import java.util.Map;

@Data
public class KafkaLogEvent {

  private String serviceName;
  private String fullUrl;
  private String apiEndpoint;
  private String requestMethod;

  private Map<String, String> requestHeaders;
  private Map<String, Object> requestQueryParams;

  private long requestTime;
  private long responseTime;
  private int apiResponseTime;
  private String apiResponseTimeUnits;

  private Object requestBody;
  private Object responseBody;

  private String apiResponseStatus;  // SUCCESS / FAILURE
  private int responseStatusCode;
  private String responseStatusCodeType;

  private String errorMessage;
  private String exceptionType;
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
}
