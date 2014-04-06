var employment_policy_totalSize;
var employment_policy_currentPage=0;
var employment_policy_pageSize = 15;
var employment_policyList;
var employment_policyTitleSize = 50;
var employment_policyBaseUrl ;
function employment_policy_init(_employment_policyList,baseUrl){
	employment_policyList	= eval('(' + _employment_policyList + ')');
	employment_policyBaseUrl = baseUrl;
	employment_policy_totalSize = employment_policyList.length;
	employment_policy_generatePage();
	employment_policy_go2Page(1);
}

function employment_policy_generatePage(){
	var pagehtml="<a href=\"javascript:employment_policy_goPrev()\" class=\"prev\">上一页</a>";
		for(var i=0;i<employment_policy_totalSize/employment_policy_pageSize;i++){
			var id = i+1;
			pagehtml+="<a id=employment_policy_"+id+" href=\"javascript:employment_policy_go2Page("+id+");\">"+id+"</a>"
	}
	pagehtml+="<a href=\"javascript:employment_policy_goNext()\" class=\"next\">下一页</a>"
	$("#employment_policy_page").html(pagehtml)
}
		
		
function employment_policy_goPrev(){
	if(employment_policy_currentPage>0){
		employment_policy_go2Page(employment_policy_currentPage);
	}
}
function employment_policy_goNext(){
	if(employment_policy_currentPage<(employment_policy_totalSize/employment_policy_pageSize-1)){
		employment_policy_go2Page(employment_policy_currentPage+2);
	}
}
		
function employment_policy_go2Page(id){
	employment_policy_currentPage = id-1;
	var html="";
	for(var i = employment_policy_currentPage*employment_policy_pageSize;i<(employment_policy_currentPage+1)*employment_policy_pageSize&&i<employment_policy_totalSize;i++){
		html+="<a class=\"fish\" target=\"_blank\" href=\""+employment_policyBaseUrl+"/student/content/detail.html?id="+employment_policyList[i].id+"\" title="+employment_policyList[i].title+" href=\"#\" title="+employment_policyList[i].title+"><span class=\"new\">";
		
		var tempTitle;
		if(employment_policyList[i].title.length>employment_policyTitleSize){
			tempTitle = employment_policyList[i].title.substring(0,employment_policyTitleSize)+"...";
		}else{
			tempTitle = employment_policyList[i].title;
		}
		html+=tempTitle+"</span><span class=\"date\">"+employment_policyList[i].create_date+"</span>";
		html+="</a>";
	}
	$("#employment_policy").empty();
	$("#employment_policy").append(html);
			
	//设置点中的样式
	for(var i=1;i<=employment_policy_totalSize/employment_policy_pageSize;i++){
		$("#employment_policy_"+i).attr("class","");
	}
	$("#employment_policy_"+id).attr("class","cur");
}
		