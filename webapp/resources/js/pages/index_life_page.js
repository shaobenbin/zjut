var life_totalSize;
var life_currentPage=0;
var life_pageSize = 25;
var lifeList;
var newBaseUrl;
function life_init(_lifeList,_baseUrl){
	lifeList	= eval('(' + _lifeList + ')');
	life_totalSize = lifeList.length;
	life_generatePage();
	life_go2Page(1);
	newBaseUrl = _baseUrl;
}

function life_generatePage(){
	var pagehtml="<a href=\"javascript:life_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<life_totalSize/life_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=lifes_"+id+" href=\"javascript:life_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:life_goNext()\" class=\"next\">下一页</a>"
	$("#lifes_page").html(pagehtml)
}
		
		
function life_goPrev(){
	if(life_currentPage>0){
		life_go2Page(life_currentPage);
	}
}
function life_goNext(){
	if(life_currentPage<(life_totalSize/life_pageSize-1)){
		life_go2Page(life_currentPage+2);
	}
}
		
function life_go2Page(id){
	life_currentPage = id-1;
	var html="";
	for(var i = life_currentPage*life_pageSize;i<(life_currentPage+1)*life_pageSize&&i<life_totalSize;i++){
		url = newBaseUrl+"/gjxy/life/detail.html?id="+lifeList[i].id;
		html+="<div class=\"life\" style=\"cursor:pointer\" onclick=\"javascript:location.href='"+url+"'\"><div class=\"pic\">";
		html+="<img src="+lifeList[i].image+" alt=\"###\" title=\"##\" /></div>";
		html+="<h3>"+lifeList[i].title+"</h3>"
		html+="<h4>"+lifeList[i].summary+"</h4>"
		
		html+="<span class=\"lifed\">"+lifeList[i].create_date+"</span>"
		html+="</div>";
	}
	
	$("#lifes").empty();
	$("#lifes").append(html);
			
	//设置点中的样式
	for(var i=1;i<=life_totalSize/life_pageSize;i++){
		$("#lifes_"+i).attr("class","");
	}
	$("#lifes_"+id).attr("class","cur");
}
		