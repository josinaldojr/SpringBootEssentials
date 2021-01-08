package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DisplayName("Tests for Anime Repository")
@Log4j2
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when successful")
    void save_PersistAnime_WhenSuccessful(){
        Anime animeTobeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeTobeSaved);

        Assertions.assertThat(animeSaved).isNotNull();

        Assertions.assertThat(animeSaved.getId()).isNotNull();

        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeTobeSaved.getName());
    }

    @Test
    @DisplayName("Save updates anime when successful")
    void save_UpdatesAnime_WhenSuccessful(){
        Anime animeTobeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeTobeSaved);

        animeSaved.setName("Goblins Slayer");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();

        Assertions.assertThat(animeUpdated.getId()).isNotNull();

        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Delete removes anime when successful")
    void delete_RemovesAnime_WhenSuccessful(){
        Anime animeTobeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeTobeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Name returns list of anime when successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful(){
        Anime animeTobeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeTobeSaved);

        String name = animeSaved.getName();

        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty();

        Assertions.assertThat(animes).contains(animeSaved);
    }

    @Test
    @DisplayName("Find By Name returns empty list when no anime is found")
    void findByName_ReturnsEmptyList_WhenAnimeIsNotFound(){
        List<Anime> animes = this.animeRepository.findByName("Hello my name is");

        Assertions.assertThat(animes).isEmpty();
    }


    private Anime createAnime(){
        return Anime
                .builder()
                .name("Sakura")
                .build();
    }
}