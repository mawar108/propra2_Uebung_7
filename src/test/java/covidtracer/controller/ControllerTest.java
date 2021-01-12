package covidtracer.controller;

import covidtracer.model.Index;
import covidtracer.model.KontaktListe;
import covidtracer.model.Kontaktperson;
import covidtracer.persistence.KontaktListeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(Webpage.class)
public class ControllerTest {
	private KontaktListe klist;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private KontaktListeRepository repo;

	@BeforeEach
	void setup() {
		klist= new KontaktListe();
		klist.setIndex(new Index("Hans", "Peter", LocalDate.now()));
		klist.addKontakt(new Kontaktperson("Michael", "Fuchs","abc"));
	}

	@Test
	void indexTest() throws Exception{
		when(repo.findAll()).thenReturn(List.of(klist));
		mvc.perform(get("/"))
				.andDo(print())
				.andExpect(content().string(containsString("Hans")))
				.andExpect(content().string(containsString("Peter")))
				.andExpect(status().isOk());
	}

	@Test
	void getListById() throws Exception {
		when(repo.findById(1L)).thenReturn(Optional.of(klist));
		mvc.perform(get("/liste/1"))
				.andDo(print())
				.andExpect(content().string(containsString("Hans")))
				.andExpect(content().string(containsString("Peter")))
				.andExpect(content().string(containsString("Michael")))
				.andExpect(content().string(containsString("Fuchs")))
				.andExpect(status().isOk());
	}

	@Test
	void erzeugeListe() throws Exception {
		mvc.perform(post("/")
			.param("vorname", "Hans")
			.param("nachname", "Peter"))
		.andExpect(status().is3xxRedirection());

		verify(repo).save(any());
	}



}
