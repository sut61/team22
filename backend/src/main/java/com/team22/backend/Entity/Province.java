package com.team22.backend.Entity;

import lombok.Data;
import lombok.NonNull;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "province")
public class Province {
    @Id
    @SequenceGenerator(name="province_seq",sequenceName="province_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="province_seq")
    @Column(name="PROVINCE_ID",unique = true, nullable = false)
    private Long provinceId;
    private String provinceName;

}
