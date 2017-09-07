package com.xier.sesame.attence.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xier.sesame.common.uploadfile.manager.ImageFileManager;
import com.xier.sesame.attence.support.JsonBinder;
import com.xier.sesame.attence.utils.SessionHolder;
import com.xier.sesame.attence.web.model.User;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(MyWebMvcConfigurerAdapter.class);
	private static JsonBinder binder = JsonBinder.buildNonDefaultBinder();
	
	@Autowired
	private ImageFileManager imageFileManager;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/login");
    	registry.addInterceptor(new ParamsCleanInterceptor()).addPathPatterns("/**");
    	registry.addInterceptor(new GlobalConfigInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
    
    /**
     * 身份认证拦截器
     * @author tianxiang
     *
     */
    public class AuthInterceptor extends HandlerInterceptorAdapter {
		
    	@Override
    	public boolean preHandle(HttpServletRequest request,
    			HttpServletResponse response, Object handler) throws Exception {

    		HttpSession session = request.getSession();
    		SessionHolder.setSession(session);
    		
    		User user = (User)session.getAttribute("sessionUser");
    		if(user != null){ // 用户已登录过
    			// TODO 后续可以补充更细粒度的权限校验
    			return super.preHandle(request, response, handler);
    			
    		} else { // 用户未登录过
    			
    			try {
					PrintWriter writer = response.getWriter();

					Map<String, String> map = new HashMap<String, String>();
					map.put("result", "-1");
					map.put("message", "会话超时，请重新登陆！");

					writer.write(binder.toJson(map)); 
					writer.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
    		}
    	}

    }
    
    /**
     * 参数清理过滤器: 1.参数首位去空,2.移除可能导致XSS攻击的非法字符
     * @author tianxiang
     *
     */
    public class ParamsCleanInterceptor extends HandlerInterceptorAdapter {
		
    	@Override
    	public boolean preHandle(HttpServletRequest request,
    			HttpServletResponse response, Object handler) throws Exception {

    		Map<String, String[]> paramMap = request.getParameterMap(); 
    		
    		return super.preHandle(request, response, handler);
    	}

    }
    
    public class GlobalConfigInterceptor extends HandlerInterceptorAdapter {
    		
    	@Override
    	public boolean preHandle(HttpServletRequest request,
    			HttpServletResponse response, Object handler) throws Exception {

    		request.setAttribute("rootImagePath", imageFileManager.getImageWebRoot());
    		return super.preHandle(request, response, handler);
    	}

    }
    
}
