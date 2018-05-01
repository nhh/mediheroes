package com.mediheroes.mediheroes.configuration;

import com.bugsnag.Bugsnag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BugsnagConfig {

    @Bean
    public Bugsnag bugsnag() {
        return new Bugsnag("1fe10f647a3646b90e4dcc201909c5df");
    }

}
