package br.com.alex.twitter.vo;


import lombok.Data;

@Data
public class TweetByTagByUserIdiomVO {
    private Long tweets;
    private String hastTag;
    private String idiom;

    public TweetByTagByUserIdiomVO(Long tweets, String hastTag, String idiom) {
        this.tweets = tweets;
        this.hastTag = String.format("#%s", hastTag);
        this.idiom = idiom;
    }
}
