package canciones.cleanarch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {

    private Long id;
    private String title;
    private String artist;
    private String album;
    private Integer releaseYear;
    private String url;
}