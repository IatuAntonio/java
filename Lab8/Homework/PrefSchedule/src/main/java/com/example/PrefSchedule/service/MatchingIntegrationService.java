package com.example.PrefSchedule.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.PrefSchedule.dto.MatchPair;
import com.example.PrefSchedule.dto.MatchingRequestDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchingIntegrationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String SERVICE_URL = "http://localhost:8083/api/solver/solve";

    @Retry(name = "stableMatch", fallbackMethod = "fallbackSolve")
    public List<MatchPair> invokeMatchingService(MatchingRequestDTO request) {
        System.out.println("Calling Stable Matching Service...");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MatchingRequestDTO> requestEntity = new HttpEntity<>(request, headers);

        MatchPair[] response = restTemplate.postForObject(SERVICE_URL, requestEntity, MatchPair[].class);
        return response != null ? List.of(response) : List.of();
    }

    public List<MatchPair> fallbackSolve(MatchingRequestDTO request, Throwable t) {
        System.err.println("StableMatch service is down! Reason: " + t.getMessage());
        System.err.println("Using Fallback: Returning empty list (no assignments made).");
        return new ArrayList<>();
    }

}
