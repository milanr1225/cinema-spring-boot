package rs.ac.singidunum.pj.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import rs.ac.singidunum.pj.model.MovieModel;

@Service
public class MovieService {

  private final RestClient client = RestClient.builder()
      .baseUrl("https://movie.pequla.com/api")
      .defaultHeader("Accept", "application/json")
      .defaultHeader("X-name", "PJ_2026")
      .build();

  public List<MovieModel> getAll() {
    List<MovieModel> unsorted = client.get()
        .uri("/movie")
        .retrieve()
        .body(new ParameterizedTypeReference<>() {
        });

        unsorted.sort(Comparator.comparingInt(MovieModel::getMovieId).reversed());
        return unsorted;
  }

  public Optional<MovieModel> getById(Integer id) {
    try {
    return Optional.ofNullable(
                              client.get()
                              .uri("/movie/"+id)
                              .retrieve()
                              .body(MovieModel.class));
    } catch (HttpClientErrorException.NotFound ex) {
      return Optional.empty();
    }
  }

  public List<MovieModel> getByIds(List<Integer> ids) {
    return client.post()
        .uri("/movie/list")
        .contentType(MediaType.APPLICATION_JSON)
        .body(ids)
        .retrieve()
        .body(new ParameterizedTypeReference<>() {
        });
  }
}
