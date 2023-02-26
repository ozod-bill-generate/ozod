package com.org.ozod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ozod.dto.UserSaleOrderDto;
import com.org.ozod.service.IReportService;

@CrossOrigin(origins = {"${ui.url}"},maxAge = 3600)
@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private IReportService service;
	
	@PostMapping(value="/pdf/generate" ,consumes = {"application/x-www-form-urlencoded","application/json"})
	public ResponseEntity<Resource> getBill(@RequestBody UserSaleOrderDto userProduct) {
		return ResponseEntity.ok()
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(service.generateBill(userProduct));
	}
	

}
