package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
    //@Query("SELECT new br.com.bluelimit.vo.TweetCountByHourOfDayVO(count (1), YEAR(t.createdAt), MONTH(t.createdAt), DAY(t.createdAt), HOUR(t.createdAt)) " +
    //        "FROM TweetEntity t GROUP BY YEAR(t.createdAt), MONTH(t.createdAt), DAY(t.createdAt), HOUR(t.createdAt) " +
    //        "ORDER BY YEAR(t.createdAt), MONTH(t.createdAt), DAY(t.createdAt), HOUR(t.createdAt)")
    //public List<TweetCountByHourOfDayVO> listTweetCountByHourOfDay();
}
