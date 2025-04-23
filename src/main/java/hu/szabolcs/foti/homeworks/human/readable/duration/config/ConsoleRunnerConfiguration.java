package hu.szabolcs.foti.homeworks.human.readable.duration.config;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationFormatterService;
import hu.szabolcs.foti.homeworks.human.readable.duration.ui.cli.DurationFormatterCLI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("console")
public class ConsoleRunnerConfiguration {
    @Bean
    public CommandLineRunner commandLineRunner(DurationFormatterService service, Environment env) {
        return new DurationFormatterCLI(service, env);
    }
}
