package cn.edu.zjut.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.zjut.cfg.domain.Zjut_newDomain;

public enum ImagePathAnalyse {
	instance;
	public List analyseImagePaths(String content) {
		if (content == null || "".equals(content)) {
			return null;
		}
		List imageList = new ArrayList();
		String img = "";
		String regEx_img = "<img.*src=(.*?)[^>]*?>"; // 图片链接地址
		// String regEx_img = "src=\"?(.*?)(\"|>|\\s+)";
		Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		Matcher m_image = p_image.matcher(content);
		while (m_image.find()) {
			img = m_image.group();
			Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img);
			while (m.find()) {
				imageList.add(m.group(1));
			}
		}
		return imageList;
	}

	public static void main(String[] ben) {
		Zjut_newDomain zjut_newDomain = new Zjut_newDomain();
		zjut_newDomain.setTitle("测试下图片");
		zjut_newDomain.loadEqual();
		System.out.println(ImagePathAnalyse.instance
				.analyseImagePaths(zjut_newDomain.getContext()));
	}
}
