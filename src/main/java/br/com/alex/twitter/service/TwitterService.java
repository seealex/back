package br.com.alex.twitter.service;

import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Log4j2
public class TwitterService {

    private final String API_KEY = "HKGtpFhgP15wfRrTdNkfpwiYB";
    private final String API_SECRET_KEY = "hTA1OhLLwetfLslCiNNmBhaGlGpCZHYHHsUPM1bN4f2AXVGruN";
    private final String ACCESS_TOKEN = "27421567-9hbVmOA1zbjOZ2sDIbrushDw27R9hQleCcxXj1iws";
    private final String ACCESS_TOKEN_SECRET = "zexVgHJxjhPusERjWVzHvAU3olDY3TpXkQHwuBY8xAXP9";

    private Twitter configurationForTwitter() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(API_KEY)
                .setOAuthConsumerSecret(API_SECRET_KEY)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());

        return twitterFactory.getInstance();
    }

    public List<Status> getStatusByHashtags(List<String> hashtags) {
        List<Status> statuses = new ArrayList<Status>();
        for (String ht : hashtags) {
            if (!StringUtils.isEmpty(ht))
                statuses.addAll(findByTag(ht));
        }

        return statuses;
    }

    public List<Status> findByTag(String tag){
        List<Status> statuses = null;

        if (StringUtils.isEmpty(tag))
            throw new RuntimeException("hashtag can not by empty");

        log.info(String.format("fetching posts to hashtag [%s]", tag));
        try {
            Query query = new Query(tag);
            query.count(100);
            QueryResult queryResult = configurationForTwitter().search(query);
            statuses = queryResult.getTweets();
        } catch (TwitterException e) {
            log.error(String.format("error fetching posts to hashtag [%s]", tag), e);
        }

        return statuses;
    }

}