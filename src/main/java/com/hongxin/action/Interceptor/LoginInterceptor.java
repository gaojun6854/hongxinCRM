package com.hongxin.action.Interceptor;
/**
 * 登录拦截器
 */
import com.hongxin.entity.User;
/**
 * 登录拦截器
 */
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 通过invocation获取本次调用的上下文
		User user = (User) ActionContext.getContext().getSession().get("login_user");
		if (user == null) {
			return Action.LOGIN;
		}
		return invocation.invoke();
	}
}
