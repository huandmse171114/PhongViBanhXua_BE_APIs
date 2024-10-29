package com.phongvi.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GhnApis {
	
	@Value("${GHN.token}")
    private String token;

    @Value("${GHN.shop_id}")
    private int shopId;
    
    @Value("${GHN.url}")
    private String ghnUrl;
    
    @Value("${GHN.districtId}")
    private int districtId;

    @Value("${GHN.wardCode}")
    private String wardCode;

    private final int provinceId = 202;
    
    public String getWardList(String district_id) {
        String output = "";
        try {
            URL url = new URL(ghnUrl + "/public-api/master-data/ward?district_id");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("token", token);
            conn.setDoOutput(true);

            String requestBody = "{\"district_id\":" + district_id + "}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestBody.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    output += line;
                }
            }
            conn.disconnect();
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getDistrictList() {
        String output = "";
        try {
            URL url = new URL(ghnUrl + "/public-api/master-data/district");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("token", token);
            conn.setDoOutput(true);

            String requestBody = "{\"province_id\":" + provinceId + "}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestBody.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    output += line;
                }
            }
            conn.disconnect();
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
        return output;
    }
    
}
