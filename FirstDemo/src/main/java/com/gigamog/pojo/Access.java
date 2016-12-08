package com.gigamog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gigamog.exceptions.UnauthorizedException;
import com.gigamog.util.KunderaConnect;

@Entity
@Table(name = "access", schema = "fddemo@cassandra_pu")
public class Access {
	

	@Column(name="intoken")
	@Id
    private String inToken;

    @Column(name="passcode")
    private String passCode;
   
    public Access(){

    }
    public Access(String token, String passCode){
    	this.inToken = token;
    	this.passCode = passCode;
    }
    
    public void validate(EntityManager em , String token, String passCode){
    	Access acc = em.find(Access.class, token);
    	if(!(acc.getInToken().equals(this.getInToken()) && acc.passCode.equals(this.getPassCode()))){
    		throw new UnauthorizedException("credentials are incorrect");
    	}
    	
    }


	public String getInToken() {
		return inToken;
	}


	public void setInToken(String inToken) {
		this.inToken = inToken;
	}


	public String getPassCode() {
		return passCode;
	}


	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}
    

    
    


    
    
}
