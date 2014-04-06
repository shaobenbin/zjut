var technology_announcement_totalSize;
var technology_announcement_currentPage=0;
var technology_announcement_pageSize = 15;
var technology_announcementList;
var technology_announcementTitleSize = 50;
var technology_announcementBaseUrl ;
function technology_announcement_init(_technology_announcementList,baseUrl){
	technology_announcementList	= eval('(' + _technology_announcementList + ')');
	technology_announcementBaseUrl = baseUrl;
	technology_announcement_totalSize = technology_announcementList.length;
	technology_announcement_generatePage();
	technology_announcement_go2Page(1);
}

function technology_announcement_generatePage(){
	var pagehtml="<a href=\"javascript:technology_announcement_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<technology_announcement_totalSize/technology_announcement_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=technology_announcement_"+id+" href=\"javascript:technology_announcement_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:technology_announcement_goNext()\" class=\"next\">下一页</a>"
	$("#technology_announcement_page").html(pagehtml)
}
		
		
function technology_announcement_goPrev(){
	if(technology_announcement_currentPage>0){
		technology_announcement_go2Page(technology_announcement_currentPage);
	}
}
function technology_announcement_goNext(){
	if(technology_announcement_currentPage<(technology_announcement_totalSize/technology_announcement_pageSize-1)){
		technology_announcement_go2Page(technology_announcement_currentPage+2);
	}
}
		
function technology_announcement_go2Page(id){
	technology_announcement_currentPage = id-1;
	var html="";
	for(var i = technology_announcement_currentPage*technology_announcement_pageSize;i<(technology_announcement_currentPage+1)*technology_announcement_pageSize&&i<technology_announcement_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+technology_announcementBaseUrl+"/student/content/detail.html?id="+technology_announcementList[i].id+"\" title="+technology_announcementList[i].title+" href=\"#\" title="+technology_announcementList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(technology_announcementList[i].title.length>technology_announcementTitleSize){
			tempTitle = technology_announcementList[i].title.substring(0,technology_announcementTitleSize)+"...";
		}else{
			tempTitle = technology_announcementList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+technology_announcementList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#technology_announcement").empty();
	$("#technology_announcement").append(html);
			
	//设置点中的样式
	for(var i=1;i<=technology_announcement_totalSize/technology_announcement_pageSize;i++){
		$("#technology_announcement_"+i).attr("class","");
	}
	$("#technology_announcement_"+id).attr("class","cur");
}
		