package br.com.alex.twitter.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTopFiveWithMoreFollowersVO {
    private Long id;
    private String screenName;
    private Long followers;
}