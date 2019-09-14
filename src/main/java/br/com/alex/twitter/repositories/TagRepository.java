package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, String> {
    //@Query("SELECT new br.com.bluelimit.vo.TweetByTagByUserIdiomVO(count(tag.tweet), tag.tag, tag.tweet.user.idiom) " +
    //        "FROM TagEntity tag GROUP BY tag.tag, tag.tweet.user.idiom ORDER BY 1, tag.tweet.user.idiom")
    //public List<TweetByTagByUserIdiomVO> listTweetAmountByTagsByUser();
}
