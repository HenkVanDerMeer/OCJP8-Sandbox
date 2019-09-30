Back to [index](README.md)

&lt; [G. Lambda Cookbook](G.md)

---
## H. Use Java SE 8 Date/Time API
#### 1. Create and manage date- and time-based events, including a combination of date and time in a single object, by using LocalDate, LocalTime, LocalDateTime, Instant, Period, and Duration

The `java.time` package contains the following Java 8 date and time classes:
* `LocalDate`: Contains just a date (e.g. '2019-10-01')
* `LocalTime`: Contains just a time (e.g. '09:00:00.000')
* `LocalDateTime`: Contains both a date and time (e.g. '2019-10-01T09:00:00.000')
* `ZonedDateTime`: Contains both a date and time including a time zone (e.g. '2019-10-01T09:00:00.000+02:00[Europe/Berlin]')

Each of these have a static method `now()` which gives the current date and/or time:
```
System.out.println(LocalDate.now());
System.out.println(LocalTime.now());
System.out.println(LocalDateTime.now());
System.out.println(ZonedDateTime.now());
```

...
#### 2. Work with dates and times across time zones and manage changes resulting from daylight savings, including Format date and times values
...
#### 3. Define, create, and manage date- and time-based events using Instant, Period, Duration, and TemporalUnit
...

---
Back to [index](README.md)

&lt; [G. Lambda Cookbook](G.md)
