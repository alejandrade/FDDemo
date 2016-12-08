package com.gigamog.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class IceCreams {

	List<IceCream> iceCreams = new ArrayList<IceCream>();

	public void fillIceCreamList(EntityManager em) throws Exception {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<IceCream> cq = cb.createQuery(IceCream.class);
			Root<IceCream> from = cq.from(IceCream.class);
			cq.select(from.alias("p"));
			TypedQuery<IceCream> query = em.createQuery(cq);
			iceCreams = query.getResultList();
		} catch (Exception e) {
			throw new Exception();
		}

	}

	public List<IceCream> getIceCreams() {
		return iceCreams;
	}

	public void setIceCreams(List<IceCream> iceCreams) {
		this.iceCreams = iceCreams;
	}

}
