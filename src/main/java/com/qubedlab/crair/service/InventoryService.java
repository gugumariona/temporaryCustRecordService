package com.qubedlab.crair.service;





import com.qubedlab.crair.models.QueryParameters;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
public class InventoryService {

    RestTemplate restTemplate = new RestTemplate();


    private static Object getObject(byte[] byteArr) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
        ObjectInput in = new ObjectInputStream(bis);
        return in.readObject();
    }


    @PostMapping("/post")
    public Resource get(){
        String url = "https://qubedlab-dealer-inventory.herokuapp.com/extract/inventory";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("queryId","IVEH_Delta")
                .queryParam("deltaDate","10/10/2020")
        .queryParam("qparamInvCompany","77")
        .queryParam("dealerId","3PADEVWB");


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(Collections.singletonMap("key", "valid"), httpHeaders);

        Resource resource = restTemplate.postForObject(builder.toUriString(), request, Resource.class);
log.info("hope"+resource);
        return resource;
    }

}
