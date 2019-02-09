package com.team22.backend.Entity;
import javax.persistence.*;
import lombok.*;
import java.time.LocalDate;
import javax.validation.constraints.*;


@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Review")
public class Review {
    @Id
    @SequenceGenerator(name="review_seq",sequenceName="review_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="review_seq")
    @Column(name="Review_ID",unique = true, nullable = false)

    private Long reviewId;
    @NotNull
    @Size(min=5,max=20)
    @Pattern(regexp = "(ควร).+")
    @Column(unique = true)
    private  String reviewComment;

    @NotNull
    private String ment2;

    @NotNull
    private LocalDate reviewDate;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellingId")
    private Selling selling;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = LevelReview.class)
    @JoinColumn(name = "levelReviewId", insertable = true)
    private LevelReview levelReview;
 }