package lv.venta.model;


import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "CUSTOMER_AS_COMPANY_TABLE")
@Entity
public class CustomerAsCompany{

	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDCC")
	private int customerId;
	
	@OneToOne
    @JoinColumn(name = "IDA")
	private Address address;
	//todo palabot
    
	@NotNull
	@Pattern(regexp = "[2]{1}[0-9]{7}", message = "Only 8 numbers allowed")
	@Column(name = "PHONE_NO")
    private String phoneNo;
    
	@OneToMany(mappedBy = "customerC")
	@NotNull
    private Collection<Parcel> parcels = new ArrayList<Parcel>();
	//todo samainit linkage
    
    @NotNull
    @Column(name = "CUSTOMER_CODE")
    protected String customerCode;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9 ]{2,60}", message = "Invalid title")
	@Column(name = "TITLE")
	private String title;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9 ]{2,60}", message = "Invalid address")
	@Column(name = "COMPANY_REG_NO")
    private String companyRegNo;
	
	
	public CustomerAsCompany(Address address, String phoneNo, String title, String companyRegNo) {
		setAddress(address);
        setPhoneNo(phoneNo);
        setTitle(title);
        setCompanyRegNo(companyRegNo);
        setCustomerCode();
	}


	public void setCustomerCode() {
		this.customerCode = this.getCustomerId() + "_company_" + this.companyRegNo;
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
