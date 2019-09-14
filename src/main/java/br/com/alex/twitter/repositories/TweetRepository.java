package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.TweetEntity;
import br.com.alex.twitter.vo.TweetCountByHourOfDayVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends MongoRepository<TweetEntity, Long> {
    @Query("SELECT new br.com.alex.twitter.vo.TweetCountByHourOfDayVO(count (1), YEAR(t.createdAt), MONTH(t.createdAt), DAY(t.createdAt), HOUR(t.createdAt)) " +
            "FROM TweetEntity t GROUP BY YEAR(t.createdAt), MONTH(t.createdAt), DAY(t.createdAt), HOUR(t.createdAt) " +
            "ORDER BY YEAR(t.createdAt), MONTH(t.createdAt), DAY(t.createdAt), HOUR(t.createdAt)")
    public List<TweetCountByHourOfDayVO> listTweetCountByHourOfDay();
}
