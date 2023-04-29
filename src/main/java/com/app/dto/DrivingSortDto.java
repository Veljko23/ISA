package com.app.dto;

public class DrivingSortDto {
	private String field;
	private String order;

	public DrivingSortDto() {
		super();
	}

	public DrivingSortDto(String field, String order) {
		super();
		this.field = field;
		this.order = order;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
