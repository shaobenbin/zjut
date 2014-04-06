var zhu_totalSize;
var zhu_currentPage=0;
var zhu_pageSize = 5;
var zhuList;
var zhuTitleSize = 50;
var zhuBaseUrl ;
function zhu_init(_zhuList,baseUrl){
	zhuList	= eval('(' + _zhuList + ')');
	zhuBaseUrl = baseUrl;
	zhu_totalSize = zhuList.length;
	zhu_generatePage();
	zhu_go2Page(1);
}

function zhu_generatePage(){
	var pagehtml="<a href=\"javascript:zhu_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<zhu_totalSize/zhu_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=zhu_"+id+" href=\"javascript:zhu_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:zhu_goNext()\" class=\"next\">下一页</a>"
	$("#zhu_page").html(pagehtml)
}
		
		
function zhu_goPrev(){
	if(zhu_currentPage>0){
		zhu_go2Page(zhu_currentPage);
	}
}
function zhu_goNext(){
	if(zhu_currentPage<(zhu_totalSize/zhu_pageSize-1)){
		zhu_go2Page(zhu_currentPage+2);
	}
}
		
function zhu_go2Page(id){
	zhu_currentPage = id-1;
	var html="";
	for(var i = zhu_currentPage*zhu_pageSize;i<(zhu_currentPage+1)*zhu_pageSize&&i<zhu_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+zhuBaseUrl+"/student/content/detail.html?id="+zhuList[i].id+"\" title="+zhuList[i].title+" href=\"#\" title="+zhuList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(zhuList[i].title.length>zhuTitleSize){
			tempTitle = zhuList[i].title.substring(0,zhuTitleSize)+"...";
		}else{
			tempTitle = zhuList[i].title;
		}
		html+=tempTitle+"</span>";
		html+="</a>";
	}
	$("#zhu").empty();
	$("#zhu").append(html);
			
	//设置点中的样式
	for(var i=1;i<=zhu_totalSize/zhu_pageSize;i++){
		$("#zhu_"+i).attr("class","");
	}
	$("#zhu_"+id).attr("class","cur");
}
		