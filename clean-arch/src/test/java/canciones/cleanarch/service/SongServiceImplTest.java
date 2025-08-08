package canciones.cleanarch.service;

import canciones.cleanarch.Service.SongServiceImpl;
import canciones.cleanarch.model.Song;
import canciones.cleanarch.Repository.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceImplTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImpl songService;

    private Song song;

    @BeforeEach
    void setUp() {
        song = new Song(1L, "Test Title", "Test Artist", "Test Album", 2000, "test-url");
    }

    @Test
    void findById_shouldReturnSong_whenExists() {
        // Given
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        // When
        Optional<Song> found = songService.findById(1L);

        // Then
        assertTrue(found.isPresent());
        assertEquals(song.getTitle(), found.get().getTitle());
        verify(songRepository, times(1)).findById(1L);
    }

    @Test
    void findAll_shouldReturnAllSongs() {
        // Given
        List<Song> songs = List.of(song, new Song(2L, "Song 2", "Artist 2", "Album 2", 2001, "url2"));
        when(songRepository.findAll()).thenReturn(songs);

        // When
        List<Song> allSongs = songService.findAll();

        // Then
        assertFalse(allSongs.isEmpty());
        assertEquals(2, allSongs.size());
        verify(songRepository, times(1)).findAll();
    }

    @Test
    void create_shouldReturnCreatedSong() {
        // Given
        Song newSong = new Song(null, "New Song", "New Artist", "New Album", 2023, "new-url");
        when(songRepository.save(any(Song.class))).thenReturn(new Song(3L, "New Song", "New Artist", "New Album", 2023, "new-url"));

        // When
        Song created = songService.create(newSong);

        // Then
        assertNotNull(created.getId());
        assertEquals("New Song", created.getTitle());
        verify(songRepository, times(1)).save(newSong);
    }

    @Test
    void delete_shouldCallRepositoryDelete() {
        // When
        songService.delete(1L);

        // Then
        verify(songRepository, times(1)).deleteById(1L);
    }
}