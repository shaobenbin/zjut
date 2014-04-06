var events_totalSize;
var events_currentPage=0;
var events_pageSize = 25;
var eventsList;
var newTitleSize = 50;
var newBaseUrl ;
function events_init(_eventsList,baseUrl){
	eventsList	= eval('(' + _eventsList + ')');
	newBaseUrl = baseUrl;
	events_totalSize = eventsList.length;
	events_generatePage();
	events_go2Page(1);
}

function events_generatePage(){
	var pagehtml="<a href=\"javascript:events_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<events_totalSize/events_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=events_"+id+" href=\"javascript:events_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:events_goNext()\" class=\"next\">下一页</a>"
	$("#events_page").html(pagehtml)
}
		
		
function events_goPrev(){
	if(events_currentPage>0){
		events_go2Page(events_currentPage);
	}
}
function events_goNext(){
	if(events_currentPage<(events_totalSize/events_pageSize-1)){
		events_go2Page(events_currentPage+2);
	}
}
		
function events_go2Page(id){
	events_currentPage = id-1;
	var html="";
	for(var i = events_currentPage*events_pageSize;i<(events_currentPage+1)*events_pageSize&&i<events_totalSize;i++){
		html+="<a class=\"fish\" href=\""+newBaseUrl+"/student/content/detail.html?id="+eventsList[i].id+"\" title="+eventsList[i].title+" href=\"#\" title="+eventsList[i].title+"><span class="new">";
		
		var tempTitle;
		if(eventsList[i].title.length>newTitleSize){
			tempTitle = eventsList[i].title.substring(0,newTitleSize)+"...";
		}else{
			tempTitle = eventsList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+eventsList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#events_page").empty();
	$("#events_page").append(html);
			
	//设置点中的样式
	for(var i=1;i<=events_totalSize/events_pageSize;i++){
		$("#events_"+i).attr("class","");
	}
	$("#events_"+id).attr("class","cur");
}
		