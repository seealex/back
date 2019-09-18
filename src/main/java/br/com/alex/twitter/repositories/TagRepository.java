package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.TagEntity;
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

}
