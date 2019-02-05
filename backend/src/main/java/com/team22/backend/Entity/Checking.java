package com.team22.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Getter @Setter
@ToString
@EqualsAndHashCode
@Table(name="Checking")
public class Checking {
    @Id
    @SequenceGenerator(name="Checking_seq",sequenceName="Checking_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Checking_seq")
    @Column(name="Checking_ID",unique = true, nullable = false)
     
    private @NonNull Long checkingId;
    private @NonNull String checking;

    public Checking (String ck){
        this. checking = ck;
    }
}