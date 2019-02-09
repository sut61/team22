package com.team22.backend.Entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity  
@Data 
@Getter @Setter
@ToString
@EqualsAndHashCode
@Table(name=" Detail ") //ชื่อตาราง
public class Detail {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="detail_seq",sequenceName="detail_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="detail_seq")
    @Column(name="Details_ID",unique = true, nullable = false)
    
    private  @NonNull Long detailIds;

    @NotNull
    private String detailName;

    public Detail (String det){
        this.detailName = det;
    }

	public Detail() {
	}
}