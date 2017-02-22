package com.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="currency")
public class Currency extends EntityAudit{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(unique=true)
	private String currencyid;
	private String currencydesc;
	
	
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
