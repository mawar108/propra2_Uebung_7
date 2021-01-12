package Controller;

import covidtracer.controller.Webpage;
import covidtracer.model.Index;
import covidtracer.model.KontaktListe;
import covidtracer.model.Kontaktperson;
import covidtracer.persistence.KontaktListeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@WebMvcTest(Webpage.class)
public class ControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private KontaktListeRepository repo;

	@Test
	void indexTest() throws Exception{
		var klist= new KontaktListe();
		klist.setIndex(new Index("Hans", "Peter", LocalDate.now()));
		klist.addKontakt(new Kontaktperson("Michael", "Fuchs","abc"));
	}

}
