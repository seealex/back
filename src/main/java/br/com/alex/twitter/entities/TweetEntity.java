package br.com.alex.twitter.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetEntity {
    @Id
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private UserEntity user;

    private Set<TagEntity> tags;
}

