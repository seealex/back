package br.com.alex.twitter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    private Long id;
    private String screenName;
    private Long followers;
    private String idiom;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private Set<TweetEntity> tweets;
}
