package com.ims.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PaymentMethod
 *
 */
@Entity

public class PaymentMethod extends EntityAudit implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public PaymentMethod() {
		super();
	}
   
}
