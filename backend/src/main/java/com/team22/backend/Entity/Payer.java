package com.team22.backend.Entity;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Payer")
public class Payer {
    @Id
    @SequenceGenerator(name="payer_seq",sequenceName="payer_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="payer_seq")
    @Column(name="Payer_ID",unique = true, nullable = false)
    private @NonNull Long   payerId;
    private @NonNull String payerIds;
    public           String payerName;
    
}