package com.gigamog.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.impetus.client.cassandra.common.CassandraConstants;

public class KunderaConnect {

	private EntityManager em;
	private EntityManagerFactory emf;
	private static KunderaConnect conn = null;

	private KunderaConnect() {

	}

	public synchronized static KunderaConnect getInstance() {

		if (conn == null)
			conn = new KunderaConnect();

		return conn;

	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void close() {
		em.close();
		em = null;
	}

	public void destroy() {
		if (em.isOpen())
			em.close();

		if (emf.isOpen())
			emf.close();

	}

	public EntityManager getEm() {
		KunderaConnect conn = KunderaConnect.getInstance();
		Map<String, String> props = new HashMap<>();
		props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("cassandra_pu", props);
		}else if(!emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("cassandra_pu", props);
		}
		
		if(em == null)
			em = emf.createEntityManager();
		else if(!em.isOpen())
			em = emf.createEntityManager();
		
		return em;
	}

}
