var xzfc_totalSize;
var xzfc_currentPage=0;
var xzfc_pageSize = 15;
var xzfcList;
var xzfcTitleSize = 30;
var xzfcBaseUrl ;
function xzfc_init(_xzfcList,baseUrl){
	xzfcList	= eval('(' + _xzfcList + ')');
	xzfcBaseUrl = baseUrl;
	xzfc_totalSize = xzfcList.length;
	xzfc_generatePage();
	xzfc_go2Page(1);
}

function xzfc_generatePage(){
	var pagehtml="<a href=\"javascript:xzfc_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<xzfc_totalSize/xzfc_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=xzfc_"+id+" href=\"javascript:xzfc_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:xzfc_goNext()\" class=\"next\">下一页</a>"
	$("#xzfc_page").html(pagehtml)
}
		
		
function xzfc_goPrev(){
	if(xzfc_currentPage>0){
		xzfc_go2Page(xzfc_currentPage);
	}
}
function xzfc_goNext(){
	if(xzfc_currentPage<(xzfc_totalSize/xzfc_pageSize-1)){
		xzfc_go2Page(xzfc_currentPage+2);
	}
}
		
function xzfc_go2Page(id){
	xzfc_currentPage = id-1;
	var html="";
	for(var i = xzfc_currentPage*xzfc_pageSize;i<(xzfc_currentPage+1)*xzfc_pageSize&&i<xzfc_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+xzfcBaseUrl+"/student/stdstyle/detail.html?id="+xzfcList[i].id+"\" title="+xzfcList[i].title+" href=\"#\" title="+xzfcList[i].title+"><span class=\"new\">";
		
		var tempTitle=xzfcList[i].name+":";
		if(xzfcList[i].motto.length>xzfcTitleSize){
			tempTitle += tempTitlexzfcList[i].motto.substring(0,xzfcTitleSize)+"...";
		}else{
			tempTitle += xzfcList[i].motto;
		}
		html+=tempTitle+"</span><span class=\"date\">"+xzfcList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#xzfc").empty();
	$("#xzfc").append(html);
			
	//设置点中的样式
	for(var i=1;i<=xzfc_totalSize/xzfc_pageSize;i++){
		$("#xzfc_"+i).attr("class","");
	}
	$("#xzfc_"+id).attr("class","cur");
}
		