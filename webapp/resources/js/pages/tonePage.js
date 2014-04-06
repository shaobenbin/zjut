/**
 * 数据分页
 */

//取数据的开始索引
var start=0;
//分页数据的每页展示数量
var step=10;
//每行展示的数据量
var cellStep=2;
//列表
var objectlist; 

//外围容器
var divname;

var tablename;

//初始化设置
function init_ben_assort(_start,_step,_cellStep){
	start = _start;
	step = _step;
	cellStep = _cellStep;
}

/**
 * 开始分页 
 * 
 */
function startpage_ben_assrot(_objectlist,_tableObj){
	//alert(_objectlist);
	tablename = _tableObj;
	objectlist	= eval('(' + _objectlist + ')');
	//deleteDivContent(_tableObj);
	var end;
	if(start+step>=objectlist.length)end = objectlist.length;
	else end = start+step;
	//var tableObj = createTableObject_assort();
	var tableObj = document.getElementById(_tableObj);
	//createTableHead(tableObj);
	clearSearchTable(tableObj);
	createTableBodytTd(tableObj,start,end);
	//document.getElementById(divname).appendChild(tableObj);
	createPageTr(tableObj,start,end);
}

function insertAssortTable(startIndex,endIndex){
	
	//deleteDivContent(divname);
	//var tableObj = createTableObject_assort();
	var tableObj = document.getElementById(tablename);
	//createTableHead(tableObj);
	clearSearchTable(tableObj);
	createTableBodytTd(tableObj,startIndex,endIndex);
	//document.getElementById(divname).appendChild(tableObj);
	createPageTr(tableObj,startIndex,endIndex);
}

/**
 * 根据分类彩铃的展示要求创建一个table对象返回
 */
function createTableObject_assort(){
	var tableObject = document.createElement("table");
	tableObject.width = "100%";
	tableObject.border = "0";
	tableObject.cellspacing = "0";
	tableObject.cellpadding = "0";
	return tableObject;
}

/**
 * 删除指定div的内容
 */
function deleteDivContent(divname){
	document.getElementById(divname).innerHTML = "";
}

/**
 * 删除指定table的obj
 */
function clearSearchTable(obj){
	//alert(obj.rows.length);
	for(var i =obj.rows.length;i>1;i--){
		obj.deleteRow(i-1);
	}
}

/**
 * 创建表的头部格式
 */
function createTableHead(tableObj){
	var tr = tableObj.insertRow(tableObj.rows.length);
	for(var i=0;i<cellStep;i++){		
		var td1 = tr.insertCell(4*i);
		var td2 = tr.insertCell(4*i+1);		
		var td3 = tr.insertCell(4*i+2);		
		var td4 = tr.insertCell(4*i+3)		
		tdHeadStyle_assrot(td1,td2,td3,td4);
	}
}

/**
 * 根据分类彩铃的要求设置头部的样式
 */
function tdHeadStyle_assrot(td1,td2,td3,td4){
	td1.innerHTML = "歌曲名称";
	td1.width = "36%";
	td1.scope="col";
	td2.innerHTML = "歌手";
	td2.width = "26%";
	td2.scope="col";
	td3.innerHTML = "试听";
	td3.width = "16%";
	td3.scope="col";
	td4.innerHTML = "定制";
	td4.width = "28%";
	td4.scope="col";
}

/**
 * 创建分页主体
 */
