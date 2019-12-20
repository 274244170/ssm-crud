package com.atguigu.crud.dto;

import java.io.Serializable;

/**
 * 返回结果对象
 * @author dengzb
 *
 */
public class RespResult implements Serializable
{
	private static final long serialVersionUID = -5336534294720358491L;
	/**
	 * 返回CODE码.
	 */
	private Integer code;
	/**
	 * 返回结果信息.
	 */
	private String msg;
	/**
	 * 返回结果数据.
	 */

	private Object result;

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Object getResult()
	{
		return result;
	}

	public void setResult(Object result)
	{
		this.result = result;
	}

	public RespResult()
	{
	}

	public RespResult(Integer code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public RespResult(Integer code, String msg, Object result)
	{
		this.code = code;
		this.msg = msg;
		this.result = result;
	}
}
