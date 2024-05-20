package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "ADDRESS_TABLE")
@Entity
public class Address {

	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@Column(name = "IDa")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long aID;
	
	@Column(name = "CITY")
	private City city;
	
	@NotNull
	@Size(min = 1, max = 1000)
	@Column(name = "HOUSE_NO")
	private int houseNo;
	
	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "STREET_OR_HOUSE_NO")
	private String streetOrHouseTitle;
	
	
	
	public Address(City city, String streetOrHouseTitle, int houseNo) {
		setCity(city);
		setHouseNo(houseNo);
		setStreetOrHouseTitle(streetOrHouseTitle);
	}
	
}
