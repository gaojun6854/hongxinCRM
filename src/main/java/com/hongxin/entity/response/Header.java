package com.hongxin.entity.response;

import java.io.Serializable;

public class Header implements Serializable{
	private static final long serialVersionUID = -8685352606331031602L;
	private int code;
	private String msg;
	private Object info;
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
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public Header(int code, String msg, Object info) {
		super();
		this.code = code;
		this.msg = msg;
		this.info = info;
	}
	public Header() {
		super();
	}
	@Override
	public String toString() {
		return "[code=" + code + ", msg=" + msg + ", info=" + info + "]";
	}
	
}
