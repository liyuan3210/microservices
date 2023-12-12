package com.liyuan3210.web.comm.vo;

public class Result {
	private boolean ret;
	private String msg;
	private Object data;
	public Result(boolean ret){
		this.ret=ret;
	}
	public Result(boolean ret,String msg){
		this.ret=ret;
		this.msg=msg;
	}
	public Result(boolean ret,String msg,Object data){
		this.ret=ret;
		this.msg=msg;
		this.data=data;
	}
	public boolean isRet() {
		return ret;
	}
	public void setRet(boolean ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
