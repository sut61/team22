package com.team22.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Style")
public class Style {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="style_seq",sequenceName="style_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="style_seq")
    @Column(name="StyleId",unique = true, nullable = false)
    @NotNull
    private  Long styleID;
    @NotNull
    private  String styleIDs;
    @NotNull
    private  String styleName;
    @NotNull
    private  Integer stylePrice;

}
