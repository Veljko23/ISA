package com.app.dto;

import java.time.LocalDate;

public class RequestStatisticsDto {
	private LocalDate start;
	private LocalDate end;

	public RequestStatisticsDto() {
		super();
	}

	public RequestStatisticsDto(LocalDate start, LocalDate end) {
		super();
		this.start = start;
		this.end = end;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}


	

}
