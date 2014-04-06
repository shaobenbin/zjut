var num=8 //该值记录标签的个数
function chgChannelBg(oChannel,curChannel,sStyle1,sStyle2)
{
	 if(curChannel.className=sStyle2);
	 if(oChannel.className!=sStyle1)
	 {
		 oChannel.className=sStyle1;
	 }
	
}
//获取对应ID或Name的元素对象
function GetObj(objName){
 if(document.getElementById){
  return eval('document.getElementById("' + objName + '")');
 }else if(document.layers){
  return eval("document.layers['" + objName +"']");
 }else{
  return eval('document.all.' + objName);
 }
}
//index:当前控制元素序号；ctrledPrefix:被控制ID前缀；ctrlPrefix:控制ID前缀；ctrlClass1:控制元素初始样式；ctrlClass2:控制元素第二样式
function tabSwift(index,ctrlPrefix,ctrledPrefix,ctrlClass1,ctrlClass2)
{

   for(var i=1;i<=num;i++){/* 最多支持8个标签 */
   
    if(GetObj(ctrlPrefix+i)&&GetObj(ctrledPrefix+i)){
	
     GetObj(ctrledPrefix+i).style.display = 'none';
	
	 if (ctrlClass1!=null)
	 {
        GetObj(ctrlPrefix+i).className =ctrlClass1;
	 }
    
    }
   }
  if(GetObj(ctrlPrefix+index)&&GetObj(ctrledPrefix+index)){
    GetObj(ctrledPrefix+index).style.display = 'block';
	if (ctrlClass2!=null)
	{
         GetObj(ctrlPrefix+index).className=ctrlClass2;
	}
   
 }
}
function showDesc(obj,desc,show){
	//var xPos=event.x;
	var id=obj.id;
	var index=id.substring(id.indexOf("nbpt")+"nbpt".length+1);
	var oDiv=document.getElementById("desc_"+index);
	if(desc!=""){
	oDiv.innerHTML=desc;}
	oDiv.style.display=show;

}
function chgMainNr(src){
  document.getElementById("mainnr").src=src;
}
function swiftSubMenu(index,ctrlPrefix,ctrlClass1,ctrlClass2){

  for(var i=1;i<=num;i++){/* 最多支持8个标签 */
    if(GetObj(ctrlPrefix+i)){
	  if (ctrlClass1!=null)
	  {
        GetObj(ctrlPrefix+i).className =ctrlClass1;
	  }
    }
   }
  if(GetObj(ctrlPrefix+index)){
	if (ctrlClass2!=null)
	{
         GetObj(ctrlPrefix+index).className=ctrlClass2;
	}
   
   }
 }




//框架左右收缩
function MouseDownToResize(obj){  
 obj.mouseDownX=event.clientX;
 obj.pareneTdW=obj.parentElement.offsetWidth;
 obj.pareneTableW=tab.offsetWidth;
 obj.setCapture();
}
function MouseMoveToResize(obj){  
 if(!obj.mouseDownX) return false;
 var newWidth=obj.pareneTdW*1+event.clientX*1-obj.mouseDownX;
 if(newWidth>0)
 {
   obj.parentElement.style.width = newWidth;
   //tab.style.width=obj.pareneTableW*1+event.clientX*1-obj.mouseDownX;
   tab.style.width="100%";
   var obj = eval("mydiv0");    
   obj.style.left = obj.parentElement.offsetLeft*1+obj.parentElement.offsetWidth*1+tab.offsetLeft;//obj.parentElement.style.pos;  

 }
}
function MouseUpToResize(obj){
  obj.releaseCapture();
  obj.mouseDownX=0;
} 

function init(){  
	var tempStr = "";
	tempStr = '<div id="mydiv0" class="resizeDivClass" onmousedown="MouseDownToResize(this);" onmousemove="MouseMoveToResize(this);" onmouseup="MouseUpToResize(this);"></div>';      
	tab.rows(0).cells(0).innerHTML = tab.rows(0).cells(0).innerHTML+tempStr;     
	var obj = eval("mydiv0");    
	obj.style.left = obj.parentElement.style.pos;   

  
}




//弹出窗口
function pupopen(){
         document.getElementById("bg2").style.display="block";
            document.getElementById("popbox2").style.display="block" ; 
 }
 
 function pupclose(){
 
        document.getElementById("bg2").style.display="none";
            document.getElementById("popbox2").style.display="none" ; 
 }


//弹出窗口的取消
function closeWin(divId,divId2){
	   var oDiv=document.getElementById(divId);
	   if(oDiv!=null){
	     oDiv.style.display='none';
	   }
 oDiv=document.getElementById(divId2);
	   if(oDiv!=null){
	     oDiv.style.display='none';
	   }

	}

//弹出层
  var flag=0;
  var div;
  function Point(iX, iY){
  this.x = iX;
  this.y = iY;
}
function getPosition(aTag,obj){
  var oTmp = aTag;

  var pt = new Point(0,0);

  do {
    pt.x += oTmp.offsetLeft;
    pt.y += oTmp.offsetTop;
    oTmp = oTmp.offsetParent;
  } while(oTmp.tagName!="BODY");
   pt.y += aTag.offsetHeight + 1;
  if((pt.x - document.body.scrollLeft) + obj.style.pixelWidth >= document.body.clientWidth){ //超出宽度了
  	pt.x -= ((pt.x - document.body.scrollLeft) + obj.style.pixelWidth - document.body.clientWidth); //减去超出部分
  }
  return pt;
}
function setPosition(btn,div)
{
 
  var p=getPosition(btn,div);
  div.style.left=p.x;
  div.style.top=p.y;
  
}

function showhide(which){
div=which;
 if( which.style.display=="none"){
  which.style.display=""
 }else{
   setTimeout("hideDiv()",1000);   
   //which.style.display="none";
 }
}
function hideDiv(){
 if(div!=null && flag==0){
   div.style.display="none";
 }
  
}


