package lv.venta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "PARCEL_TABLE")
@Entity
public class Parcel {

	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@Column(name = "IDPA")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long pAID;
	
	@NotNull
	@Column(name = "IS_FRAGILE")
	private boolean isFragile;
    
	@NotNull
	@Column(name = "ORDER_CREATED")
    private LocalDateTime orderCreated;
    
	@NotNull
	@Column(name = "PLANNED_DELIVERY")
    private LocalDateTime plannedDelivery;
    
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "PRICE")
    private float price;
    
    @NotNull
	@Column(name = "SIZE")
    private ParcelSize size;
    
    @NotNull
	@Column(name = "IDP")
    private Driver driver;
    
    
    
    public Parcel(boolean isFragile, LocalDateTime orderCreated, LocalDateTime plannedDelivery, float price, ParcelSize size) {
    	setFragile(isFragile);
    	setOrderCreated(orderCreated);
    	setPlannedDelivery(plannedDelivery);
    	setPrice(price);
    	setSize(size);
    }
	
}
