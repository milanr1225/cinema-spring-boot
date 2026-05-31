package rs.ac.singidunum.pj.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovieActorModel {
  private Integer movieActorId;
  private Integer movieId;
  private Integer actorId;
  private ActorModel actor;
}
