package lv.venta.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
    private int parcelId;
	
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
    
    
    
    public void setPlannedDelivery(LocalDateTime plannedDelivery){
        if (plannedDelivery.isAfter(orderCreated))
            this.plannedDelivery = plannedDelivery;
        else
            this.plannedDelivery = orderCreated.plus(1, ChronoUnit.WEEKS);
    }
    
    public void setPrice(ParcelSize size, boolean isFragile)
    {
        if(isFragile == true)
        {
            this.price = 2.99F;
        } else
        {
            this.price = 0; // Set price to 0 if not fragile
        }

        switch (size)
        {
            case X:
                this.price += 1 * 1.99F;
                break;
            case S:
                this.price += 2 * 1.99F;
                break;
            case M:
                this.price += 3 * 1.99F;
                break;
            case L:
                this.price += 4 * 1.99F;
                break;
            case XL:
                this.price += 5 * 1.99F;
                break;
            default:
                this.price += 5 * 1.99F;
                break;
        }
    }
    
    public void setDriver(Driver driver)
    {
        if(driver != null)
            this.driver = driver;
        else
            this.driver = new Driver();
    }
    
    
    public Parcel(boolean isFragile, ParcelSize size, Driver driver, LocalDateTime plannedDelivery) {
    	this.orderCreated = LocalDateTime.now();
    	setFragile(isFragile);
    	setSize(size);
    	setPrice(price);
    	setDriver(driver);
    	setPlannedDelivery(plannedDelivery);
    }
	
}
