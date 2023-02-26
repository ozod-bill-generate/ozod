package com.org.ozod.util;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {
	@Autowired
	ObjectMapper mapper;

	public JsonNode getProductMaster() throws StreamReadException, DatabindException, IOException {

		JsonNode node = mapper.readValue(
				new File(new File("").getAbsolutePath() + Constant.PRODUCT_MASTER_PATH_UNICODE), JsonNode.class);
		return node;
	}

	public JsonNode getOneProduct(String producttype, String productnameid) throws StreamReadException, DatabindException, IOException {
		JsonNode node = getProductMaster().findValue(producttype)
				.get(productnameid);
		return node;
	}

}
