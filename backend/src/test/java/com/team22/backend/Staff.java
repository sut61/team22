package com.okta.developer.demo.Entity;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.*;
import java.util.Collection;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Staff")
public class Staff {
    @Id
    @SequenceGenerator(name="staff_seq",sequenceName="staff_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="staff_seq")
    @Column(name="Staff_ID",unique = true, nullable = false)
    private @NonNull Long staffId;
    private @NonNull String staffIDs;
    public   String staffName;
    private  String position;

}
