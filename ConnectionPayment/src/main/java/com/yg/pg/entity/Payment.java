package com.yg.pg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment {

	@Id
	@Column(name  = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(name = "order_no")
	private String orderNo; 
	
	@Column(name = "product_name")
	private String productName; 
	
	@Column(name = "member_no")
	private String memberNo; 
	
	@Column(name = "amount")
	private int amount; 
	
	@Column(name = "tax_supply_amount")
	private int taxSupplyAmount; 
	
	@Column(name = "tax_free_supply_amount")
	private int taxFreeSupplyAmount; 
	
	@Column(name = "tax_amount")
	private int taxAmount; 
	
	@Column(name = "vat_amount")
	private int vatAmount; 
	
	@Column(name = "product_count")
	private int productCount; 
	
	@Column(name = "approve_yn")
	private String approveYn;
	
	@Column(name = "cancel_yn")
	private String cancelYn; 
	
	@Column(name = "pg_transaction_id")
	private String pgTransactionId; 
	
	
	
    @PrePersist
    public void preApproveYn() {
        this.approveYn = this.approveYn == null ? "N" : this.approveYn;
        this.cancelYn = this.cancelYn == null ? "N" : this.cancelYn;
    }
}
