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
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder {

	@Id
	@GeneratedValue
	private Long id;

	private String product_name;

	private Long quantity;

	private Long price;
	
	private String distributer_name;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;

	@Column(name = "CREATED_TIMESTAMP")
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIMESTAMP")
	private LocalDateTime updatedTime;

	@Column(name = "REC_STAT")
	private boolean recStat;

}
