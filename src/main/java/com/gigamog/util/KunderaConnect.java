package com.gigamog.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.eclipse.persistence.jpa.jpql.parser.EmptyCollectionComparisonExpressionBNF;

import com.impetus.client.cassandra.common.CassandraConstants;

public class KunderaConnect {
	

	
	EntityManagerFactory emf;
	EntityManager em = null;
	private static KunderaConnect conn = null;

	public static final String CASSANDRA_FDDEMO = "cassandra_pu";

	private KunderaConnect() {
		//createFactory(CASSANDRA_FDDEMO);
	}
	
	public void setup(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	public synchronized static KunderaConnect getInstance(){
		
		if(conn == null)
			conn = new KunderaConnect();
		
		return conn;
			
	}



	public void createFactory(String connection) {
		Map<String, String> props = new HashMap<>();
		props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
		emf = Persistence.createEntityManagerFactory(connection, props);
	}
	
	public void getConnection(){
		em = emf.createEntityManager();
	}

	public void save(Object obj) {
		em.persist(obj);
	}
	
	
	public <T> T selectById(Class<T> cls, String email){
		return em.find(cls, email);
	}

	public void close() {
		em.close();
	}
	
	public void destroy(){
		if(em.isOpen()){
			em.close();
			em = null;
		}
		
		if(emf.isOpen()){
			emf.close();
			emf = null;
		}
	}

}
