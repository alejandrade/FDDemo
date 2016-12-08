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

@WebFilter(filterName = "BaseFilter", urlPatterns = { "/*" })
public class BaseFilter implements javax.servlet.Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		try {

			FilterHelper.addCORS(resp);
			if (req.getMethod().equals("OPTIONS")) {
				FilterHelper.changeContent(resp, "thanks for options call");
			}

		} catch (Exception e) {
			FilterHelper.addCORS(resp);
			FilterHelper.changeContent(resp, new ErrorMessage(401, e.getMessage()).toJson());
		} finally {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
