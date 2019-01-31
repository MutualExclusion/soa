package cn.solarcat.common.pojo;

public class LogEntity {
	/**
	 * 层级水平
	 */
	private LEVEL level;
	/**
	 * 类名
	 */
	private String ClassName;
	/**
	 * 方法
	 */
	private String Method;
	/**
	 * 动作
	 */
	private ACTION action;
	/**
	 * 开始时间
	 */
	private long startTime;
	/**
	 * 结束时间
	 */
	private long endTime;
	/**
	 * 消耗时间
	 */
	private long expendTime;
	/**
	 * 请求参数
	 */
	private Object[] params;
	/**
	 * 返回参数
	 */
	private Object[] obj;
	/**
	 * 异常信息
	 */
	private String exception;

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Object[] getObj() {
		return obj;
	}

	public void setObj(Object[] obj) {
		this.obj = obj;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public LEVEL getLevel() {
		return level;
	}

	public void setLevel(LEVEL level) {
		this.level = level;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public String getMethod() {
		return Method;
	}

	public void setMethod(String method) {
		Method = method;
	}

	public ACTION getAction() {
		return action;
	}

	public void setAction(ACTION action) {
		this.action = action;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getExpendTime() {
		return expendTime;
	}

	public void setExpendTime(long expendTime) {
		this.expendTime = expendTime;
	}

	public LogEntity() {
		super();
	}

	public LogEntity(LEVEL level, String className, String method, ACTION action, long startTime, long endTime,
			long expendTime, Object[] params, Object[] obj, String exception) {
		super();
		this.level = level;
		ClassName = className;
		Method = method;
		this.action = action;
		this.startTime = startTime;
		this.endTime = endTime;
		this.expendTime = expendTime;
		this.params = params;
		this.obj = obj;
		this.exception = exception;
	}

}
