package hu.szabolcs.foti.homeworks.human.readable.duration.ui.rest.controller;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationFormatterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("rest")
@RequiredArgsConstructor
public class DurationFormatterController {

    private final DurationFormatterService service;

    @GetMapping("/api/{duration}")
    public ResponseEntity<String> getHumanReadableFormat(@PathVariable String duration) {
        //accept malformed input for proper error handling
        int seconds;
        if (!duration.matches("\\d+")) {
            return ResponseEntity.status(418).body(String.format("Duration should be an integer. '%s' is not an integer.", duration));
        } else {
            //overflow check
            try {
                seconds = Integer.parseInt(duration);
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body(String.format("'%s' is too large for an integer", duration));
            }
        }

        return ResponseEntity.ok(service.formatDuration(seconds));
    }
}
