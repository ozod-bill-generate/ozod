package com.org.ozod.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String email;

	private String mobile;

	private String password;
	
	private String storeName;
	
	@OneToMany(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	private List<Customer> customers;
	
	@OneToMany(targetEntity = SaleOrder.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	private List<SaleOrder> saleOrder;

	@OneToMany(targetEntity = PurchaseOrder.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	private List<PurchaseOrder> purchaseId;

	@OneToMany(targetEntity = Subscription.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	private List<Subscription> subscriptionId;
	
	@Column(name = "CREATED_TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIMESTAMP")
	@UpdateTimestamp
	private LocalDateTime updatedTime;
	
	@Column(name = "REC_STAT")
	private boolean recStat;

}
