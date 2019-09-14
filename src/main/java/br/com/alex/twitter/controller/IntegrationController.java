package br.com.alex.twitter.controller;

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
    private TwitterService twitterService;

    @GetMapping("/{tag}")
    public ResponseEntity<?> findByTag(@PathVariable("tag") String tag) throws IOException {
        log.info("by tag:" + tag);
        return ResponseEntity.ok(twitterService.findByTag(tag));
    }

}
