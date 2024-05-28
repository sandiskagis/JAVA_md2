package lv.venta.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity
public abstract class AbstractCustomer {

	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]+", message = "Only letters allowed")
    private Address address;
    
	@NotNull
	@Pattern(regexp = "[2]{1}[0-9]{7}", message = "Only 8 numbers allowed")
    private String phoneNo;
    
	@OneToMany
	@NotNull
    private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
    
    @NotNull
    protected String customerCode;
	
    
    public AbstractCustomer(Address address, String phoneNo){
        setAddress(address);
        setPhoneNo(phoneNo);
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
    
    
    public abstract void setCustomerCode();
    
}
