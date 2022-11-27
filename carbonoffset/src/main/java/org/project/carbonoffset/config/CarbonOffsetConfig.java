package org.project.carbonoffset.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CarbonOffsetConfig {

    @Bean
    public Map<String, Double> carbonOffsetMap(){
        return new HashMap<String, Double>();
    }
}
