package com.hamzabekkaoui.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    public static String callApi(String apiUrl, String requestMethod, Object requestBody) throws IOException {

        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = null;

        try {
            URL url = new URL(apiUrl);

            // Create HttpURLConnection
            connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod(requestMethod);

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");


            // Get response code
            int responseCode = connection.getResponseCode();
            if(responseCode != 200) throw new RuntimeException("something went wrong ...");

            // Read response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } finally {
            // Close connection
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }


}
