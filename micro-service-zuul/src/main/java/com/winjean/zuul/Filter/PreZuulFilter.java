package com.winjean.zuul.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PreZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
//        pre：路由之前
//        routing：路由之时
//        post： 路由之后
//        error：发送错误调用
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
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
//        String service_id = (String)ctx.get(FilterConstants.SERVICE_ID_KEY);

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        if(user!=null){
            if(user instanceof OAuth2Authentication){
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) user.getDetails() ;
                ctx.addZuulRequestHeader("Authorization", details.getTokenType() + " " + details.getTokenValue());
            }
        }

        /*HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object token = request.getParameter("token");
        if(token == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("ok");*/
        return null;
    }
}
