package com.team22.backend.Entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Payer")
public class Payer {
    @Id
    @SequenceGenerator(name = "payer_seq", sequenceName = "payer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payer_seq")
    @Column(name = "Payer_ID", unique = true, nullable = false)
    @NotNull
    private Long payerId;

    @NotNull(message = "payerIds not be null")
    private String payerIds;

    @NotNull(message = "payerName not be null")
    @Pattern(regexp = "[A-Z,a-z]\\w{2,}")
    @Column(unique = true)
    @Size(min = 2, max = 20)
    private String payerName;

}