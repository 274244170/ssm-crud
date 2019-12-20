package com.atguigu.crud.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterceptorJDKProxy implements InvocationHandler
{
	private Object target;//真实对象
	private String interceptorClass = null;//拦截器全限定名

	public InterceptorJDKProxy(Object target, String interceptorClass)
	{
		this.target = target;
		this.interceptorClass = interceptorClass;
	}

	/**
	 * 绑定被代理对象并返回一个代理对象
	 *
	 */
	public static Object bind(Object target, String interceptorClass)
	{
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
		        new InterceptorJDKProxy(target, interceptorClass));
	}

	/**
	 * @param proxy 代理对象
	 * @param method 被调用方法
	 * @param args 方法的参数
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		if (interceptorClass == null)
		{
			return method.invoke(target, args);
		}
		Object result = null;
		//通过反射生成拦截器
		Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
		//调用前置方法
		if (interceptor.before(proxy, target, method, args))
		{
			result = method.invoke(target, args);
		}
		else
		{
			//调用环绕方法
			interceptor.around(proxy, target, method, args);
		}
		//调用后置方法
		interceptor.after(proxy, target, method, args);
		return result;
	}

}
