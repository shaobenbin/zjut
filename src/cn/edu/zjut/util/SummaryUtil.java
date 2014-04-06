package cn.edu.zjut.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SummaryUtil {
	instance;

	public String summary(String content) {
		String result = content;
		String regEx_img = "<img.*src=(.*?)[^>]*?>";
		String img = "";
		Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		Matcher m_image = p_image.matcher(content);
		while (m_image.find()) {
			img = m_image.group();
			result = result.replace(img, "");
		}
		return result;
	}

	public String removeHtmlTag(String content) {
		if (content == null || "".equals(content)) {
			return null;
		}
		return content.replaceAll("<[.[^<]]*>", "").replaceAll("[//n|//r]", "")
				.replaceAll("&bsp;", "").replaceAll("\n", "").replaceAll("\t", "");
	}

	public String removeHtmlTag(String content, int size) {
		String result = this.removeHtmlTag(content);
		if (result != null && result.length() > size) {
			return result.substring(0, size)+"...";
		}
		return result;
	}
}
