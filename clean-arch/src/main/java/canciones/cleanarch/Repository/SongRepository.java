package canciones.cleanarch.Repository;

import canciones.cleanarch.model.Song;
import java.util.List;
import java.util.Optional;

public interface SongRepository {
    List<Song> findAll();
    Optional<Song> findById(Long id);
    Song save(Song song);
    void deleteById(Long id);
}