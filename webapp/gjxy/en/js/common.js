//焦点图片切换JS代码 && placeholder属性兼容代码
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
	$('.tab_t li').on('click',function(){
		var index = $(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$(this).parent().siblings('.tab_c:eq('+index+')').addClass('active').siblings().removeClass('active');
	});

	//焦点图片切换

	$('.theGallery').sySlider();

	//以下是placeholder属性兼容代码
	// -- Constants --
	var PLACE_HOLDER_COLOR = "rgb(169,169,169)"; // "darkGrey" does not work in IE6
	var PLACE_HOLDER_DATA_NAME = "original-font-color";
	
	// -- Util Methods --	
	var getContent = function(element){
		return $(element).val();		
	}

	var setContent = function(element, content){
		$(element).val(content);		
	}
	
	var getPlaceholder = function(element){
		return $(element).attr("placeholder");
	}
	
	var isContentEmpty = function(element){
		var content = getContent(element);
		return (content.length === 0) || content == getPlaceholder(element);
	}
		
	var setPlaceholderStyle = function(element){
		$(element).data(PLACE_HOLDER_DATA_NAME, $(element).css("color"));
		$(element).css("color", PLACE_HOLDER_COLOR);		
	}
	
	var clearPlaceholderStyle = function(element){
		$(element).css("color", $(element).data(PLACE_HOLDER_DATA_NAME));		
		$(element).removeData(PLACE_HOLDER_DATA_NAME);
	}
	
	var showPlaceholder = function(element){
		setContent(element, getPlaceholder(element));
		setPlaceholderStyle(element);	
	}
	
	var hidePlaceholder = function(element){
		if($(element).data(PLACE_HOLDER_DATA_NAME)){
			setContent(element, "");
			clearPlaceholderStyle(element);
		}
	}
	
	// -- Event Handlers --
	var inputFocused = function(){
		if(isContentEmpty(this)){
			hidePlaceholder(this);		
		}
	}
	
	var inputBlurred = function(){
		if(isContentEmpty(this)){
			showPlaceholder(this);
		}
	}
	
	var parentFormSubmitted = function(){
		if(isContentEmpty(this)){
			hidePlaceholder(this);		
		}	
	}
	
	// -- Bind event to components --
	$("textarea, input[type='text']").each(function(index, element){
		if($(element).attr("placeholder")){
			$(element).focus(inputFocused);
			$(element).blur(inputBlurred);
			$(element).bind("parentformsubmitted", parentFormSubmitted);
			
			// triggers show place holder on page load
			$(element).trigger("blur");
			// triggers form submitted event on parent form submit
			$(element).parents("form").submit(function(){
				$(element).trigger("parentformsubmitted");
			});
		}
	});
});

