package covidtracer.controller;

import covidtracer.model.Index;
import covidtracer.model.KontaktListe;
import covidtracer.model.Kontaktperson;
import covidtracer.persistence.KontaktListeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
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

		when(repo.findAll()).thenReturn(List.of(klist));

		mvc.perform(get("/"))
				.andDo(print())
				.andExpect(content().string(containsString("Hans")))
				.andExpect(content().string(containsString("Peter")))
				.andExpect(content().string(containsString("Michael")))
				.andExpect(content().string(containsString("Fuchs")))
				.andExpect(status().isOk());
	}

}