package com.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="currency")
public class Currency {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true)
	private String currencyid;
	private String currencydesc;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCurrencyid() {
		return currencyid;
	}
	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}
	public String getCurrencydesc() {
		return currencydesc;
	}
	public void setCurrencydesc(String currencydesc) {
		this.currencydesc = currencydesc;
	}
}
