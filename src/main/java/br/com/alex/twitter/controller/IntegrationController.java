package br.com.alex.twitter.controller;

import br.com.alex.twitter.service.IntegrationService;
import br.com.alex.twitter.vo.TweetByTagByUserIdiomVO;
import br.com.alex.twitter.vo.TweetCountByHourOfDayVO;
import br.com.alex.twitter.vo.UserTopFiveWithMoreFollowersVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    @GetMapping("/topFive")
    @ApiOperation(value = "Retorna 5 (cinco) usuários, que possuem mais seguidores")
    public ResponseEntity<?> topFiveByFollowers() {
        log.info("find to five users by Followers count in data base");
        List<UserTopFiveWithMoreFollowersVO> top = integrationService.topFiveFollowers();
        return new ResponseEntity<>(top, HttpStatus.OK);
    }

    @GetMapping("/byHour")
    @ApiOperation(value = "Retorna total de postagens, agrupadas por hora do dia")
    public ResponseEntity<?> totalPostByHours() {
        log.info("find total post by hour of day in data base");
        List<TweetCountByHourOfDayVO> tweets = integrationService.totalPostByHours();
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    @GetMapping("/byUserIdiomCountry")
    @ApiOperation(value = "Retorna total de postagens para cada uma das #tag por idioma/país do usuário que postou")
    public ResponseEntity<?> totalPostByUserIdiomCountry() {
        log.info("find total post by hours in data base");
        List<TweetByTagByUserIdiomVO> tags = integrationService.totalPostByUserIdiomCountry();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
