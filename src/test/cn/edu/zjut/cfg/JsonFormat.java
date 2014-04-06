package test.cn.edu.zjut.cfg;

import cn.edu.zjut.util.JsonFormatUtil;

public class JsonFormat {
	public static void main(String[] ben){
		String testStr = "2012年11月7日和17日，国际学院学生工作部先后组织了98名在读语言进修留学生和80名学历留学生赴东阳横店影视城秋游活动。此次秋游活动是国际学院\u201c中国梦\u2022留学生探索中国传统文化\u201d 的系列活动之一，深受广大留学生欢迎。\r\n\r\n";
		System.out.println(JsonFormatUtil.instance.format(testStr));
	}

}
