package com.atguigu.crud.enums;

public enum GenderEnum
{
	MAN(0, "男"), WOMAN(1, "女");

	private Integer key;
	private String value;

	private GenderEnum()
	{

	}

	private GenderEnum(Integer key, String value)
	{
		this.key = key;
		this.value = value;
	}

	public Integer getKey()
	{
		return key;
	}

	public void setKey(Integer key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

}
