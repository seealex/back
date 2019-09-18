package br.com.alex.twitter.service;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import br.com.alex.twitter.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class TwitterServiceTest {

    private final String HASHTAG = "#twitter";

    @Autowired
    private TwitterService twitterService;

    @Test
    public void getStatusByHashtagsTest() {
        List<String> hash = new ArrayList<String>();
        hash.add(HASHTAG);

        List<Status> statuses = twitterService.getStatusByHashtags(hash);

        assertThat(statuses.size(), not(0));

    }

    @Test
    public void findByTagTest(){
        List<Status> statuses = twitterService.findByTag(HASHTAG);

        assertThat(statuses.size(), not(0));

    }

}