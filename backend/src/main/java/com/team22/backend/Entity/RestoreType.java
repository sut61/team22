package com.team22.backend.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="RestoreType") //ชื่อตาราง
public class RestoreType {
    @Id
    @SequenceGenerator(name = "restoreType_seq", sequenceName = "restoreType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restoreType_seq")
    @Column(name = "RestoreType_ID", unique = true, nullable = false)
    private Long restoreTypeId;

    @NotNull
    private  String typeRestoreName;

    
}
