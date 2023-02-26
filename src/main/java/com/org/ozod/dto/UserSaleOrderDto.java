package com.org.ozod.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaleOrderDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private List<SaleOrderDto> saleOrder;


}
