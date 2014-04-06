/**
 * 表格分页工具
 * ben.s   
 * shaobenbin@gmail.com
 * 2013.6.21
 * 创建一个对象 
 * var obj = new pagging('每一页的显示行数，不包括标题','表格id编号');
 * 显示第一页:obj.first()
 * 显示上一页:obj.prev()
 * 显示下一页:obj.next()
 * 显示最后一页:obj.last()
 * 显示指定页数:obj.show(页数)
 * 其他功能请联系作者补充.
**/
var pagging = function(){
	this.init.apply(this, arguments);
}

pagging.prototype={  
	init:function(s){
		if(s.pageSize){
			this.pageSize = s.pageSize;
		}else{
			this.pageSize = 10;
		}
		
		if(s.divId){
			this.divId = s.divId;
		}
		this.currentPage = 0;
		this.size = $('#'+this.divId).find('a').length;
	},
	show:function(pageSeq){
		this.currentPage = pageSeq;
		var beginSeq = this.pageSize*this.currentPage;
		var endSeq = this.pageSize*(this.currentPage) >=this.size ? (this.size):(this.pageSize*(this.currentPage+1)-1);
		$('#'+this.divId).find('a').hide();
		for(var i=beginSeq;i<=endSeq;i++){
			$('#'+this.divId).find('a').eq(i).show();
		}
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
		this.show((($('#'+this.divId).find('a').length-1)/this.pageSize)-1);
	}
}