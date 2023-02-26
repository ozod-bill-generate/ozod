package com.org.ozod.service;

import org.springframework.core.io.Resource;

import com.org.ozod.dto.UserSaleOrderDto;

public interface IReportService {

	Resource generateBill(UserSaleOrderDto userProduct);

}
