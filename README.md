# homework-human-readable-duration
A springboot application with multiple runtime interfaces which formats a duration, given as a number of seconds, in a human-friendly way.  The function must accept a non-negative integer. If it is zero, it just returns "now". Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds. [Original task description](https://www.codewars.com/kata/human-readable-duration-format)

## Usage

### Console Mode

To run the application in console mode:

```bash
./gradlew clean build'
export SPRING_PROFILES_ACTIVE=console
java -jar build/libs/human-readable-duration-0.0.1.jar 
```

gradlew highjacks System.in so the standard method:

```bash
./gradlew bootRun --args='--spring.profiles.active=console'
```

does not work in console mode


In console mode, you'll be presented with a simple CLI interface:
1. Choose option 1 to format a duration
2. Enter a duration in seconds (must be a non-negative integer)
3. View the human-readable formatted result
4. Choose option 2 to exit the application

### REST API Mode

To run the application as a REST API:

```bash
./gradlew bootRun --args='--spring.profiles.active=rest'
```

Once started, the REST API will be available at:
- `http://localhost:8080/api/{duration}` where `{duration}` is the number of seconds to format

Example:
- `GET http://localhost:8080/api/3661` returns `"1 hour, 1 minute and 1 second"`
- `GET http://localhost:8080/api/0` returns `"now"`

Swagger UI is also available at `http://localhost:8080/swagger-ui.html` for interactive API exploration.
