package br.com.alex.twitter.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class TwitterService {

    @Value("${api.key}")
    private String API_KEY;

    @Value("${api.key.secret}")
    private String API_SECRET_KEY;

    @Value("${api.access.token}")
    private String ACCESS_TOKEN;

    @Value("${api.access.token.secret}")
    private String ACCESS_TOKEN_SECRET;

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