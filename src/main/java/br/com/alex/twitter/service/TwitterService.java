package br.com.alex.twitter.service;

import br.com.alex.twitter.dto.TwitteDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Log4j2
public class TwitterService {

    private final String API_KEY = "";
    private final String API_SECRET_KEY = "";
    private final String HOST = "https://api.twitter.com/oauth2/token";
    private final String HOST_SEARCH = "https://api.twitter.com/1.1/search/tweets.json?q=";
    private String token;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ModelMapper modelMapper;

    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
            set( "Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        }};
    }

    private HttpHeaders createHeadersTwitter(String token){
        return new HttpHeaders() {{
            set( "Authorization", "Bearer " + token );
            set( "Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        }};
    }

    @PostConstruct
    public void getAccessToken(){
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.set("grant_type", "client_credentials");

        HttpEntity entity = new HttpEntity<>(bodyMap, createHeaders(API_KEY, API_SECRET_KEY));

        ResponseEntity<Map> response = restTemplate.exchange(HOST, HttpMethod.POST, entity, Map.class);

        this.token = (String) Objects.requireNonNull(response.getBody()).get("access_token");

        log.info(response.getStatusCode());
    }

    public Object findByTag(String tag) throws IOException {
        HttpEntity entity = new HttpEntity<>(null, createHeadersTwitter(token));
        ResponseEntity<Map> response = restTemplate.exchange(HOST_SEARCH + encodeTag("#"+tag) + "&count=100", HttpMethod.GET, entity, Map.class);

        //ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new StringReader(response.getBody().get("statuses").toString()));
        reader.setLenient(true);

        Type collectionType = new TypeToken<Collection<TwitteDto>>(){}.getType();

        Collection<TwitteDto> obj = gson.fromJson(reader, collectionType);
        //JSON file to Java object
        //List<TwitteDto> obj = mapper.readValue(response.getBody().get("statuses").toString(), new TypeReference<List<TwitteDto>>(){});
        System.out.println(obj);

        return response.getBody().get("statuses");

    }

    private static String encodeTag(String tag) {
        try {
            return URLEncoder.encode(tag, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}