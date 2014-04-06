(function($) {
	$.fn.sySlider = function() {
		$(this).each(function(){
			var $this = $(this);
			var html = '<span class="prev"></span><span class="next"></span><ul>';
			var width = $this.width(),
				height = $this.height(),
				n = $this.find('a').length,index,$next,autoSlider
				$pic = $this.find('a');
			$pic.eq(0).css('display','inline-block');
			for (var i=0; i<n; i=i+1) {
				if (i===0) {
					html += '<li class="active"></li>';
				} else {
					html += '<li></li>';
				}
			}
			html += '</ul>';
			$this.append(html);
			var $span = $this.find('span');
			$span.css('top',((height/2) - ($span.height()/2)) + 'px');
			$this.hover(function(){
				$span.css('display','inline-block');
			},function(){
				$span.css('display','none');
			});

			//点击翻页按钮时 
			$span.on('click',function(){
				clearInterval(autoSlider);
				var cls = $(this).attr('class'),
					$cur = $this.find('a:visible');
				if (!$cur.is(':animated')) {
					if (cls === 'prev') {
						if ($cur.index() === 0) {
							index = n-1;
						} else {
							index = $cur.index() - 1;
						}
						$next = $this.find('a:eq('+index+')');
						$next.css({'left':-width +'px','display':'inline-block'}).animate({'left':0},400);
						$cur.animate({'left':width +'px'},400).hide(10);				
					};
					if (cls === 'next') {
						if ($cur.index() === n-1) {
							index = 0;
						} else {
							index = $cur.index() + 1;
						}
						$next = $this.find('a:eq('+index+')');
						$next.css({'left':width +'px','display':'inline-block'}).animate({'left':0},400);
						$cur.animate({'left':-width +'px'},400).hide(10);	
					};
					$this.find('li:eq('+index+')').addClass('active')
						 .siblings().removeClass('active');
				};
				autoSlider = setInterval(auto,3000);
				return false;
			});

			//点击页标按钮时
			$this.find('li').on('click',function(){
				clearInterval(autoSlider);
				var $cur = $this.find('a:visible'),
					index = $(this).index();
				if (!$cur.is(':animated') && $cur.index() !== index){
					$cur.fadeOut(400);
					$this.find('a:eq('+index+')').css('left',0).fadeIn(400);
					$(this).addClass('active').siblings().removeClass('active');
				}
				autoSlider = setInterval(auto,3000);
				return　false;
			});
			function auto(){
				var i,
						$cur = $this.find('a:visible'),
						ind = $cur.index();
					if (ind === n-1) {
						i = 0;
					} else {
						i = ind+1;
					}
				if (!$cur.is(':animated')){
					$cur.fadeOut(400);
					$this.find('a:eq('+i+')').css('left',0).fadeIn(400);
					$this.find('li:eq('+i+')').addClass('active').siblings().removeClass('active');
				}
			}
			//无点击时自动播放
			autoSlider = setInterval(auto,3000);
		});
	}
})(jQuery);
$(function(){
	//去除点击连接后，链接周围的虚线框
	$('.leftNav a').focus(function(){
		$(this).blur();
	});

	//导航
	$('.leftNav li').on('click',function(){
		var index = $(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$(this).parent().next().find('.tabBody:eq('+index+')').addClass('active').siblings().removeClass('active');
		return false;
	});
	
	//选项卡
	//ben.s 2013.4.1修改
	$('.tab_t li').on('click',function(){
		var index = $(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$(this).parent().siblings('.tab_c').removeClass('active');
		//$(this).parent().siblings('.tab_c:eq('+index+')').addClass('active').siblings().removeClass('active');
		$(this).parent().siblings('.tab_c:eq('+index+')').addClass('active')
	});

	//焦点图片切换

	$('.theGallery').sySlider();
});
