package com.qubedlab.crair.service;


import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class InventoryTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://qubedlab-dealer-inventory.herokuapp.com/extract/inventory");
        Map<String,Object> params = new LinkedHashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        params.put("queryId", "IVEH_Delta");
        params.put("deltaDate", dateFormat.format(date));
        params.put("qparamInvCompany", "77");
        params.put("dealerId", "3PADEVWB");
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        //converting byte [] to object



     log.info("This is the data"+postDataBytes.toString());
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
    }
}
