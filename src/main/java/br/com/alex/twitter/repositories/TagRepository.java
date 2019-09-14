package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.TagEntity;
import br.com.alex.twitter.entities.TweetEntity;
import br.com.alex.twitter.vo.TweetByTagByUserIdiomVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository  extends MongoRepository<TagEntity, Long> {
    @Query("SELECT new br.com.alex.twitter.vo.TweetByTagByUserIdiomVO(count(tag.tweet), tag.tag, tag.tweet.user.idiom) " +
            "FROM TagEntity tag GROUP BY tag.tag, tag.tweet.user.idiom ORDER BY 1, tag.tweet.user.idiom")
    public List<TweetByTagByUserIdiomVO> listTweetAmountByTagsByUser();
}
