package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.TweetEntity;
import br.com.alex.twitter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends MongoRepository<UserEntity, Long> {
    public List<UserEntity> findTop5ByOrderByFollowersDesc();
}
