package br.com.alex.twitter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tweet")
public class TweetEntity {
    @Id
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ID_TWEET")
    private Set<TagEntity> tags = new HashSet<TagEntity>();
}

