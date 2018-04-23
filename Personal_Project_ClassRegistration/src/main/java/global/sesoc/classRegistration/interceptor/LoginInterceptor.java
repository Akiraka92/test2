package global.sesoc.classRegistration.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import global.sesoc.classRegistration.vo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception {
		
		logger.info("LoginInterceptor executed");
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			String url = request.getContextPath() + "/";
			response.sendRedirect(url);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
	
	
}
