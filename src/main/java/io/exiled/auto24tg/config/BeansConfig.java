package io.exiled.auto24tg.config;

import io.exiled.auto24tg.service.impl.ExtractPageContentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public ExtractPageContentServiceImpl getExtractPageService() {
        return new ExtractPageContentServiceImpl();
    }
}
