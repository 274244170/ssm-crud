package com.atguigu.crud.enums;

public enum ResultCode
{
	// ----------------系统级返回错误码----------------
	C800200(800200, "操作成功"), 
	C800401(800401, "系统错误"),
	C800500(800500, "操作失败");
	
	private int code;
	private String reason;
	
	private ResultCode()
	{
		
	}
	
	private ResultCode(int code, String reason)
	{
		this.code = code;
		this.reason = reason;
	}


	public int getCode()
	{
		return code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public String getReason()
	{
		return reason;
	}
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	
	

}
