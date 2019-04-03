package cn.solarcat.item.util;

import java.io.File;

public class FileHandle {

	/**
	 * 删除以html结尾的文件
	 * 
	 * @param f 文件路径指针
	 */
	public static void delete(File f) {
		File[] fi = f.listFiles();
		for (File file : fi) {
			if (ItemContact.HTML_SUFFIX.equals(file.getName().substring(file.getName().lastIndexOf(".") + 1))) {
				file.delete();
			}
		}
	}
}
