package canciones.cleanarch.repository;

import canciones.cleanarch.Repository.InMemorySongRepository;
import canciones.cleanarch.model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemorySongRepositoryTest {

    private InMemorySongRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemorySongRepository();
    }

    @Test
    void findAll_shouldReturnAllSongs() {
        // Given
        int initialSize = repository.findAll().size();

        // When
        repository.save(new Song(null, "Test Song 1", "Artist 1", "Album 1", 2020, "url1"));
        repository.save(new Song(null, "Test Song 2", "Artist 2", "Album 2", 2021, "url2"));

        // Then
        assertEquals(initialSize + 2, repository.findAll().size());
    }

    @Test
    void findById_shouldReturnSong_whenSongExists() {
        // Given
        Song newSong = new Song(null, "Test Song 3", "Artist 3", "Album 3", 2022, "url3");
        Song savedSong = repository.save(newSong);

        // When
        var foundSong = repository.findById(savedSong.getId());

        // Then
        assertTrue(foundSong.isPresent());
        assertEquals("Test Song 3", foundSong.get().getTitle());
    }

    @Test
    void save_shouldAssignId_whenIdIsNull() {
        // Given
        Song newSong = new Song(null, "Test Song 4", "Artist 4", "Album 4", 2023, "url4");

        // When
        Song savedSong = repository.save(newSong);

        // Then
        assertNotNull(savedSong.getId());
    }

    @Test
    void deleteById_shouldRemoveSong_whenIdExists() {
        // Given
        Song newSong = new Song(null, "Test Song 5", "Artist 5", "Album 5", 2024, "url5");
        Song savedSong = repository.save(newSong);
        int initialSize = repository.findAll().size();

        // When
        repository.deleteById(savedSong.getId());

        // Then
        assertEquals(initialSize - 1, repository.findAll().size());
        assertTrue(repository.findById(savedSong.getId()).isEmpty());
    }
}