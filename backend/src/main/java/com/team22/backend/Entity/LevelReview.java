package com.team22.backend.Entity;
import javax.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="LevelReview")
public class LevelReview {
    @Id
    @SequenceGenerator(name="levelReview_seq",sequenceName="levelReview_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="levelReview_seq")
    @Column(name="LevelReview_ID",unique = true, nullable = false)
     
    private Long levelReviewId;
    @NotNull
    private  String levelName;


  
}