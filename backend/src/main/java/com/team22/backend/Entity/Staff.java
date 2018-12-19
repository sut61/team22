package com.team22.backend.Entity;
        import javax.persistence.*;

        import lombok.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Staff")

public class Staff {


    @Id
    //@JsonFormat(pattern="yyyy-mm-dd")
    @SequenceGenerator(name="staff_seq",sequenceName="staff_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="staff_seq")
    @Column(name="staffId",unique = true, nullable = false)

    private @NonNull Long staffId;
    private @NonNull String staffIds;
    private          String staffName;
    private          String staffGender;
    private          String staffJobtype;
    private          String staffPhone;
    private          int staffSalary;
    private          String staffStatus;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "positionId", insertable = true)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Experience.class)
    @JoinColumn(name = "experienceId", insertable = true)
    private Experience experience;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Education.class)
    @JoinColumn(name = "educationId", insertable = true)
    private Education education;


}