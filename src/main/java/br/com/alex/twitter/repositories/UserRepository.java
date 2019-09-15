package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    //@Query("SELECT u " +
     //       "FROM UserEntity u " +
      //      "ORDER BY u.followers DESC LIMIT 5 ")
    public List<UserEntity> findTop5ByOrderByFollowersDesc();
}
