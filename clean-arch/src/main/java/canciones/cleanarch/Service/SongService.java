package canciones.cleanarch.Service;

import canciones.cleanarch.model.Song;
import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> findAll();
    Optional<Song> findById(Long id);
    Song create(Song song);
    Song update(Long id, Song updatedSong);
    void delete(Long id);
}