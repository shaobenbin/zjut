var rewardsolution_totalSize;
var rewardsolution_currentPage=0;
var rewardsolution_pageSize = 15;
var rewardsolutionList;
var rewardsolutionTitleSize = 50;
var rewardsolutionBaseUrl ;
function rewardsolution_init(_rewardsolutionList,baseUrl){
	rewardsolutionList	= eval('(' + _rewardsolutionList + ')');
	rewardsolutionBaseUrl = baseUrl;
	rewardsolution_totalSize = rewardsolutionList.length;
	rewardsolution_generatePage();
	rewardsolution_go2Page(1);
}

function rewardsolution_generatePage(){
	var pagehtml="<a href=\"javascript:rewardsolution_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<rewardsolution_totalSize/rewardsolution_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=rewardsolution_"+id+" href=\"javascript:rewardsolution_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:rewardsolution_goNext()\" class=\"next\">下一页</a>"
	$("#rewardsolution_page").html(pagehtml)
}
		
		
function rewardsolution_goPrev(){
	if(rewardsolution_currentPage>0){
		rewardsolution_go2Page(rewardsolution_currentPage);
	}
}
function rewardsolution_goNext(){
	if(rewardsolution_currentPage<(rewardsolution_totalSize/rewardsolution_pageSize-1)){
		rewardsolution_go2Page(rewardsolution_currentPage+2);
	}
}
		
function rewardsolution_go2Page(id){
	rewardsolution_currentPage = id-1;
	var html="";
	for(var i = rewardsolution_currentPage*rewardsolution_pageSize;i<(rewardsolution_currentPage+1)*rewardsolution_pageSize&&i<rewardsolution_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+rewardsolutionBaseUrl+"/student/content/detail.html?id="+rewardsolutionList[i].id+"\" title="+rewardsolutionList[i].title+" href=\"#\" title="+rewardsolutionList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(rewardsolutionList[i].title.length>rewardsolutionTitleSize){
			tempTitle = rewardsolutionList[i].title.substring(0,rewardsolutionTitleSize)+"...";
		}else{
			tempTitle = rewardsolutionList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+rewardsolutionList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#rewardsolution").empty();
	$("#rewardsolution").append(html);
			
	//设置点中的样式
	for(var i=1;i<=rewardsolution_totalSize/rewardsolution_pageSize;i++){
		$("#rewardsolution_"+i).attr("class","");
	}
	$("#rewardsolution_"+id).attr("class","cur");
}
		