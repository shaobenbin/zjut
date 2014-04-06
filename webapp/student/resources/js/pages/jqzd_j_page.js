var jiang_totalSize;
var jiang_currentPage=0;
var jiang_pageSize = 5;
var jiangList;
var jiangTitleSize = 50;
var jiangBaseUrl ;
function jiang_init(_jiangList,baseUrl){
	jiangList	= eval('(' + _jiangList + ')');
	jiangBaseUrl = baseUrl;
	jiang_totalSize = jiangList.length;
	jiang_generatePage();
	jiang_go2Page(1);
}

function jiang_generatePage(){
	var pagehtml="<a href=\"javascript:jiang_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<jiang_totalSize/jiang_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=jiang_"+id+" href=\"javascript:jiang_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:jiang_goNext()\" class=\"next\">下一页</a>"
	$("#jiang_page").html(pagehtml)
}
		
		
function jiang_goPrev(){
	if(jiang_currentPage>0){
		jiang_go2Page(jiang_currentPage);
	}
}
function jiang_goNext(){
	if(jiang_currentPage<(jiang_totalSize/jiang_pageSize-1)){
		jiang_go2Page(jiang_currentPage+2);
	}
}
		
function jiang_go2Page(id){
	jiang_currentPage = id-1;
	var html="";
	for(var i = jiang_currentPage*jiang_pageSize;i<(jiang_currentPage+1)*jiang_pageSize&&i<jiang_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+jiangBaseUrl+"/student/content/detail.html?id="+jiangList[i].id+"\" title="+jiangList[i].title+" href=\"#\" title="+jiangList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(jiangList[i].title.length>jiangTitleSize){
			tempTitle = jiangList[i].title.substring(0,jiangTitleSize)+"...";
		}else{
			tempTitle = jiangList[i].title;
		}
		html+=tempTitle+"</span>";
		html+="</a>";
	}
	$("#jiang").empty();
	$("#jiang").append(html);
			
	//设置点中的样式
	for(var i=1;i<=jiang_totalSize/jiang_pageSize;i++){
		$("#jiang_"+i).attr("class","");
	}
	$("#jiang_"+id).attr("class","cur");
}
		