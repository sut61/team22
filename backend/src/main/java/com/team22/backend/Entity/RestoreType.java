package com.team22.backend.Entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Getter @Setter
@ToString
@EqualsAndHashCode
@Table(name="RestoreType") //ชื่อตาราง
public class RestoreType {
    @Id
    @SequenceGenerator(name = "restoreType_seq", sequenceName = "restoreType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restoreType_seq")
    @Column(name = "RestoreType_ID", unique = true, nullable = false)

    private @NonNull Long restoreTypeId;
    private @NonNull String typeRestoreName;

    public RestoreType(String rtype){
        this.typeRestoreName = rtype;
    }
}
