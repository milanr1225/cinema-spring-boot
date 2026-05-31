package rs.ac.singidunum.pj.model;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DirectorModel {
  public Integer directorId;
  public String name;
  public LocalDateTime createdAt;
}
