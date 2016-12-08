package com.gigamog.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gigamog.exceptions.ServerErrorException;

@Entity
@Table(name = "icecream", schema = "fddemo@cassandra_pu")
public class IceCream {

	@Column(name = "intoken")
	@Id
	protected String inToken = "";

	@Column(name = "flavor")
	protected String flavor;

	@Column(name = "name")
	protected String name;

	public IceCream(String token) {
		inToken = token;
	}

	public IceCream() {
	}

	public void populateByToken(EntityManager em) {
		if (inToken == null)
			throw new ServerErrorException("bad code getting in the way");

		try {
			IceCream cream = em.find(this.getClass(), inToken);
			this.flavor = cream.flavor;
			this.name = cream.name;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("token must not exist");
		}

	}

	public String getInToken() {
		return inToken;
	}

	public void setInToken(String inToken) {
		this.inToken = inToken;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
