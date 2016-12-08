package com.gigamog.settings;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.gigamog.pojo.ErrorMessage;
import com.gigamog.util.KunderaConnect;

@WebFilter(filterName = "JwtFilter", urlPatterns = { "/api/*" })
public class JwtFilter implements javax.servlet.Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (!req.getMethod().equals("OPTIONS")) {
			try {

				FilterHelper.openJwt(req);

			} catch (Exception e) {
				FilterHelper.addCORS(resp);
				FilterHelper.changeContent(resp, new ErrorMessage(401,e.getMessage()).toJson());
			}
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		KunderaConnect conn = KunderaConnect.getInstance();
		conn.destroy();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}