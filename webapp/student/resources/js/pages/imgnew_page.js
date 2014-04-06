var imgnew_totalSize;
var imgnew_currentPage=0;
var imgnew_pageSize = 5;
var imgnewList;
var imgnewTitleSize = 20;
var imgnewBaseUrl ;
function imgnew_init(_imgnewList,baseUrl){
	imgnewList	= eval('(' + _imgnewList + ')');
	imgnewBaseUrl = baseUrl;
	imgnew_totalSize = imgnewList.length;
	imgnew_generatePage();
	imgnew_go2Page(1);
}

function imgnew_generatePage(){
	var pagehtml="<a href=\"javascript:imgnew_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<imgnew_totalSize/imgnew_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=imgnew_"+id+" href=\"javascript:imgnew_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:imgnew_goNext()\" class=\"next\">下一页</a>"
	$("#imgnew_page").html(pagehtml)
}
		
		
function imgnew_goPrev(){
	if(imgnew_currentPage>0){
		imgnew_go2Page(imgnew_currentPage);
	}
}
function imgnew_goNext(){
	if(imgnew_currentPage<(imgnew_totalSize/imgnew_pageSize-1)){
		imgnew_go2Page(imgnew_currentPage+2);
	}
}
		
function imgnew_go2Page(id){
	imgnew_currentPage = id-1;
	var html="";
	for(var i = imgnew_currentPage*imgnew_pageSize;i<(imgnew_currentPage+1)*imgnew_pageSize&&i<imgnew_totalSize;i++){
		html+="<div  style=\"cursor:pointer\" onclick=\"javascript:window.open('"+imgnewBaseUrl+"/student/new/detail.html?id="+imgnewList[i].id+"')\" class=\"imgNew\"><img src=\""+imgnewList[i].image2index+"\" /><h3>";
		
		var tempTitle;
		if(imgnewList[i].title.length>imgnewTitleSize){
			tempTitle = imgnewList[i].title.substring(0,imgnewTitleSize)+"...";
		}else{
			tempTitle = imgnewList[i].title;
		}
		var tempSummary;
		if(imgnewList[i].summary.length>80){
			tempSummary = imgnewList[i].summary.substring(0,80)+"...";
		}else{
			tempSummary = imgnewList[i].summary;
		}
		html+=tempTitle+"</h3><p>"+tempSummary+"</p>";
		html+="</div>";
	}
	$("#imgnew").empty();
	$("#imgnew").append(html);
			
	//设置点中的样式
	for(var i=1;i<=imgnew_totalSize/imgnew_pageSize;i++){
		$("#imgnew_"+i).attr("class","");
	}
	$("#imgnew_"+id).attr("class","cur");
}
		