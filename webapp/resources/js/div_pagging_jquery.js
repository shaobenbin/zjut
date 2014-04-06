/**
 * 表格分页工具
 * ben.s   
 * shaobenbin@gmail.com
 * 2013.6.21
 * 创建一个对象 
 * var obj = new div_pagging('每一页的显示行数，不包括标题','表格id编号');
 * 显示第一页:obj.first()
 * 显示上一页:obj.prev()
 * 显示下一页:obj.next()
 * 显示最后一页:obj.last()
 * 显示指定页数:obj.show(页数)
 * 其他功能请联系作者补充.
**/
var div_pagging = function(){
	this.init.apply(this, arguments);
}

div_pagging.prototype={  
	init:function(s){
		if(s.pageSize){
			this.pageSize = s.pageSize;
		}else{
			this.pageSize = 10;
		}
		
		if(s.divId){
			this.divId = s.divId;
		}
		if(s.currentPageId){
			this.currentPageId	= s.currentPageId;
		}
		this.currentPage = 0;
	},
	
	show:function(pageSeq){
		if(pageSeq<0)pageSeq = 0;
		if(pageSeq>=this.totalPage)pageSeq = this.totalPage-1;
		this.currentPage = pageSeq;
		size = $('#'+this.divId).find('a').length;
		var beginSeq = this.pageSize*this.currentPage;
		var endSeq = this.pageSize*(this.currentPage+1) >=size ? (size-1):(this.pageSize*(this.currentPage+1)-1);
		$('#'+this.divId).find('a').hide();
	
		for(var i=beginSeq;i<=endSeq;i++){
			$('#'+this.divId).find('a').eq(i).show();
		}
		
		if(this.currentPageId){
			$('#'+this.currentPageId).val(this.currentPage+1);
		}
		
		this.totalPage = Math.ceil(($('#'+this.divId).find('a').length)/this.pageSize);
		

	},
	
	prev:function(){
		if(this.currentPage>0){
    		this.show(this.currentPage-1);
   	 	}
	},
	next:function(){
		if(this.currentPage<((($('#'+this.divId).find('a').length)/this.pageSize)-1)){
			this.show(this.currentPage+1);
		}
	},

    first:function(){
		this.show(0);
	},

	last:function(){
		this.show(this.totalPage-1);
	},
	getTotalPage:function(){
		return this.totalPage;
	},
	getCurrentPage:function(){
		return this.currentPage+1;
	}

}