package com.gigamog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gigamog.exceptions.ServerErrorException;
import com.gigamog.util.JwtHelper;
import com.gigamog.util.KunderaConnect;

@Entity
@Table(name = "jwtkey", schema = "fddemo@cassandra_pu")
public class Jwt {
	
	
	@Id
    @Column(name="jwt")
	String jwt;
	
    @Column(name="intoken")
	String inToken;
	
    @Column(name="banned")
    int banned = 0;
    
    public Jwt(){
    	
    }
    
    public Jwt(String jwt){
    	this.jwt = jwt;	
    }
    public Jwt(String jwt, String inToken){
    	this.jwt = jwt;	
    	this.inToken = inToken;
    }
    
    
    public boolean validateJWT(EntityManager em){
    	JwtHelper helper = new JwtHelper();
    	Boolean result = false;
    	if(helper.validateJwt(jwt)){
    		Jwt jwt = getJwtFromServer(em, this.jwt);
    		if(jwt.banned==0)
    			result = true;
    	}
    	return result;
    	
    }
    
    public Jwt getJwtFromServer(EntityManager em, String jwt2){
    	return em.find(Jwt.class, jwt2);
    }
    
    public void logAccess(EntityManager em){
    	if(jwt==null)
    		throw new ServerErrorException("bad code is blocking progress, sorry");
    	em.persist(this);
    }

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public int getLastAccess() {
		return banned;
	}

	public void setLastAccess(int lastAccess) {
		this.banned = lastAccess;
	}
	public String getInToken() {
		return inToken;
	}
	public void setInToken(String inToken) {
		this.inToken = inToken;
	}
	public int getBanned() {
		return banned;
	}
	public void setBanned(int banned) {
		this.banned = banned;
	}

}
