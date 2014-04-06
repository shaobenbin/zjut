package cn.edu.zjut.web.page;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_bulletinDomain;
import cn.edu.zjut.cfg.domain.Zjut_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_menu_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_partner_universitiesDomain;
import cn.edu.zjut.cfg.domain.Zjut_resourcesDomain;
import cn.edu.zjut.cfg.domain.Zjut_school_lifeDomain;
import cn.edu.zjut.cfg.domain.Zjut_studentDomain;
import cn.edu.zjut.util.ImagePathAnalyse;

public class Filepathchange extends DefaultAction {
	private final String sourceBaseUrl = "/zjut";
	private final String targetBaseUrl = "";

	public void bulletin() {
		List<Zjut_bulletinDomain> bulletinList = (new Zjut_bulletinDomain())
				.selectAll();
		for (Zjut_bulletinDomain domain : bulletinList) {
			String context = domain.getContext();
			List imgList = ImagePathAnalyse.instance.analyseImagePaths(context);
			if (imgList == null || imgList.isEmpty())
				continue;
			for (int i = 0; i < imgList.size(); i++) {
				String imgPath = (String) imgList.get(i);
				String replaceImgPath = imgPath.replaceAll(sourceBaseUrl,
						targetBaseUrl);
				context = context.replaceAll(imgPath, replaceImgPath);
			}
			domain.setContext(context);
			domain.update();
		}
	}

	public void content() {
		List<Zjut_contentDomain> contentList = (new Zjut_contentDomain())
				.selectAll();
		for (Zjut_contentDomain domain : contentList) {
			String content = domain.getContent();
			List imgList = ImagePathAnalyse.instance.analyseImagePaths(content);
			if (imgList == null || imgList.isEmpty())
				continue;
			for (int i = 0; i < imgList.size(); i++) {
				String imgPath = (String) imgList.get(i);
				String replaceImgPath = imgPath.replaceAll(sourceBaseUrl,
						targetBaseUrl);
				content = content.replaceAll(imgPath, replaceImgPath);
			}
			domain.setContent(content);
			domain.update();
		}
	}

	public void news() {
		List<Zjut_newDomain> newsList = (new Zjut_newDomain()).selectAll();
		for (Zjut_newDomain domain : newsList) {
			String context = domain.getContext();
			List imgList = ImagePathAnalyse.instance.analyseImagePaths(context);
			if (imgList != null && !imgList.isEmpty()) {
				for (int i = 0; i < imgList.size(); i++) {
					String imgPath = (String) imgList.get(i);
					String replaceImgPath = imgPath.replaceAll(sourceBaseUrl,
							targetBaseUrl);
					context = context.replaceAll(imgPath, replaceImgPath);
				}
			}

			String image2index = domain.getImage2index();
			if (image2index != null) {
				image2index = image2index.replaceAll(sourceBaseUrl,
						targetBaseUrl);
				domain.setImage2index(image2index);
			}
			domain.setContext(context);
			domain.update();
		}
	}

	public void menucontent() {
		List<Zjut_menu_contentDomain> menuContentList = (new Zjut_menu_contentDomain())
				.selectAll();
		for (Zjut_menu_contentDomain domain : menuContentList) {
			String content = domain.getContent();
			List imgList = ImagePathAnalyse.instance.analyseImagePaths(content);
			if (imgList == null || imgList.isEmpty())
				continue;
			for (int i = 0; i < imgList.size(); i++) {
				String imgPath = (String) imgList.get(i);
				String replaceImgPath = imgPath.replaceAll(sourceBaseUrl,
						targetBaseUrl);
				content = content.replaceAll(imgPath, replaceImgPath);
			}
			domain.setContent(content);
			domain.update();
		}
	}

	public void universities() {
		List<Zjut_partner_universitiesDomain> universitiesList = (new Zjut_partner_universitiesDomain())
				.selectAll();
		for (Zjut_partner_universitiesDomain domain : universitiesList) {
			String imgPath = domain.getImg_path();
			if(imgPath == null)continue;
			String replaceImgPath = imgPath.replaceAll(sourceBaseUrl,
					targetBaseUrl);
			domain.setImg_path(replaceImgPath);
			domain.update();
		}
	}

	public void life() {
		List<Zjut_school_lifeDomain> lifeList = (new Zjut_school_lifeDomain())
				.selectAll();
		for (Zjut_school_lifeDomain domain : lifeList) {
			String imgPath = domain.getImage();
			if(imgPath == null)continue;
			String replaceImgPath = imgPath.replaceAll(sourceBaseUrl,
					targetBaseUrl);
			domain.setImage(replaceImgPath);
			domain.update();
		}
	}

	public void student() {
		List<Zjut_studentDomain> studentList = (new Zjut_studentDomain())
				.selectAll();
		for (Zjut_studentDomain domain : studentList) {
			String imgPath = domain.getImg_path();
			if(imgPath == null)continue;
			String replaceImgPath = imgPath.replaceAll(sourceBaseUrl,
					targetBaseUrl);
			domain.setImg_path(replaceImgPath);
			domain.update();
		}
	}

	public void resources() {
		List<Zjut_resourcesDomain> resourcesList = (new Zjut_resourcesDomain())
				.selectAll();
		for (Zjut_resourcesDomain domain : resourcesList) {
			String filePath = domain.getDownload_path();
			if(filePath == null)continue;
			String replaceFilePath = filePath.replaceAll(sourceBaseUrl,
					targetBaseUrl);
			domain.setDownload_path(replaceFilePath);
			domain.update();
		}
	}

	public void replace() {
		bulletin();
		content();
		news();
		menucontent();
		universities();
		life();
		student();
		resources();
	}
//
//	public static void main(String[] ben) {
//		Filepathchange fc = new Filepathchange();
//		fc.replace();
//	}
}
