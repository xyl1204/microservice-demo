package com.xyl.microservice.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreRequestLogFilter extends ZuulFilter{

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PreRequestLogFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		LOGGER.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURI().toString()));
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
