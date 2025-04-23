package hu.szabolcs.foti.homeworks.human.readable.duration.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("console")
public class ConsoleRunnerConfiguration {
    @Bean
    public CommandLineRunner run() {
        return args -> {};
    }
}
