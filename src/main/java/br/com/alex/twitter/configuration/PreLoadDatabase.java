package br.com.alex.twitter.configuration;

import br.com.alex.twitter.entities.TagEntity;
import br.com.alex.twitter.entities.TweetEntity;
import br.com.alex.twitter.entities.UserEntity;
import br.com.alex.twitter.repositories.TagRepository;
import br.com.alex.twitter.repositories.TweetRepository;
import br.com.alex.twitter.repositories.UserRepository;
import br.com.alex.twitter.service.TwitterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Configuration
public class PreLoadDatabase {

    @Autowired
    private TwitterService twitterService;

    @Bean
    @Order(value = 1)
    public CommandLineRunner collectMessagesAndSave(TweetRepository tweetRepository, TagRepository tagRepository) {
        return args -> {
            List<String> hash = new ArrayList<String>();
            hash.add("#openbanking");
            hash.add("#apifirst");
            hash.add("#devops");
            hash.add("#cloudfirst");
            hash.add("#microservices");
            hash.add("#apigateway");
            hash.add("#oauth");
            hash.add("#swagger");
            hash.add("#raml");
            hash.add("#openapis");

            List<Status> statuses = twitterService.getStatusByHashtags(hash);

            log.info("collect form Twitter service started");

            statuses.parallelStream().forEach(s -> {
                        TweetEntity tweet = new TweetEntity();
                        tweet.setId(s.getId());
                        tweet.setCreatedAt(s.getCreatedAt());
                        tweet.setUser(new UserEntity(s.getUser().getId(), String.format("@%s", s.getUser().getScreenName()), Long.valueOf(s.getUser().getFollowersCount()), s.getUser().getLang(), null));
                        tweetRepository.save(tweet);

                        HashtagEntity tags[] = s.getHashtagEntities();
                        Arrays.stream(tags).filter(x -> hash.contains("#" + x.getText().toLowerCase())).forEach(h -> {
                                    TagEntity tagEntity = new TagEntity();
                                    tagEntity.setTag(h.getText().toLowerCase());
                                    tagEntity.setTweet(tweet);

                                    tagRepository.save(tagEntity);
                                }
                        );
                    }
            );

            log.info("collect and save finished");
        };
    }

    @Bean
    @Order(value = 2)
    public CommandLineRunner messagesSumAndSave(TweetRepository tweetRepository, TagRepository tagRepository, UserRepository userRepository) {
        return args -> {
            List<UserEntity> userEntities = userRepository.findTop5ByOrderByFollowersDesc();
            
        };
    }
}