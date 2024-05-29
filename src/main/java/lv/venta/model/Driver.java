package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Driver extends Person{

    @NotNull
	@Pattern(regexp = "\\d{8}", message = "Only numbers allowed")
	@Column(name = "LICENCE_NO")
    private String licenseNo;

    @Min(0)
    @Max(50)
    @Column(name = "EXPERIENCE_IN_YEARS")
    private float experienceInYears;

    @OneToMany(mappedBy = "driver")
	@NotNull
    private Collection<Parcel> parcels = new ArrayList<Parcel>();
    
    public Driver(String name, String surname, String personCode, String licenseNo, float experienceInYears) {
        super(name, surname, personCode);
        setLicenseNo(licenseNo);
        setExperienceInYears(experienceInYears);
    }
}
