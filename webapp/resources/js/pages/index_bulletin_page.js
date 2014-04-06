var b_totalSize;
var b_currentPage=0;
var b_pageSize = 25;
var bulletinList;
var bulletinBaseUrl;

var bulletin_title_size = 50;
function b_init(_bulletinList,baseUrl){
	bulletinList	= eval('(' + _bulletinList + ')');
	bulletinBaseUrl = baseUrl;
	b_totalSize = bulletinList.length;
	b_generatePage();
	b_go2Page(1);
}

function b_generatePage(){
	var pagehtml="<a href=\"javascript:b_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<b_totalSize/b_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=bulletin_"+id+" href=\"javascript:b_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:b_goNext()\" class=\"next\">下一页</a>"
	$("#bulletins_page").html(pagehtml)
}
		
		
function b_goPrev(){
	if(b_currentPage>0){
		b_go2Page(b_currentPage);
	}
}
function b_goNext(){
	if(b_currentPage<(b_totalSize/b_pageSize-1)){
		b_go2Page(b_currentPage+2);
	}
}
		
function b_go2Page(id){
	b_currentPage = id-1;
	var html="";
	for(var i = b_currentPage*b_pageSize;i<(b_currentPage+1)*b_pageSize&&i<b_totalSize;i++){
		html+="<a class=\"nw\" href=\""+bulletinBaseUrl+"/gjxy/bulletin/detail.html?id="+bulletinList[i].id+"\" title="+bulletinList[i].title+" href=\"#\" title="+bulletinList[i].title+">";
		var tempTitle;
		if(bulletinList[i].title.length>bulletin_title_size){
			tempTitle = bulletinList[i].title.substring(0,bulletin_title_size)+"...";
		}else{
			tempTitle = bulletinList[i].title;
		}
		html+=tempTitle+"<span class=\"nwd\">"+bulletinList[i].create_date+"</span>";;
		html+="</a>";
	}
	$("#bulletins").empty();
	$("#bulletins").append(html);
			
	//设置点中的样式
	for(var i=1;i<=b_totalSize/b_pageSize;i++){
		$("#bulletin_"+i).attr("class","");
	}
	$("#bulletin_"+id).attr("class","cur");
}
		