package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.TagEntity;
import br.com.alex.twitter.entities.TweetEntity;
import br.com.alex.twitter.vo.TweetByTagByUserIdiomVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository  extends JpaRepository<TagEntity, Long> {
     @Query("SELECT new br.com.alex.twitter.vo.TweetByTagByUserIdiomVO(count(tw.id), tg.tag, u.idiom) " +
            "FROM TweetEntity tw JOIN tw.tags tg JOIN tw.user u " +
             "GROUP BY tg.tag, u.idiom ")
     public List<TweetByTagByUserIdiomVO> listTweetAmountByTagsByUser();

     /*
    @Query(value = "SELECT tg.TAG hastTag, u.idiom, count(tw.id) tweets \n" +
            "FROM  tweet tw \n" +
            "INNER JOIN TAG tg on tg.id_tweet = tw.id \n" +
            "INNER JOIN USER u on tw.USER_ID = u.ID \n" +
            "GROUP BY tg.TAG, u.idiom", nativeQuery = true)
    public List<TweetByTagByUserIdiomVO> listTweetAmountByTagsByUsern();
    *
      */
}
