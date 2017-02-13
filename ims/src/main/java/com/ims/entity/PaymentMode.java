package com.ims.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class PaymentMode {
	
	@Id
	private long id;
	
	@Column(unique=true)
	private String paymentId;
	private String paymentdesc;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentdesc() {
		return paymentdesc;
	}
	public void setPaymentdesc(String paymentdesc) {
		this.paymentdesc = paymentdesc;
	}

}
