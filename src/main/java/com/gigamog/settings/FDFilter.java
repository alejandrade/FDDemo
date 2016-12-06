package com.gigamog.settings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gigamog.util.KunderaConnect;
import com.impetus.client.cassandra.common.CassandraConstants;


@WebFilter(filterName = "AResponseFilter", urlPatterns = { "/*" })
public class FDFilter implements javax.servlet.Filter {
	
	
	EntityManagerFactory emf;
	KunderaConnect conn = KunderaConnect.getInstance();

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse  resp = (HttpServletResponse) response;

		try{
	
			//FilterHelper.addCORS(resp);
			if (req.getMethod().equals("OPTIONS")) {
				FilterHelper.changeContent(resp,"thanks for options call");
			}
			

		}catch(Exception e){
			FilterHelper.changeContent(resp,e.getMessage());
		}finally{
			chain.doFilter(request, response);
		}
	}


	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		Map<String, String> props = new HashMap<>();
        props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
        emf = Persistence.createEntityManagerFactory("cassandra_pu", props);
		conn.setup(emf);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		conn.destroy();
	}
	
}