var dai_totalSize;
var dai_currentPage=0;
var dai_pageSize = 5;
var daiList;
var daiTitleSize = 50;
var daiBaseUrl ;
function dai_init(_daiList,baseUrl){
	daiList	= eval('(' + _daiList + ')');
	daiBaseUrl = baseUrl;
	dai_totalSize = daiList.length;
	dai_generatePage();
	dai_go2Page(1);
}

function dai_generatePage(){
	var pagehtml="<a href=\"javascript:dai_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<dai_totalSize/dai_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=dai_"+id+" href=\"javascript:dai_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:dai_goNext()\" class=\"next\">下一页</a>"
	$("#dai_page").html(pagehtml)
}
		
		
function dai_goPrev(){
	if(dai_currentPage>0){
		dai_go2Page(dai_currentPage);
	}
}
function dai_goNext(){
	if(dai_currentPage<(dai_totalSize/dai_pageSize-1)){
		dai_go2Page(dai_currentPage+2);
	}
}
		
function dai_go2Page(id){
	dai_currentPage = id-1;
	var html="";
	for(var i = dai_currentPage*dai_pageSize;i<(dai_currentPage+1)*dai_pageSize&&i<dai_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+daiBaseUrl+"/student/content/detail.html?id="+daiList[i].id+"\" title="+daiList[i].title+" href=\"#\" title="+daiList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(daiList[i].title.length>daiTitleSize){
			tempTitle = daiList[i].title.substring(0,daiTitleSize)+"...";
		}else{
			tempTitle = daiList[i].title;
		}
		html+=tempTitle+"</span>";
		html+="</a>";
	}
	$("#dai").empty();
	$("#dai").append(html);
			
	//设置点中的样式
	for(var i=1;i<=dai_totalSize/dai_pageSize;i++){
		$("#dai_"+i).attr("class","");
	}
	$("#dai_"+id).attr("class","cur");
}
		