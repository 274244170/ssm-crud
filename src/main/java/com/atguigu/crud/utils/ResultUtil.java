package com.atguigu.crud.utils;

import com.atguigu.crud.dto.PageVo;
import com.atguigu.crud.dto.RespPageResult;
import com.atguigu.crud.dto.RespResult;
import com.atguigu.crud.enums.ResultCode;

public class ResultUtil
{

	/**
	 * 操作成功
	 * @return
	 */
	public static RespResult getSuccessResult()
	{
		return new RespResult(ResultCode.C800200.getCode(), ResultCode.C800200.getReason());
	}

	/**
	 * 操作成功并返回结果对象
	 * @param result
	 * @return
	 */
	public static RespResult getSuccessResult(Object result)
	{
		return new RespResult(ResultCode.C800200.getCode(), ResultCode.C800200.getReason(), result);
	}

	public static RespPageResult getSuccessPageResult(PageVo pageVO)
	{
		return new RespPageResult(ResultCode.C800200.getCode(), ResultCode.C800200.getReason(), pageVO);
	}

	/**
	 * 操作失败
	 * @return
	 */
	public static RespResult getFailedResult()
	{
		return new RespResult(ResultCode.C800500.getCode(), ResultCode.C800500.getReason());
	}

	public static RespPageResult getFailedPageResult(PageVo pageVO)
	{
		return new RespPageResult(ResultCode.C800500.getCode(), ResultCode.C800500.getReason(), pageVO);
	}

	public static RespPageResult getFailedPageResult(String msg)
	{
		return new RespPageResult(ResultCode.C800500.getCode(), msg, null);
	}

	public static RespPageResult getFailedPageResult()
	{
		return new RespPageResult(ResultCode.C800500.getCode(), ResultCode.C800500.getReason(), null);
	}

	public static RespResult getResultByResultCode(int ResultCode)
	{
		return new RespResult(ResultCode, "");
	}

	/**
	 * 操作失败并返回结果消息
	 * @param msg
	 * @return
	 */
	public static RespResult getFailedResult(String msg)
	{
		return new RespResult(ResultCode.C800500.getCode(), msg);
	}

	public static RespResult getFailedResult(int ResultCode, String msg)
	{
		return new RespResult(ResultCode, msg);
	}
}
