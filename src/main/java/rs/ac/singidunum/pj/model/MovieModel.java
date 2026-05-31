package rs.ac.singidunum.pj.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovieModel {
  private Integer movieId;
  private String internalId;
  private String corporateId;
  private Integer directorId;
  private String title;
  private String originalTitle;
  private String description;
  private String shortDescription;
  private String poster;
  private LocalDate startDate;
  private String shortUrl;
  private Integer runTime;
  private Boolean active;
  private LocalDateTime createdAt;
  private LocalDateTime editedAt;
  private DirectorModel director;
  private List<MovieActorModel> movieActors;
  private List<MovieGenreModel> movieGenres;
}
