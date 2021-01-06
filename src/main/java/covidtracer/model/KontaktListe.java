package covidtracer.model;

import covidtracer.stereotypes.AggregateRoot;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;

@AggregateRoot
public class KontaktListe {

  @Id
  private Long id = null;
  private Index index = null;
  private Set<Kontaktperson> kontakte = new HashSet<>();
  private LocalDateTime changed;


  public void addKontakt(Kontaktperson person) {
    kontakte.add(person);
    touch();
  }

  private void touch() {
    changed = LocalDateTime.now();
  }


  public Index getIndex() {
    return index;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    if (id == null) {
      this.id = id;
    }
    else {
      throw new IllegalStateException("ID ist schon gesetzt");
    }
  }

  public void setIndex(Index index) {
    if (this.index == null) {
      this.index = index;
      touch();
    }
    else {
      throw new IllegalStateException("Index ist schon gesetzt");
    }
  }

  public Set<Kontaktperson> getKontakte() {
    return kontakte;
  }



  public String getChanged() {
    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    String formated = changed.format(formatter);
    return formated;
  }

  public void removeKontakt(Kontaktperson kontaktperson) {
    kontakte.remove(kontaktperson);
    touch();
  }

  public int size() {
    return kontakte.size();
  }
}
