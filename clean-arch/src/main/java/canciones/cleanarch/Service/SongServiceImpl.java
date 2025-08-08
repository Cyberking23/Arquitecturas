package canciones.cleanarch.Service;

import canciones.cleanarch.model.Song;
import canciones.cleanarch.Repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Song create(Song song) {
        // En un caso real, aquí irían validaciones de negocio antes de guardar
        return songRepository.save(song);
    }

    @Override
    public Song update(Long id, Song updatedSong) {
        return songRepository.findById(id)
                .map(song -> {
                    song.setTitle(updatedSong.getTitle());
                    song.setArtist(updatedSong.getArtist());
                    song.setAlbum(updatedSong.getAlbum());
                    song.setReleaseYear(updatedSong.getReleaseYear());
                    song.setUrl(updatedSong.getUrl());
                    return songRepository.save(song);
                })
                .orElseThrow(() -> new RuntimeException("Song not found with id " + id)); // Se podría crear una excepción personalizada
    }

    @Override
    public void delete(Long id) {
        songRepository.deleteById(id);
    }
}