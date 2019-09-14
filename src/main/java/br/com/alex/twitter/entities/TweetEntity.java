package br.com.alex.twitter.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Data
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

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private Set<TagEntity> tags;
}

