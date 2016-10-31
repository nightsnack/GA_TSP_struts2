package org.nightsnack.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 创建一个类让其继承于AbstractInterceptor
 * @author Administrator
 *
 */
public class LoadInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("default interceptor");
		return invocation.invoke();
	}

}
