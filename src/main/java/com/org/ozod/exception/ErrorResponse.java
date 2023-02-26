package com.org.ozod.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

	private String msg;

	private LocalDateTime dateTime;

	public ErrorResponse(String msg, LocalDateTime dateTime) {
		this.msg=msg;
		this.dateTime=dateTime;
	}

}
