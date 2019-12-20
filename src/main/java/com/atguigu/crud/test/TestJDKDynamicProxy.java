package com.atguigu.crud.test;

import org.junit.Test;

import com.atguigu.crud.interceptor.InterceptorJDKProxy;
import com.atguigu.crud.service.HelloService;
import com.atguigu.crud.service.impl.HelloServiceImpl;

public class TestJDKDynamicProxy
{

	@Test
	public void test()
	{
		HelloService helloServiceProxy = (HelloService) InterceptorJDKProxy.bind(new HelloServiceImpl(),
		        "com.atguigu.crud.interceptor.impl.InterceptorImpl");
		helloServiceProxy.sayHello();
	}
}
