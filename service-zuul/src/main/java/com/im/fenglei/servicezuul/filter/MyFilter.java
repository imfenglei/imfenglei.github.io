package com.im.fenglei.servicezuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class MyFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

	/**
	 * run：过滤器的具体逻辑。
	 * 需要注意，这里我们通过requestContext.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
	 * 然后通过requestContext.setResponseStatusCode(401)设置了其返回的错误码
	 */
	@Override
	public Object run() {
		
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		String name = request.getParameter("name");
		if(null == name || "".equals(name)) {
			LOGGER.error("name can not be null, name={}", name);
			// 过滤该请求，不对其进行路由
			requestContext.setSendZuulResponse(false);
			// 返回错误码
			requestContext.setResponseStatusCode(401);
			//可以把一些值放到ctx中，便于后面的filter获取使用  
			// 设值，让下一个Filter看到上一个Filter的状态
			requestContext.set("isSuccess", false);
			requestContext.setResponseBody("{'result':'name can not be null'}");
		} else {
			LOGGER.info("name is not null, name={}",name);
			// 对该请求进行路由 
			requestContext.setSendZuulResponse(true);
			// http状态码
			requestContext.setResponseStatusCode(200);
			//可以把一些值放到ctx中，便于后面的filter获取使用  
			// 设值，让下一个Filter看到上一个Filter的状态
			requestContext.set("isSuccess", true);
		}
		
		return null;
	}

	/**
	 * shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。
	 * 可以有如下写法，依据于上一个过滤器的执行结果来判断是否进入当前过滤器
	 * RequestContext ctx = RequestContext.getCurrentContext();
	 * return (boolean) ctx.get("isSuccess");// 如果前一个过滤器的结果为true，则说明上一个过滤器成功了，需要进入当前的过滤，如果前一个过滤器的结果为false，则说明上一个过滤器没有成功，则无需进行下面的过滤动作了，直接跳过后面的所有过滤器并返回结果  
	 */
	@Override
	public boolean shouldFilter() {
		//RequestContext ctx = RequestContext.getCurrentContext();
		//return (boolean) ctx.get("isSuccess");// 前一个过滤器的结果
		return true;
	}

	/**
	 * filterOrder：通过int值来定义过滤器的执行顺序
	 */
	@Override
	public int filterOrder() {
		return 0;// 优先级为0，数字越大，优先级越低  
	}

	/**
	 * filterType：过滤器类型，
	 * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
	 * pre：可以在请求被路由之前调用
	 * route：在路由请求时候被调用
	 * post：在route和error过滤器之后被调用
	 * error：处理请求时发生错误时被调用
	 * 顺序: pre ->routing -> post ,以上3个顺序出现异常时都可以触发error类型的filter 
	 */
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;// 前置过滤器
	}

}
