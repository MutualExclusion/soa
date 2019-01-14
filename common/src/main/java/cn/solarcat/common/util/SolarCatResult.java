package cn.solarcat.common.util;

import java.io.Serializable;

/**
 * 淘淘商城自定义响应结构
 */
public class SolarCatResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521159046130483582L;

	// 响应业务状态
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	public static SolarCatResult build(Integer status, String msg, Object data) {
		return new SolarCatResult(status, msg, data);
	}

	public static SolarCatResult ok(Object data) {
		return new SolarCatResult(data);
	}

	public static SolarCatResult ok() {
		return new SolarCatResult(null);
	}

	public SolarCatResult() {

	}

	public static SolarCatResult build(Integer status, String msg) {
		return new SolarCatResult(status, msg, null);
	}

	public SolarCatResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public SolarCatResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

//    public Boolean isOK() {
//        return this.status == 200;
//    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
