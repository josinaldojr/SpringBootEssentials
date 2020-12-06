package academy.devdojo.springboot2.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import academy.devdojo.springboot2.domain.Anime;

@Service
public class AnimeService {
	public List<Anime> listAll() {
		 return Arrays.asList(new Anime(1L, "Boku No Hero"), new Anime(2L, "Berserk"));
	}
}
