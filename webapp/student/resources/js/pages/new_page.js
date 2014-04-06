var new_totalSize;
var new_currentPage=0;
var new_pageSize = 15;
var newList;
var newTitleSize = 50;
var newBaseUrl ;
function new_init(_newList,baseUrl){
	newList	= eval('(' + _newList + ')');
	newBaseUrl = baseUrl;
	new_totalSize = newList.length;
	new_generatePage();
	new_go2Page(1);
}

function new_generatePage(){
	var pagehtml="<a href=\"javascript:new_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<new_totalSize/new_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=new_"+id+" href=\"javascript:new_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:new_goNext()\" class=\"next\">下一页</a>"
	$("#new_page").html(pagehtml)
}
		
		
function new_goPrev(){
	if(new_currentPage>0){
		new_go2Page(new_currentPage);
	}
}
function new_goNext(){
	if(new_currentPage<(new_totalSize/new_pageSize-1)){
		new_go2Page(new_currentPage+2);
	}
}
		
function new_go2Page(id){
	new_currentPage = id-1;
	var html="";
	for(var i = new_currentPage*new_pageSize;i<(new_currentPage+1)*new_pageSize&&i<new_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+newBaseUrl+"/student/new/detail.html?id="+newList[i].id+"\" title="+newList[i].title+" href=\"#\" title="+newList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(newList[i].title.length>newTitleSize){
			tempTitle = newList[i].title.substring(0,newTitleSize)+"...";
		}else{
			tempTitle = newList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+newList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#new").empty();
	$("#new").append(html);
			
	//设置点中的样式
	for(var i=1;i<=new_totalSize/new_pageSize;i++){
		$("#new_"+i).attr("class","");
	}
	$("#new_"+id).attr("class","cur");
}
		