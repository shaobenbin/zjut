package cn.edu.zjut.util;

public enum JsonFormatUtil {
	instance;
	public String format(String jsonString) {
		

		if (jsonString.indexOf("'") != -1) {
			// 将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
			jsonString = jsonString.replaceAll("'", "\u0060");
		}
		if (jsonString.indexOf("\"") != -1) {
			// 将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
			jsonString = jsonString.replaceAll("\"", "\\\"");
		}

		if (jsonString.indexOf("\r\n") != -1) {
			// 将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
			jsonString = jsonString.replaceAll("\r\n", "\\u000d\\u000a");
		}
		if (jsonString.indexOf("\n") != -1) {
			// 将换行转换一下，因为JSON串中字符串不能出现显式的换行
			jsonString = jsonString.replaceAll("\n", "\\u000a");
		}

		return jsonString;
	}
}
