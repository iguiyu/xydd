package com.xydd.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xydd.util.WeixinUtil;
import com.xydd.weixin.pojo.WeiXinStatic;

import passport.AuthenticationTicket;
import passport.PassportServiceLocator;
import passport.PassportServiceSoap_PortType;

public class TeacherInterceptor implements HandlerInterceptor {

    /** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */ 
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作  
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     *  
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */ 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
    	
        
        String openid="";
        openid=(String)request.getSession().getAttribute("openid");
        
        //如果session是空的，调微信接口获取openid
        if(StringUtils.isEmpty(openid))
        {
        	String code=request.getParameter("code");
        	if(!StringUtils.isEmpty(code))
        	{
	    		WeixinUtil util=new WeixinUtil();
	    		openid=util.getOpenId(WeiXinStatic.appId, WeiXinStatic.appSecret, code);
        	}
        }
        
        //return true;
        
        if(!StringUtils.isEmpty(openid)){//直接进入系统
            request.getSession().setAttribute("openid", openid);
            return true;
        }
        else{
        	//进入微信跳转页面
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeiXinStatic.appId+"&redirect_uri=http://www.fuyatou.com/xydd/teacher/index.shtml?response_type=code&scope=snsapi_base&state=1#wechat_redirect");
            
            return false;
        }
    }

}
