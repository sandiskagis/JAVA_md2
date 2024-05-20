package lv.venta.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AbstractCustomer {

	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cID;
	
	
    private Address address;
    
    
    private String phoneNo;
    
    
    private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
    
    
    protected String customerCode;
    
    
    private static long counter = 0;
	

    
    
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
    
    
}
