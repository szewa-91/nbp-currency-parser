[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7805dfa371f44ad48290276e581c0b3d)](https://www.codacy.com/app/marcin91.szewczyk/nbp-currency-parser?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=szewa-91/nbp-currency-parser&amp;utm_campaign=Badge_Grade)

# Currency parser for NBP service
This application retrieves files with currency data from NBP servers (based
on instructions at [this page](https://www.nbp.pl/home.aspx?f=/kursy/instrukcja_pobierania_kursow_walut.html).

## Run
The application is running on Java 11.

If you want a fat jar (containing all dependencies) to be created, execute `mvn clean compile assembly:single`

Then, to run application, execute:
`java -cp target/nbp-currency-parser-1.0-SNAPSHOT-jar-with-dependencies.jar pl.parser.nbp.MainClass EUR 2013-01-28 2013-01-31`

Where three arguments are respectively: currency for which we want to see statistics, start date and end date.

The application contains retry mechanism.
