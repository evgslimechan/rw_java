package com.slimechan.raceway_system.manages;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

public class Http {

    public static JSONObject sendPost(String url, Map<String, Object> params) throws URISyntaxException {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        params.forEach((key,value)->{
            headers.put(key, new ArrayList<String>(){{add(value.toString());}});
        });
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String json = template.postForObject(new URI(url), entity, String.class);
        if(json==null) return new JSONObject();
        else return new JSONObject(json);
    }

}
