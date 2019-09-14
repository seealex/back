package br.com.alex.twitter.vo;


import lombok.Data;

@Data
public class TweetCountByHourOfDayVO {
    private Long amount;
    private String date;

    public TweetCountByHourOfDayVO(Long amount, String dataFormated) {
        this.amount = amount;
        this.date = dataFormated;
    }

    public TweetCountByHourOfDayVO(Long amount, Integer year, Integer month, Integer day, Integer hour) {
        this.amount = amount;
        this.date = String.format("%sh %d/%d/%d", hour, day, month = month, year, hour);
    }
}
