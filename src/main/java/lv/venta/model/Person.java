package lv.venta.model;

import java.util.Collection;

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
@Table(name = "PERSON_TABLE")
@Entity
public class Person {

	
	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@Column(name = "IDP")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int personId;
	
	@NotNull
	@Size(min = 2, max = 20)
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]+", message = "Only letters allowed")
	@Column(name = "NAME")
	private String name;
	
	@NotNull
    @Size(min = 2, max = 20)
    @Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]+", message = "Only letters allowed")
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Pattern(regexp = "^(\\d{6})-[012]\\d{4}$", message = "Person code must be in the format 101099-32437")
    @Column(name = "PERSON_CODE", unique = true)
    private String personCode;
	
    public Person(String name, String surname, String personCode) {
		setName(name);;
		setSurname(surname);
		setPersonCode(personCode);
	}
	
	
	
}
