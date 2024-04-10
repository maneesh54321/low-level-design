package com.ms.json.model;

public class Address {
	private int houseNo;
	private String zipCode;
	private String city;

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address{" +
				"houseNo=" + houseNo +
				", zipCode='" + zipCode + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}
