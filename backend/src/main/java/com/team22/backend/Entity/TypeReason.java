package com.team22.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity  
@Data 
@Getter @Setter
@ToString
@EqualsAndHashCode
@Table(name="TypeReason") 
public class TypeReason {
    @Id  
    @SequenceGenerator(name="typereason_seq",sequenceName="typereason_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="typereason_seq")
    @Column(name="TypeReasonID",unique = true, nullable = false)
    @NotNull
    private Long typeReasonID;
    @NotNull
    private String typeReasonName;
    
    
}