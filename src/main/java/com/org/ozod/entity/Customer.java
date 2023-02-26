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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	private String mobile;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;
	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "purchase_id")
//	private List<PurchaseOrder> purchaseId=new ArrayList<>();
	
	@Column(name = "CREATED_TIMESTAMP")
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIMESTAMP")
	private LocalDateTime updatedTime;
	
	@Column(name = "REC_STAT")
	private boolean recStat;
	
	

}
