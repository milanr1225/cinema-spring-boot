package rs.ac.singidunum.pj.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovieGenreModel {
  private Integer movieGenreId;
  private Integer movieId;
  private Integer genreId;
  private GenreModel genre;
}
