package com.org.ozod.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "PRODUCT_MASTER")
public class ProductMaster {

	@Id
	@GeneratedValue
	private Long id;
	
	private String product_type;

	private String unicodeCarector;
	
	@Column(name = "CREATED_TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIMESTAMP")
	@UpdateTimestamp
	private LocalDateTime updatedTime;
	
	@Column(name = "REC_STAT")
	private boolean recStat;

}
