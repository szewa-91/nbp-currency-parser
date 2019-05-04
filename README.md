# Currency parser for NBP service

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/59ac121ddcc04bd88d76af3035ae3d87)](https://app.codacy.com/app/marcin91.szewczyk/nbp-currency-parser?utm_source=github.com&utm_medium=referral&utm_content=szewa-91/nbp-currency-parser&utm_campaign=Badge_Grade_Settings)

This application retrieves files with currency data from NBP servers (based
on instructions at [this page](https://www.nbp.pl/home.aspx?f=/kursy/instrukcja_pobierania_kursow_walut.html).

## Run
The application is running on Java 11.

If you want a fat jar (containing all dependencies) to be created, execute `mvn clean compile assembly:single`

Then, to run application, run:
`java -cp nbp-currency-parser-1.0-SNAPSHOT-jar-with-dependencies.jar pl.parser.nbp.MainClass EUR 2013-01-28 2013-01-31`

Where three arguments are respectively: currency for which we want to see statistics, start date and end date.

The application contains retry mechanism.