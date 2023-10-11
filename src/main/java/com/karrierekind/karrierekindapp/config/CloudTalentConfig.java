package com.karrierekind.karrierekindapp.config;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.talent.v4.JobServiceClient;
import com.google.cloud.talent.v4.JobServiceSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class CloudTalentConfig {

    @Value("${TALENT_SOLUTION_CREDENTIALS}")
    private String credentialsPath;

    @Bean
    public JobServiceClient jobServiceClient() throws IOException {
        if (credentialsPath == null || credentialsPath.trim().isEmpty()) {
            throw new IOException("TALENT_SOLUTION_CREDENTIALS property is not set or is empty in application.properties.");
        }

        // Considering the credentials path starts with 'file:', you might need to remove that prefix to get the actual path
        if (credentialsPath.startsWith("file:")) {
            credentialsPath = credentialsPath.substring(5);
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));

        // Initialize the Cloud Talent Solution client with credentials
        return JobServiceClient.create(JobServiceSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build());
    }
}
