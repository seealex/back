package br.com.alex.twitter.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tag;

    @ManyToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "ID_TWEET")
    private TweetEntity tweet;
}
