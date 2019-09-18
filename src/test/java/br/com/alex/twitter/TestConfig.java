package br.com.alex.twitter;

import br.com.alex.twitter.configuration.PreLoadDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ComponentScan(basePackages = {"br.com.alex.twitter"}, excludeFilters={
@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value= PreLoadDatabase.class)})
@ActiveProfiles("test")
public class TestConfig {

}