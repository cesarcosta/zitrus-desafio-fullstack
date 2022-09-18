package br.com.zitrus.api.entities;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.zitrus.api.enums.MovementType;
import br.com.zitrus.api.util.DateUtil;

@Entity
@Table(name = "stock_movement")
public class StockMovement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "movement_type")
	private MovementType movementType;
	
	@Column(name = "date_sale")
	@Temporal(TemporalType.DATE)
	private Date dateSale;
	
	@Column(name = "total_sale")
	private Double total;
	
	@Column
	private Double quantity;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public Date getDateSale() {
		return dateSale;
	}
	
	public void setDateSale(Date dateSale) {
		this.dateSale = dateSale;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public String getDate() {
		return DateUtil.getDateString(dateSale, DateUtil.DD_MM_YYYY);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockMovement other = (StockMovement) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "StockMovement [id=" + id + ", product=" + product + ", movementType=" + movementType + ", dateSale=" + dateSale + ", total=" + total + ", quantity=" + quantity + "]";
	}
}