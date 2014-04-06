var qin_totalSize;
var qin_currentPage=0;
var qin_pageSize = 5;
var qinList;
var qinTitleSize = 50;
var qinBaseUrl ;
function qin_init(_qinList,baseUrl){
	qinList	= eval('(' + _qinList + ')');
	qinBaseUrl = baseUrl;
	qin_totalSize = qinList.length;
	qin_generatePage();
	qin_go2Page(1);
}

function qin_generatePage(){
	var pagehtml="<a href=\"javascript:qin_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<qin_totalSize/qin_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=qin_"+id+" href=\"javascript:qin_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:qin_goNext()\" class=\"next\">下一页</a>"
	$("#qin_page").html(pagehtml)
}
		
		
function qin_goPrev(){
	if(qin_currentPage>0){
		qin_go2Page(qin_currentPage);
	}
}
function qin_goNext(){
	if(qin_currentPage<(qin_totalSize/qin_pageSize-1)){
		qin_go2Page(qin_currentPage+2);
	}
}
		
function qin_go2Page(id){
	qin_currentPage = id-1;
	var html="";
	for(var i = qin_currentPage*qin_pageSize;i<(qin_currentPage+1)*qin_pageSize&&i<qin_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+qinBaseUrl+"/student/content/detail.html?id="+qinList[i].id+"\" title="+qinList[i].title+" href=\"#\" title="+qinList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(qinList[i].title.length>qinTitleSize){
			tempTitle = qinList[i].title.substring(0,qinTitleSize)+"...";
		}else{
			tempTitle = qinList[i].title;
		}
		html+=tempTitle+"</span>";
		html+="</a>";
	}
	$("#qin").empty();
	$("#qin").append(html);
			
	//设置点中的样式
	for(var i=1;i<=qin_totalSize/qin_pageSize;i++){
		$("#qin_"+i).attr("class","");
	}
	$("#qin_"+id).attr("class","cur");
}
		