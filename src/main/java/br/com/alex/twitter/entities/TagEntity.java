package br.com.alex.twitter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tag;

    //@ManyToOne(cascade = {
    //        CascadeType.ALL
    //})
    //@JoinColumn(name = "ID_TWEET")
    //private TweetEntity tweet;
}
