package com.ims.entity;



import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vms_role database table.
 * 
 */
@Entity(name="vms_role")
@AttributeOverride(name="id",column=@Column(name = "role_id"))
public class Role extends EntityAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private boolean isMenu1;

	private boolean isMenu2;

	private boolean isMenu3;

	private String role_Name;

	public Role() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public String getRole_Name() {
		return this.role_Name;
	}

	public void setRole_Name(String role_Name) {
		this.role_Name = role_Name;
	}

	public boolean isMenu1() {
		return isMenu1;
	}

	public void setMenu1(boolean isMenu1) {
		this.isMenu1 = isMenu1;
	}

	public boolean isMenu2() {
		return isMenu2;
	}

	public void setMenu2(boolean isMenu2) {
		this.isMenu2 = isMenu2;
	}

	public boolean isMenu3() {
		return isMenu3;
	}

	public void setMenu3(boolean isMenu3) {
		this.isMenu3 = isMenu3;
	}

}