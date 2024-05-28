package lv.venta.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "CUSTOMER_AS_COMPANY")
@Entity
public class CustomerAsCompany extends AbstractCustomer{

	@NotNull
	@Pattern(regexp = "[A-Za-z0-9 ]{2,60}", message = "Invalid title")
	@Column(name = "TITLE")
	private String title;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9 ]{2,60}", message = "Invalid address")
	@Column(name = "COMPANY_REG_NO")
    private String companyRegNo;
	
	
	public CustomerAsCompany(Address address, String phoneNo, String title, String companyRegNo) {
		super(address, phoneNo);
        setTitle(title);
        setCompanyRegNo(companyRegNo);
        setCustomerCode();
	}


	@Override
	public void setCustomerCode() {
		super.customerCode = super.getCustomerId() + "_company_" + this.companyRegNo;
	}
	
}
