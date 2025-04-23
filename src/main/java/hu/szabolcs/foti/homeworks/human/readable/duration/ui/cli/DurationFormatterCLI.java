package hu.szabolcs.foti.homeworks.human.readable.duration.ui.cli;

import hu.szabolcs.foti.homeworks.human.readable.duration.service.duration.DurationFormatterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Profile("console")
public class DurationFormatterCLI implements CommandLineRunner {

    private final DurationFormatterService durationFormatterService;
    private boolean running = true;

    public DurationFormatterCLI(DurationFormatterService durationFormatterService) {
        this.durationFormatterService = durationFormatterService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Duration Formatter CLI");
        System.out.println("=====================");
        try(Scanner scanner = new Scanner(System.in)) {
            while (running) {
                try {
                    displayMenu();
                    processUserInput(scanner);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    running = false;
                }
            }
        }

        System.out.println("Application terminated. Goodbye!");
    }

    private void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Format duration in seconds");
        System.out.println("2. Exit");
        System.out.print("> ");
    }

    private void processUserInput(Scanner scanner) {
        String input = scanner.nextLine().trim();

        try {
            int option = Integer.parseInt(input);
            switch (option) {
                case 1:
                    handleDurationFormatting(scanner);
                    break;
                case 2:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private void handleDurationFormatting(Scanner scanner) {
        System.out.print("Enter duration in seconds (integer only): ");
        String input = scanner.nextLine().trim();

        try {
            int seconds = Integer.parseInt(input);
            String formattedDuration = durationFormatterService.formatDuration(seconds);

            if (formattedDuration.isEmpty()) {
                System.out.println("Result: now");
            } else {
                System.out.println("Result: " + formattedDuration);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input must be an integer.");
        }
    }
} 