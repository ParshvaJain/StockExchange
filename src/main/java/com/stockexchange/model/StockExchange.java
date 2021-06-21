package com.stockexchange.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StockExchanges")
public class StockExchange {
	
	@Id
	private String id;
	private String name;
	private String brief;
	private String contactAddress;
	private String remarks;
	private ArrayList<String> companies;
	
	public StockExchange(String id, String name, String brief, String contactAddress, String remarks,ArrayList<String> companies) {
		super();
		this.id = id;
		this.name = name;
		this.brief = brief;
		this.contactAddress = contactAddress;
		this.remarks = remarks;
		this.companies = companies;
	}

	
	public ArrayList<String> getCompanies() {
		return companies;
	}


	public void setCompanies(ArrayList<String> companies) {
		this.companies = companies;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
