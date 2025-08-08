package canciones.cleanarch.controller;

import canciones.cleanarch.Controller.SongController;
import canciones.cleanarch.model.Song;
import canciones.cleanarch.Service.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SongController.class)
class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @Autowired
    private ObjectMapper objectMapper;

    private Song song1;
    private Song song2;

    @BeforeEach
    void setUp() {
        song1 = new Song(1L, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 1975, "url1");
        song2 = new Song(2L, "Stairway to Heaven", "Led Zeppelin", "Led Zeppelin IV", 1971, "url2");
    }

    @Test
    void getAllSongs_shouldReturnSongsList() throws Exception {
        when(songService.findAll()).thenReturn(List.of(song1, song2));

        mockMvc.perform(get("/api/v1/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Bohemian Rhapsody")));
    }

    @Test
    void getSongById_shouldReturnSong_whenExists() throws Exception {
        when(songService.findById(1L)).thenReturn(Optional.of(song1));

        mockMvc.perform(get("/api/v1/songs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Bohemian Rhapsody")));
    }

    @Test
    void getSongById_shouldReturnNotFound_whenNotExists() throws Exception {
        when(songService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/songs/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createSong_shouldReturnCreatedSong() throws Exception {
        Song newSong = new Song(null, "New Song", "New Artist", "New Album", 2023, "new-url");
        Song savedSong = new Song(3L, "New Song", "New Artist", "New Album", 2023, "new-url");

        when(songService.create(any(Song.class))).thenReturn(savedSong);

        mockMvc.perform(post("/api/v1/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSong)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1/songs/3"))
                .andExpect(jsonPath("$.id", is(3)));
    }

    @Test
    void updateSong_shouldReturnUpdatedSong() throws Exception {
        Song updatedSong = new Song(1L, "Updated Title", "Queen", "A Night at the Opera", 1975, "url1");

        when(songService.update(eq(1L), any(Song.class))).thenReturn(updatedSong);

        mockMvc.perform(put("/api/v1/songs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSong)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Title")));
    }

    @Test
    void deleteSong_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/songs/1"))
                .andExpect(status().isNoContent());
    }
}