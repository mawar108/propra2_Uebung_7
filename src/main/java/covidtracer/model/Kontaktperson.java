package covidtracer.model;

import lombok.Value;

@Value
public class Kontaktperson {
  private String nachname;
  private String vorname;
  private String kontaktinformationen;
}
