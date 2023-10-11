package com.karrierekind.karrierekindapp.service;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.jobs.v4.CloudTalentSolution;
import com.google.api.services.jobs.v4.CloudTalentSolutionScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;

import java.util.Collections;

;


@Service
public class GoogleCloudTalentService {

    private static final JsonFactory JSON_FACTORY = new GsonFactory();
    private static final NetHttpTransport NET_HTTP_TRANSPORT = new NetHttpTransport();
    private static final String DEFAULT_PROJECT_ID =
            "projects/" + System.getenv("GOOGLE_CLOUD_PROJECT");

    private static CloudTalentSolution talentSolutionClient =
            createTalentSolutionClient(generateCredential());

    private static CloudTalentSolution createTalentSolutionClient(GoogleCredentials credential) {
        String url = "https://jobs.googleapis.com";

        HttpRequestInitializer requestInitializer =
                request -> {
                    new HttpCredentialsAdapter(credential).initialize(request);
                    request.setConnectTimeout(60000); // 1 minute connect timeout
                    request.setReadTimeout(60000); // 1 minute read timeout
                };

        return new CloudTalentSolution.Builder(NET_HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                .setApplicationName("JobServiceClientSamples")
                .setRootUrl(url)
                .build();
    }

    private static GoogleCredentials generateCredential() {
        try {
            // Credentials could be downloaded after creating service account
            // set the `GOOGLE_APPLICATION_CREDENTIALS` environment variable, for example:
            // export GOOGLE_APPLICATION_CREDENTIALS=/path/to/your/key.json
            return GoogleCredentials.getApplicationDefault()
                    .createScoped(Collections.singleton(CloudTalentSolutionScopes.JOBS));
        } catch (Exception e) {
            System.out.println("Error in generating credential");
            throw new RuntimeException(e);
        }
    }

    public static CloudTalentSolution getTalentSolutionClient() {
        return talentSolutionClient;
    }

}
