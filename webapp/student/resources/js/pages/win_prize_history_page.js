var win_prize_history_totalSize;
var win_prize_history_currentPage=0;
var win_prize_history_pageSize = 15;
var win_prize_historyList;
var win_prize_historyTitleSize = 50;
var win_prize_historyBaseUrl ;
function win_prize_history_init(_win_prize_historyList,baseUrl){
	win_prize_historyList	= eval('(' + _win_prize_historyList + ')');
	win_prize_historyBaseUrl = baseUrl;
	win_prize_history_totalSize = win_prize_historyList.length;
	win_prize_history_generatePage();
	win_prize_history_go2Page(1);
}

function win_prize_history_generatePage(){
	var pagehtml="<a href=\"javascript:win_prize_history_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<win_prize_history_totalSize/win_prize_history_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=win_prize_history_"+id+" href=\"javascript:win_prize_history_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:win_prize_history_goNext()\" class=\"next\">下一页</a>"
	$("#win_prize_history_page").html(pagehtml)
}
		
		
function win_prize_history_goPrev(){
	if(win_prize_history_currentPage>0){
		win_prize_history_go2Page(win_prize_history_currentPage);
	}
}
function win_prize_history_goNext(){
	if(win_prize_history_currentPage<(win_prize_history_totalSize/win_prize_history_pageSize-1)){
		win_prize_history_go2Page(win_prize_history_currentPage+2);
	}
}
		
function win_prize_history_go2Page(id){
	win_prize_history_currentPage = id-1;
	var html="";
	for(var i = win_prize_history_currentPage*win_prize_history_pageSize;i<(win_prize_history_currentPage+1)*win_prize_history_pageSize&&i<win_prize_history_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+win_prize_historyBaseUrl+"/student/content/detail.html?id="+win_prize_historyList[i].id+"\" title="+win_prize_historyList[i].title+" href=\"#\" title="+win_prize_historyList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(win_prize_historyList[i].title.length>win_prize_historyTitleSize){
			tempTitle = win_prize_historyList[i].title.substring(0,win_prize_historyTitleSize)+"...";
		}else{
			tempTitle = win_prize_historyList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+win_prize_historyList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#win_prize_history").empty();
	$("#win_prize_history").append(html);
			
	//设置点中的样式
	for(var i=1;i<=win_prize_history_totalSize/win_prize_history_pageSize;i++){
		$("#win_prize_history_"+i).attr("class","");
	}
	$("#win_prize_history_"+id).attr("class","cur");
}
		