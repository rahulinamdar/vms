package com.ims.entity;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_ database table.
 * 
 */
@Entity
@Table(name="user_")
@NamedQuery(name="User.findUser", query="SELECT u FROM User u WHERE u.userName =:userName")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name="pass_word")
	private String password;

	@Column(name="user_name")
	private String userName;
	
	@OneToOne
	@JoinColumn(name="role_id",insertable=true,nullable=false)
	private Role role;

	public User() {
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}