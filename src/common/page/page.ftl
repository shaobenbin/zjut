<#macro pager formName="forms[0]">
   <script type="text/javascript">
     function sub(page){
	  document.getElementById("page.page").value = page;
 	  var pro = document.${formName};
	  
 	  pro.submit();
  }

  function go(){
	  var gopage = document.getElementById("page.page");
	  if(isNaN(gopage.value)){
		  gopage.value = "0";
		  return;
	  }
 	  var pro = document.${formName};
	 
 	  pro.submit();
  }
    </script>
    
    <tr>
	  <td align="right" class="padright10">
	  		共<span class="wordyel">${(page.total)!'0'}</span>条记录 &nbsp;<span class="wordyel">${(page.page)!'0'}/${(page.pagetotal)!'0'}</span>页 &nbsp;每页显示<span class="wordyel">${(page.pagesize)!'0'}</span>条
	  		<a href="#" onclick='sub(1)'>首页</a>
	  		<a href="#" onclick='sub(${(page.previouspage)!'0'})'>上一页</a>
	  		<a href="#" onclick='sub(${(page.nextpage)!'0'})'>下一页</a> 
	  		跳到第 <input name="page.page" size="3" type="text"> <input type="button" value='页' name='button3' onClick="go()" style="cursor:pointer;height:20px;background-color:#F3F3F3; color:#36567F; border:1 solid #36567F">
	  </td>
    </tr>
</#macro>