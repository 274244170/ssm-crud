package com.atguigu.crud.dto;

import java.util.HashMap;
import java.util.Map;

import com.atguigu.crud.enums.ResultCode;

/**
 * 通用的返回的类
 * 
 * @author lfy
 * 
 */
public class Msg {
	//状态码 
	private int code;
	
	//提示信息
	private String msg;
	
	//用户要返回给浏览器的数据
	private Map<String, Object> extend = new HashMap<String, Object>();

	public void setResCode(ResultCode resCode)
	{
		this.code = resCode.getCode();
		this.msg = resCode.getReason();
	}
	
	public Msg add(String key,Object value){
		this.getExtend().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
}
