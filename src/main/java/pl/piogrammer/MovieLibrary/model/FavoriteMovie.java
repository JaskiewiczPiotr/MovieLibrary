package pl.piogrammer.MovieLibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="favorite_movie")
public class FavoriteMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id_favorite_movie;

    private String movie_name;

    private int rating;
    @Lob
    private Blob image;

    @Transient
    private String imageBase64;

    public void setImageBase64() {
        if (this.image != null) {
            try {
                byte[] imageBytes = this.image.getBytes(1, (int) this.image.length());
                this.imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
            } catch (Exception e) {
                e.printStackTrace();
                this.imageBase64 = ""; // Handle the case where conversion fails
            }
        } else {
            this.imageBase64 = ""; // No image available
        }
    }

}
