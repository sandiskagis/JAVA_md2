package lv.venta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class CustomerAsPerson extends AbstractCustomer{
	
	protected Person person;
	
	
	public CustomerAsPerson(String name, String surname, String personCode, Address address, String phone) {
		super(address, phone);
        this.person = new Person(name, surname, personCode);
        setCustomerCode();
	}

	
	
	@Override
	public void setCustomerCode() {
		super.customerCode = super.getCustomerId() + "_person_" + person.getPersonCode();
	}

	
	
}
