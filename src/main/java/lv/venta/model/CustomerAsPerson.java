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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "CUSTOMER_AS_PERSON_TABLE")
@Entity
public class CustomerAsPerson{
	
	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDCP")
	private int customerId;
	
	//@NotNull
	//@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]+", message = "Only letters allowed")
	@OneToOne
	@JoinColumn(name = "IDA")
	private Address address;
	//todo palabot
    
	@NotNull
	@Pattern(regexp = "[2]{1}[0-9]{7}", message = "Only 8 numbers allowed")
	@Column(name = "PHONE_NO")
    private String phoneNo;
    
	@OneToMany(mappedBy = "customerP")
	@NotNull
    private Collection<Parcel> parcels = new ArrayList<Parcel>();
	//todo samainit linkage
	
    
    @NotNull
    @Column(name = "CUSTOMER_CODE")
    protected String customerCode;
	
	@OneToOne
    @JoinColumn(name = "IDP", referencedColumnName = "IDP")
	protected Person person;
	
	
	public CustomerAsPerson(Person person, Address address, String phoneNo) {
		setAddress(address);
        setPhoneNo(phoneNo);
        setPerson(person);
        setCustomerCode();
	}

	
	
	
	public void setCustomerCode() {
		this.customerCode = this.getCustomerId() + "_person_" + person.getPersonCode();
	}




	public void addNewParcel(Parcel parcel) throws Exception {
    	if (parcel != null && parcel instanceof Parcel) {
    		for (Parcel e : parcels){
    			if (e.equals(parcel)){
    				throw new Exception("Parcel already exists");
    			}
    		}
    		parcels.add(parcel);
    		return;
    	}
    	throw new Exception("Invalid input parameters");
    }

	
	
}
