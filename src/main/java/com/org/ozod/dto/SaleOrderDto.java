package com.org.ozod.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private String product_name;
	
	private String  productType;
	
	private String  productNameId;
	
	private String quantity;
	
	private String price;
}
