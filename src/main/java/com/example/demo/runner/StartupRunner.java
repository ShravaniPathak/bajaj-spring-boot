package com.example.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.WebhookResponse;
import com.example.demo.service.SqlSolverService;
import com.example.demo.service.WebhookService;

@Component
public class StartupRunner {

    @Autowired
    private WebhookService webhookService;

    @Autowired
    private SqlSolverService sqlSolverService;

    public void execute() {

        System.out.println("=== PROCESS STARTED ===");

        String name = "Shravani Pathak";
        String regNo = "ADT23SOCB1057";
        String email = "shravanispathak@gmail.com";

        try {
            System.out.println("Calling generateWebhook API...");

            WebhookResponse response = webhookService.generateWebhook(name, regNo, email);

            String webhookUrl = response.getWebhook();
            String token = response.getAccessToken();

            System.out.println("Webhook URL: " + webhookUrl);
            System.out.println("Access Token: " + token);

            String finalQuery = sqlSolverService.solve(regNo);

            System.out.println("Final SQL Query: " + finalQuery);

            System.out.println("Submitting final query...");

            webhookService.submitFinalQuery(
                    webhookUrl,
                    token,
                    finalQuery,
                    name,
                    regNo,
                    email
            );

            System.out.println("=== PROCESS COMPLETED SUCCESSFULLY ===");

        } catch (Exception e) {
            System.out.println("❌ ERROR OCCURRED:");
            e.printStackTrace();
        }
    }
}