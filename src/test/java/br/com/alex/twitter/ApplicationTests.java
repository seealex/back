package br.com.alex.twitter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestConfig.class, Application.class})
@ActiveProfiles(value = "test")
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
