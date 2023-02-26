package com.org.ozod;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itextpdf.text.DocumentException;

@SpringBootApplication
public class OzodApplication {

	public static void main(String[] args) throws IOException, DocumentException {

		SpringApplication.run(OzodApplication.class, args);

	}

}
