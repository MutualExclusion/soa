package cn.solarcat.common.pojo;

/**
 * 实现日志打印
 * 
 * @author MutualExclusion
 *
 */
public enum ACTION {
	/**
	 * 添加
	 */
	ADD,
	/**
	 * 删除
	 */
	DELETE;

	public static String ActiontoString(ACTION action) {
		String str = "";
		switch (action) {
		case ADD:
			str = "添加";
		case DELETE:
			str = "删除";
		}
		return str;
	}

}
