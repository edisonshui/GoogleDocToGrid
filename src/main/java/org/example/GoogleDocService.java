package org.example;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.DocsScopes;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.util.List;

public class GoogleDocService {
    private static final String APPLICATION_NAME = "GridBuilder";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = List.of(DocsScopes.DOCUMENTS);
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    public static Docs getDocService() throws Exception {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        InputStream in = GoogleDocService.class.getResourceAsStream("/client_secret.json");
        if (in == null) {
            in = new java.io.FileInputStream("client_secret.json"); // fallback
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
