package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "DRIVER_TABLE")
@Entity
public class Driver {

	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@Column(name = "IDP")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long dID;

    @ManyToOne
    @JoinColumn(name = "IDP")
    private Person person;

    @NotNull
	@Pattern(regexp = "\\d{8}", message = "Only numbers allowed")
	@Column(name = "LICENCE_NO")
    private String licenseNo;

    @Size(min = 0, max = 50)
    @Column(name = "EXPERIENCE_IN_YEARS")
    private float experienceInYears;

    public Driver(Person person, String licenseNo, float experienceInYears) {
        this.person = person;
        this.licenseNo = licenseNo;
        this.experienceInYears = experienceInYears;
    }
}