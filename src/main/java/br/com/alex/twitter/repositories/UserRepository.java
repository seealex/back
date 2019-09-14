package br.com.alex.twitter.repositories;

import br.com.alex.twitter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public List<UserEntity> findTop5ByOrderByFollowersDesc();
}
