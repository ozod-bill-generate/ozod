package com.org.ozod.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALE_ORDER")
public class SaleOrder {
	
	@Id
	@GeneratedValue
	private Long id;

	private String product_name;
	
	private String  productType;
	
	private String  productNameId;
	
	private String quantity;
	
	private Long price;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;
	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "customer_id")
//	private List<Customer> customer=new ArrayList<>();
	
	@Column(name = "CREATED_TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIMESTAMP")
	@UpdateTimestamp
	private LocalDateTime updatedTime;
	
	@Column(name = "REC_STAT")
	private boolean recStat;
	
	

}
