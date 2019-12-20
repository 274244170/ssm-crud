package com.atguigu.crud.dto;


import java.io.Serializable;
import java.util.List;

/**
 * 返回结果分页对象
 * @author Liu.Z.H
 *
 */
public class RespPageResult implements Serializable {
	private static final long serialVersionUID = 2349669876141156412L;
	/**
	 * 返回CODE码.
	 */
	private Integer code;
	/**
	 * 返回结果信息.
	 */
	private String msg;
	//当前页
	private int pageNum;
	//每页的数量
	private int pageSize;
	//总记录数
	private long total;
	//总页数
    private int pages;
	//结果集
	private List<?> list;

	public RespPageResult() {
	}

	public RespPageResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

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

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public long getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	public List<?> getList()
	{
		return list;
	}

	public void setList(List<?> list)
	{
		this.list = list;
	}

	public int getPages()
	{
		return pages;
	}

	public void setPages(int pages)
	{
		this.pages = pages;
	}

	public RespPageResult(Integer code, String msg, PageVo pageVO) {
		this.code = code;
		this.msg = msg;
		this.pageNum = pageVO.getPageNum();
		this.pageSize = pageVO.getPageSize();
		this.total = pageVO.getTotal();
		this.list = pageVO.getList();

    }
}
