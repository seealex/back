package br.com.alex.twitter.controller;

import br.com.alex.twitter.service.IntegrationService;
import br.com.alex.twitter.service.TwitterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Log4j2
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    @GetMapping()
    public ResponseEntity<?> topFiveByFollowers() throws IOException {
        log.info("find to five users by Followers count in data base");
        return ResponseEntity.ok(integrationService.topFiveFollowers());
    }

    @GetMapping()
    public ResponseEntity<?> totalPostByHours() throws IOException {
        log.info("find total post by hours in data base");
        return ResponseEntity.ok(integrationService.totalPostByHours());
    }

    @GetMapping()
    public ResponseEntity<?> totalPostByUserIdiomCountry() throws IOException {
        log.info("find total post by hours in data base");
        return ResponseEntity.ok(integrationService.totalPostByUserIdiomCountry());
    }

}
