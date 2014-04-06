var partydynamic_totalSize;
var partydynamic_currentPage=0;
var partydynamic_pageSize = 15;
var partydynamicList;
var partydynamicTitleSize = 50;
var partydynamicBaseUrl ;
function partydynamic_init(_partydynamicList,baseUrl){
	partydynamicList	= eval('(' + _partydynamicList + ')');
	partydynamicBaseUrl = baseUrl;
	partydynamic_totalSize = partydynamicList.length;
	partydynamic_generatePage();
	partydynamic_go2Page(1);
}

function partydynamic_generatePage(){
	var pagehtml="<a href=\"javascript:partydynamic_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<partydynamic_totalSize/partydynamic_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=partydynamic_"+id+" href=\"javascript:partydynamic_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:partydynamic_goNext()\" class=\"next\">下一页</a>"
	$("#partydynamic_page").html(pagehtml)
}
		
		
function partydynamic_goPrev(){
	if(partydynamic_currentPage>0){
		partydynamic_go2Page(partydynamic_currentPage);
	}
}
function partydynamic_goNext(){
	if(partydynamic_currentPage<(partydynamic_totalSize/partydynamic_pageSize-1)){
		partydynamic_go2Page(partydynamic_currentPage+2);
	}
}
		
function partydynamic_go2Page(id){
	partydynamic_currentPage = id-1;
	var html="";
	for(var i = partydynamic_currentPage*partydynamic_pageSize;i<(partydynamic_currentPage+1)*partydynamic_pageSize&&i<partydynamic_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+partydynamicBaseUrl+"/student/content/detail.html?id="+partydynamicList[i].id+"\" title="+partydynamicList[i].title+" href=\"#\" title="+partydynamicList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(partydynamicList[i].title.length>partydynamicTitleSize){
			tempTitle = partydynamicList[i].title.substring(0,partydynamicTitleSize)+"...";
		}else{
			tempTitle = partydynamicList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+partydynamicList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#partydynamic").empty();
	$("#partydynamic").append(html);
			
	//设置点中的样式
	for(var i=1;i<=partydynamic_totalSize/partydynamic_pageSize;i++){
		$("#partydynamic_"+i).attr("class","");
	}
	$("#partydynamic_"+id).attr("class","cur");
}
		