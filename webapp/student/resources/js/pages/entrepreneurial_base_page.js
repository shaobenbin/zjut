var entrepreneurial_base_totalSize;
var entrepreneurial_base_currentPage=0;
var entrepreneurial_base_pageSize = 15;
var entrepreneurial_baseList;
var entrepreneurial_baseTitleSize = 50;
var entrepreneurial_baseBaseUrl ;
function entrepreneurial_base_init(_entrepreneurial_baseList,baseUrl){
	entrepreneurial_baseList	= eval('(' + _entrepreneurial_baseList + ')');
	entrepreneurial_baseBaseUrl = baseUrl;
	entrepreneurial_base_totalSize = entrepreneurial_baseList.length;
	entrepreneurial_base_generatePage();
	entrepreneurial_base_go2Page(1);
}

function entrepreneurial_base_generatePage(){
	var pagehtml="<a href=\"javascript:entrepreneurial_base_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<entrepreneurial_base_totalSize/entrepreneurial_base_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=entrepreneurial_base_"+id+" href=\"javascript:entrepreneurial_base_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:entrepreneurial_base_goNext()\" class=\"next\">下一页</a>"
	$("#entrepreneurial_base_page").html(pagehtml)
}
		
		
function entrepreneurial_base_goPrev(){
	if(entrepreneurial_base_currentPage>0){
		entrepreneurial_base_go2Page(entrepreneurial_base_currentPage);
	}
}
function entrepreneurial_base_goNext(){
	if(entrepreneurial_base_currentPage<(entrepreneurial_base_totalSize/entrepreneurial_base_pageSize-1)){
		entrepreneurial_base_go2Page(entrepreneurial_base_currentPage+2);
	}
}
		
function entrepreneurial_base_go2Page(id){
	entrepreneurial_base_currentPage = id-1;
	var html="";
	for(var i = entrepreneurial_base_currentPage*entrepreneurial_base_pageSize;i<(entrepreneurial_base_currentPage+1)*entrepreneurial_base_pageSize&&i<entrepreneurial_base_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+entrepreneurial_baseBaseUrl+"/student/content/detail.html?id="+entrepreneurial_baseList[i].id+"\" title="+entrepreneurial_baseList[i].title+" href=\"#\" title="+entrepreneurial_baseList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(entrepreneurial_baseList[i].title.length>entrepreneurial_baseTitleSize){
			tempTitle = entrepreneurial_baseList[i].title.substring(0,entrepreneurial_baseTitleSize)+"...";
		}else{
			tempTitle = entrepreneurial_baseList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+entrepreneurial_baseList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#entrepreneurial_base").empty();
	$("#entrepreneurial_base").append(html);
			
	//设置点中的样式
	for(var i=1;i<=entrepreneurial_base_totalSize/entrepreneurial_base_pageSize;i++){
		$("#entrepreneurial_base_"+i).attr("class","");
	}
	$("#entrepreneurial_base_"+id).attr("class","cur");
}
		