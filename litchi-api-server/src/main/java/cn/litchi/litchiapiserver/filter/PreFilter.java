package cn.litchi.litchiapiserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreFilter extends ZuulFilter {
    public PreFilter() {
        super();
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String requestUrl = request.getRequestURL().toString();
        String requestUri = request.getRequestURI();
        String zuul = requestUrl.substring(0,requestUrl.indexOf(requestUri)); // zuul根路径
        ctx.addZuulRequestHeader("zuul", zuul);

        return null;
    }



}
