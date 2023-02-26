package com.org.ozod.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.org.ozod.util.JsonUtil;

@CrossOrigin(origins = {"${ui.url}"},maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private JsonUtil jsonutill;

	@GetMapping("/all")
	public ResponseEntity<JsonNode> getAllproduct() throws StreamReadException, DatabindException, IOException {
		return new ResponseEntity<JsonNode>(jsonutill.getProductMaster(), HttpStatus.OK);
	}

	@GetMapping("/{key}")
	public ResponseEntity<JsonNode> getOne(@PathVariable("key") String key)
			throws StreamReadException, DatabindException, IOException {
		return new ResponseEntity<JsonNode>(jsonutill.getProductMaster().findValue(key), HttpStatus.OK);
	}
	
	@GetMapping("/producttype/{producttype}/productnameid/{productnameid}")
	public ResponseEntity<JsonNode> getOneProduct(@PathVariable("producttype") String producttype,@PathVariable("productnameid") String productnameid)
			throws StreamReadException, DatabindException, IOException {
		return new ResponseEntity<JsonNode>(jsonutill.getOneProduct(producttype,productnameid), HttpStatus.OK);
	}

}
