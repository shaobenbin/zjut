var recruitment_info_totalSize;
var recruitment_info_currentPage=0;
var recruitment_info_pageSize = 25;
var recruitment_infoList;
var recruitment_infoTitleSize = 50;
var recruitment_infoBaseUrl ;
function recruitment_info_init(_recruitment_infoList,baseUrl){
	recruitment_infoList	= eval('(' + _recruitment_infoList + ')');
	recruitment_infoBaseUrl = baseUrl;
	recruitment_info_totalSize = recruitment_infoList.length;
	recruitment_info_generatePage();
	recruitment_info_go2Page(1);
}

function recruitment_info_generatePage(){
	var pagehtml="<a href=\"javascript:recruitment_info_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<recruitment_info_totalSize/recruitment_info_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=recruitment_info_"+id+" href=\"javascript:recruitment_info_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:recruitment_info_goNext()\" class=\"next\">下一页</a>"
	$("#recruitment_info_page").html(pagehtml)
}
		
		
function recruitment_info_goPrev(){
	if(recruitment_info_currentPage>0){
		recruitment_info_go2Page(recruitment_info_currentPage);
	}
}
function recruitment_info_goNext(){
	if(recruitment_info_currentPage<(recruitment_info_totalSize/recruitment_info_pageSize-1)){
		recruitment_info_go2Page(recruitment_info_currentPage+2);
	}
}
		
function recruitment_info_go2Page(id){
	recruitment_info_currentPage = id-1;
	var html="";
	for(var i = recruitment_info_currentPage*recruitment_info_pageSize;i<(recruitment_info_currentPage+1)*recruitment_info_pageSize&&i<recruitment_info_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+recruitment_infoBaseUrl+"/student/content/detail.html?id="+recruitment_infoList[i].id+"\" title="+recruitment_infoList[i].title+" href=\"#\" title="+recruitment_infoList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(recruitment_infoList[i].title.length>recruitment_infoTitleSize){
			tempTitle = recruitment_infoList[i].title.substring(0,recruitment_infoTitleSize)+"...";
		}else{
			tempTitle = recruitment_infoList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+recruitment_infoList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#recruitment_info").empty();
	$("#recruitment_info").append(html);
			
	//设置点中的样式
	for(var i=1;i<=recruitment_info_totalSize/recruitment_info_pageSize;i++){
		$("#recruitment_info_"+i).attr("class","");
	}
	$("#recruitment_info_"+id).attr("class","cur");
}
		