package canciones.cleanarch.Repository;

import canciones.cleanarch.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemorySongRepository implements SongRepository {

    private final List<Song> songs = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public InMemorySongRepository() {
        // Datos de ejemplo para empezar
        save(new Song(null, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 1975, "https://www.youtube.com/watch?v=fJ9rUzIMcZQ"));
        save(new Song(null, "Stairway to Heaven", "Led Zeppelin", "Led Zeppelin IV", 1971, "https://www.youtube.com/watch?v=xbhC1y-j814"));
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songs);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songs.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst();
    }

    @Override
    public Song save(Song song) {
        if (song.getId() == null) {
            song.setId(counter.incrementAndGet());
            songs.add(song);
        } else {
            findById(song.getId()).ifPresent(existingSong -> {
                songs.remove(existingSong);
                songs.add(song);
            });
        }
        return song;
    }

    @Override
    public void deleteById(Long id) {
        songs.removeIf(song -> song.getId().equals(id));
    }
}