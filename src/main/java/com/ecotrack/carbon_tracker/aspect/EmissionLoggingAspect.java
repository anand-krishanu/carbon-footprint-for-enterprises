package com.ecotrack.carbon_tracker.aspect;

import com.ecotrack.carbon_tracker.entity.EmissionRecord;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Slf4j
@Component
public class EmissionLoggingAspect {

    @AfterReturning(pointcut = "exectution(* com.ecotrack.carbon_tracker.service.EmissionRecordService.createEmissionRecord(..)", returning = "result")
    public void logEmissionRecordCreation (EmissionRecord result) {
        if(result != null) {
            log.info("New Emission Record added for Department: {}, Amount: {} CO₂e, Type: {}",
                    result.getDepartment().getName(),
                    result.getCo2Amount(),
                    result.getEmissionType());
        }
    }

    @Around("execution(* com.ecotrack.carbon_tracker.service.CarbonReportService.getCarbonSummary(..))")
    public Object logSummaryExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        Object result = joinPoint.proceed();
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();

        log.info("Carbon summary generated in {} ms", timeElapsed);
        return result;
    }
}
