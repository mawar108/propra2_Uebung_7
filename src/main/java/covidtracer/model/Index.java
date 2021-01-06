package covidtracer.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import lombok.Value;

@Value
public class Index {

  private final String nachname;
  private final String vorname;
  private LocalDate erstbefund;

  public Index(String nachname, String vorname, LocalDate erstbefund) {
    this.nachname = nachname;
    this.vorname = vorname;
    this.erstbefund = erstbefund;
  }

  public long getTage() {
    return Period.between(erstbefund, LocalDate.now()).getDays();
  }

}
