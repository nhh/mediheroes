package com.mediheroes.mediheroes.configuration;

import com.bugsnag.Bugsnag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BugsnagConfig {

    private final String apiKey;

    public BugsnagConfig(
        @Value("${mediheroes.bugsnag.api-key}") String apiKey
    ) {
        this.apiKey = apiKey;
    }

    @Bean
    public Bugsnag bugsnag() {
        return new Bugsnag(this.apiKey);
    }

}
