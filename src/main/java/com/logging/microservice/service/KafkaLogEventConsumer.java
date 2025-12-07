package com.logging.microservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.logging.microservice.dto.kafkaLogEventDto.KafkaLogEvent;
import com.logging.microservice.entity.KafkaLogEntity;
import com.logging.microservice.repository.KafkaLogEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaLogEventConsumer {

  private final KafkaLogEventRepository logRepository;
  private final ObjectMapper mapper = new ObjectMapper();

  @KafkaListener(topics = "user_request_logs", groupId = "logging-service-group")
  public void consume(KafkaLogEvent logEvent) {
    KafkaLogEntity entity = new KafkaLogEntity();

    entity.setServiceName(logEvent.getServiceName());
    entity.setFullUrl(logEvent.getFullUrl());
    entity.setApiEndpoint(logEvent.getApiEndpoint());
    entity.setRequestMethod(logEvent.getRequestMethod());

    try {
      entity.setRequestHeaders(mapper.writeValueAsString(logEvent.getRequestHeaders()));
      entity.setRequestQueryParams(mapper.writeValueAsString(logEvent.getRequestQueryParams()));
      entity.setRequestBody(mapper.writeValueAsString(logEvent.getRequestBody()));
      entity.setResponseBody(mapper.writeValueAsString(logEvent.getResponseBody()));
    } catch (Exception ignored) {
    }

    entity.setRequestTime(logEvent.getRequestTime());
    entity.setResponseTime(logEvent.getResponseTime());
    entity.setApiResponseTime(logEvent.getApiResponseTime());
    entity.setApiResponseTimeUnits(logEvent.getApiResponseTimeUnits());

    entity.setApiResponseStatus(logEvent.getApiResponseStatus());
    entity.setResponseStatusCode(logEvent.getResponseStatusCode());
    entity.setResponseStatusCodeType(logEvent.getResponseStatusCodeType());

    entity.setErrorMessage(logEvent.getErrorMessage());
    entity.setExceptionType(logEvent.getExceptionType());
    entity.setExceptionStackTrace(logEvent.getExceptionStackTrace());

    entity.setTraceId(logEvent.getTraceId());
    entity.setClientIp(logEvent.getClientIp());
    entity.setUserAgent(logEvent.getUserAgent());
    entity.setContentType(logEvent.getContentType());
    entity.setAcceptType(logEvent.getAcceptType());

    entity.setRequestBodySize(logEvent.getRequestBodySize());
    entity.setResponseBodySize(logEvent.getResponseBodySize());

    entity.setHttpVersion(logEvent.getHttpVersion());
    entity.setRequestUserId(logEvent.getRequestUserId());

    logRepository.save(entity);
  }
}
