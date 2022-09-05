package com.example.search.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EmployeeService {
    @Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    Map<Integer, Map[]> fetchAllEmployeesByAges(List<Integer> ages);

    @Recover
    public String getResponseFall(RuntimeException e);
}
