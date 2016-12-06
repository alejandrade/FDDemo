package com.gigamog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "fddemo@cassandra_pu")
public class User {
	
    @Id
    @Column(name="email")
    private String email;

    @Column(name="access_token")
    private String accessToken;
    

    @Column(name="times_viewd")
    private int timesChecked;
    
    
    public User(){

    }
    
    public User(String email, String accessToken){
    	this.email = email;
    	this.accessToken = accessToken;
    	this.timesChecked = 1;
    }
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getTimesChecked() {
		return timesChecked;
	}

	public void setTimesChecked(int timesChecked) {
		this.timesChecked = timesChecked;
	}


    
    
}
