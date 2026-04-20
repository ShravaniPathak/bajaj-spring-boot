package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.WebhookRequest;
import com.example.demo.model.WebhookResponse;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public WebhookResponse generateWebhook(String name, String regNo, String email) {

        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        WebhookRequest request = new WebhookRequest();
        request.setName(name);
        request.setRegNo(regNo);
        request.setEmail(email);

        return restTemplate.postForObject(url, request, WebhookResponse.class);
    }

    public void submitFinalQuery(String webhookUrl, String token, String finalQuery,
        String name, String regNo, String email) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, String> body = new HashMap<>();
		body.put("finalQuery", finalQuery);
		body.put("name", name);
		body.put("regNo", regNo);
		body.put("email", email);
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
		webhookUrl,
		HttpMethod.POST,
		entity,
		String.class
		);
		
		System.out.println("Response Status: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody());
		}
	}