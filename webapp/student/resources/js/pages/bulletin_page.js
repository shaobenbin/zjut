var bulletin_totalSize;
var bulletin_currentPage=0;
var bulletin_pageSize = 15;
var bulletinList;
var bulletinTitleSize = 50;
var bulletinBaseUrl ;
function bulletin_init(_bulletinList,baseUrl){
	bulletinList	= eval('(' + _bulletinList + ')');
	bulletinBaseUrl = baseUrl;
	bulletin_totalSize = bulletinList.length;
	bulletin_generatePage();
	bulletin_go2Page(1);
}

function bulletin_generatePage(){
	var pagehtml="<a href=\"javascript:bulletin_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<bulletin_totalSize/bulletin_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=bulletin_"+id+" href=\"javascript:bulletin_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:bulletin_goNext()\" class=\"next\">下一页</a>"
	$("#bulletin_page").html(pagehtml)
}
		
		
function bulletin_goPrev(){
	if(bulletin_currentPage>0){
		bulletin_go2Page(bulletin_currentPage);
	}
}
function bulletin_goNext(){
	if(bulletin_currentPage<(bulletin_totalSize/bulletin_pageSize-1)){
		bulletin_go2Page(bulletin_currentPage+2);
	}
}
		
function bulletin_go2Page(id){
	bulletin_currentPage = id-1;
	var html="";
	for(var i = bulletin_currentPage*bulletin_pageSize;i<(bulletin_currentPage+1)*bulletin_pageSize&&i<bulletin_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+bulletinBaseUrl+"/student/bulletin/detail.html?id="+bulletinList[i].id+"\" title="+bulletinList[i].title+" href=\"#\" title="+bulletinList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(bulletinList[i].title.length>bulletinTitleSize){
			tempTitle = bulletinList[i].title.substring(0,bulletinTitleSize)+"...";
		}else{
			tempTitle = bulletinList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+bulletinList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#bulletin").empty();
	$("#bulletin").append(html);
			
	//设置点中的样式
	for(var i=1;i<=bulletin_totalSize/bulletin_pageSize;i++){
		$("#bulletin_"+i).attr("class","");
	}
	$("#bulletin_"+id).attr("class","cur");
}
		