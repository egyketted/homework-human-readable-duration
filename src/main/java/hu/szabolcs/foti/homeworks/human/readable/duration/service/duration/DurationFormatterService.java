package hu.szabolcs.foti.homeworks.human.readable.duration.service.duration;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponent;
import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.model.DurationComponentValue;
import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.parser.DurationComponentParser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DurationFormatterService {

    private final Map<DurationComponent, DurationComponentParser> componentToParser;

    public DurationFormatterService(List<DurationComponentParser> registeredParsers) {
        this.componentToParser = registeredParsers.stream()
                .collect(Collectors.toMap(DurationComponentParser::getParsedType, Function.identity()));
    }

    public String formatDuration(int seconds) {
        if (seconds < 0) {
            return String.format("Invalid input %d < 0", seconds);
        }

        Map<DurationComponent, DurationComponentValue> parsedDurationComponents = new HashMap<>();

        componentToParser.entrySet().stream().forEach(entry ->
            parsedDurationComponents.put(entry.getKey(), entry.getValue().getParsedValue(seconds))
        );

        // Both List and Array are ordered, enum values are returned in the order they are defined in

        List<String> componentsGreaterThanZero = new LinkedList<>();
        for (DurationComponent component : DurationComponent.values()) {
            DurationComponentValue durationComponentValue = parsedDurationComponents.get(component);
            if (durationComponentValue.getValue() > 0) {
                String componentName = component.name().toLowerCase();

                if (durationComponentValue.isPlural()) {
                    componentName += "s";
                }

                String stringValue = String.format("%d %s", durationComponentValue.getValue(), componentName);
                componentsGreaterThanZero.add(stringValue);
            }
        }

        if (componentsGreaterThanZero.isEmpty()) {
            return "";
        } else if (componentsGreaterThanZero.size() == 1) {
            return componentsGreaterThanZero.getFirst();
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < componentsGreaterThanZero.size() - 2; i++) {
                result.append(componentsGreaterThanZero.get(i)).append(", ");
            }

            String last = componentsGreaterThanZero.getLast();
            String beforeLast = componentsGreaterThanZero.get(componentsGreaterThanZero.size() - 2);
            result.append(beforeLast).append(" and ").append(last);

            return result.toString();
        }
    }
}