function createTableBodytTd(tableObj,_start,_end){
	//var img1 = baseUrl+"/images/pic/chailing_yiny.gif";
 	//var img2 = baseUrl+"/images/pic/chailing_save.gif";
 	var img1 = baseUrl+"/images/pic/chialing_ting.gif";
 	var img2 = baseUrl+"/images/pic/chailing_phoe.gif";
 	
	if(_start>=objectlist.length)return;
	if(_end>objectlist.length)return;
	if(_start>=_end)return;
	var trindex = 1;
	for(var i=_start;i<_end;i++){
		var tr=tableObj.insertRow(tableObj.rows.length);
		if(trindex%2 == 1){
			tr.className="alt";
		}
		for(var j = 0;j < cellStep;j++){
			
			var td0 = tr.insertCell(4*j);
			td0.innerHTML = "&nbsp;";
			
			var td1 = tr.insertCell(4*j+1);
			td1.title = objectlist[i].tonename;
			
			if(objectlist[i].tonename.length>8){
				td1.innerHTML = (i+1)+"."+objectlist[i].tonename.substring(0,7)+"..";
			}else{
				td1.innerHTML = (i+1)+"."+objectlist[i].tonename;
			}
			
			var td2 = tr.insertCell(4*j+2);
			td2.title=objectlist[i].singer;
			if(objectlist[i].singer.length>7){
				td2.innerHTML = objectlist[i].singer.substring(0,6)+"..";
			}else{
				td2.innerHTML = objectlist[i].singer;
			}
			
			var td3 = tr.insertCell(4*j+3);
			td3.innerHTML = objectlist[i].tonevalidday;
			
			var td4 = tr.insertCell(4*j+4);
			td4.innerHTML = objectlist[i].price;
			
			var td5 = tr.insertCell(4*j+5);
			td5.innerHTML = "<a  href='javascript:getToneDetailSub(\""+objectlist[i].tonecode+"\",\""+objectlist[i].singer+"\",\""+objectlist[i].tonename+"\",\""+objectlist[i].toneinfo+"\",\""+objectlist[i].price+"\",\""+objectlist[i].tonevalidday+"\",\""+objectlist[i].updatetime+"\",\""+objectlist[i].downloadtimes+"\",\""+objectlist[i].prelistenurl+"\",\"\",\"false\")'><img src='"+img1+"' alt=\"试听\" /></a>";
			var td6 = tr.insertCell(4*j+6);
			td6.innerHTML = "&nbsp";
			var td7 = tr.insertCell(4*j+7);
			td7.colSpan="2";
			td7.innerHTML = "<a  href='javascript:getToneDetailSub(\""+objectlist[i].tonecode+"\",\""+objectlist[i].singer+"\",\""+objectlist[i].tonename+"\",\""+objectlist[i].toneinfo+"\",\""+objectlist[i].price+"\",\""+objectlist[i].tonevalidday+"\",\""+objectlist[i].updatetime+"\",\""+objectlist[i].downloadtimes+"\",\""+objectlist[i].prelistenurl+"\",\"\",\"true\")'><img src='"+img2+"' alt=\"定制\" /></a>";
			
			i = i+j;
			
			if(i>=_start+step)break;
		}
		if(i>=_start+step)break;
		trindex++;
	}
}

function createPageTr(tableObj,starIndex,endIndex){
	var tr = tableObj.insertRow(tableObj.rows.length);
	td = tr.insertCell(0);
 	//td.colSpan = 8*cellStep+'';
	td.colSpan = '8';
	var totalPage = Math.ceil(objectlist.length/step);
 	var currentPage = Math.ceil(starIndex/step);
 	td.innerHTML = "<table id='assrotCurrentIndexTable' width='100%'></table>";
 	var tableObj = document.getElementById("assrotCurrentIndexTable");
 	var tr = tableObj.insertRow(0);
 	var td1 = tr.insertCell(0);
 	td1.width="10%";
 	td1.align="center";
 	if(starIndex>=step){
 		td1.innerHTML = "<a href='javascript:insertAssortTable(0,"+step+")'>返回最前</a>";
 	}else{
 		td1.innerHTML = "<a>返回最前</a>";
 	}
 	var td2 = tr.insertCell(1);
 	td2.width="10%";
 	td2.align="center";
 	if(starIndex>=step){
 		td2.innerHTML = "<a href='javascript:insertAssortTable("+(starIndex-step)+","+starIndex+")'>上一页</a>";
 	}else{
 		td2.innerHTML = "<a>上一页</a>";
 	}
 	var td3 = tr.insertCell(2);
 	td3.width="15%";
 	td3.align="center";
 	td3.innerHTML = "Page <input type='text' size='1' id='currentIndex' value='"+(currentPage+1)+"' >of&nbsp;"+totalPage;
 	var td4 = tr.insertCell(3);
 	td4.width="10%";
 	td4.align="center";
 	if(endIndex>=objectlist.length){
 		td4.innerHTML = "<a>下一页<a>";
 	}else{
 		if((endIndex+step)>objectlist.length){
 			td4.innerHTML = "<a href='javascript:insertAssortTable("+endIndex+","+objectlist.length+")'>下一页<a>";
 		}else{
 			td4.innerHTML = "<a href='javascript:insertAssortTable("+endIndex+","+(endIndex+step)+")'>下一页<a>";
 		}
 	}
 	var td5 = tr.insertCell(4);
 	td5.width="10%";
 	td5.align="center";
 	if(endIndex>=objectlist.length){
 		td5.innerHTML = "<a>最后一页</a>";	
 	}else{
 		td5.innerHTML = "<a href='javascript:insertAssortTable("+step*(totalPage-1)+","+objectlist.length+")'>最后一页</a>";	
 	}
 	var td6 = tr.insertCell(5);
 	td6.align='right';
 	td6.width="45%";
 	td6.innerHTML = "显示第"+starIndex+"到第"+endIndex+"记录，共"+objectlist.length+"条记录";
}


 
 