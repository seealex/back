package br.com.alex.twitter.service;

import br.com.alex.twitter.entities.UserEntity;
import br.com.alex.twitter.repositories.TagRepository;
import br.com.alex.twitter.repositories.TweetRepository;
import br.com.alex.twitter.repositories.UserRepository;
import br.com.alex.twitter.vo.TweetByTagByUserIdiomVO;
import br.com.alex.twitter.vo.TweetCountByHourOfDayVO;
import br.com.alex.twitter.vo.UserTopFiveWithMoreFollowersVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class IntegrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TweetRepository tweetRepository;

    public List<TweetByTagByUserIdiomVO> totalPostByUserIdiomCountry() {
    List<TweetByTagByUserIdiomVO> tags = tagRepository.listTweetAmountByTagsByUser();
    return tags;
    }

    public List<TweetCountByHourOfDayVO> totalPostByHours() {
        List<TweetCountByHourOfDayVO> tweets = tweetRepository.listTweetCountByHourOfDay();
        return tweets;
    }

    public List<UserTopFiveWithMoreFollowersVO> topFiveFollowers() {
        List<UserEntity> users = userRepository.findTop5ByOrderByFollowersDesc();

        List<UserTopFiveWithMoreFollowersVO> usersVO = new ArrayList<>();
        users.forEach(u ->
                usersVO.add(new UserTopFiveWithMoreFollowersVO(u.getId(), u.getScreenName(), u.getFollowers()))
        );

        return null;
    }
}
