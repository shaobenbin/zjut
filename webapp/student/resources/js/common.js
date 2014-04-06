$(function(){
	//二级导航
	$('.header .nav li div').hover(function(){
		$(this).css('height',($(this).find('a').length * 25) +'px');
	},function(){
		$(this).css('height','25px');
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

	$('.gallery_js .prev').on('click',function(){
		var $this = $(this);
		var $hid = $this.siblings('.hid');
		var li_n = $hid.find('li').length;
		var li_w = 79;
		var ul_w = li_w * li_n;
		var $ul = $hid.find('ul');
		var mag = parseInt($ul.css('margin-left'),10);
		$ul.css('width',ul_w);
		if(!$ul.is(':animated')){
			if (mag <= li_w*3 - ul_w) {
				$ul.animate({'margin-left':'0'},400);
			} else {
				$ul.animate({'margin-left':(mag - li_w) + 'px'},400);
			}
		}
			

	});
	$('.gallery_js .next').on('click',function(){
		var $this = $(this);
		var $hid = $this.siblings('.hid');
		var li_n = $hid.find('li').length;
		var li_w = 79;
		var ul_w = li_w * li_n;
		var $ul = $hid.find('ul');
		var mag = parseInt($ul.css('margin-left'),10);
		$ul.css('width',ul_w);

		if(!$ul.is(':animated')){
			if (mag >= 0) {
				$ul.animate({'margin-left':(li_w*3 - ul_w) +'px'},400);
			} else {
				$ul.animate({'margin-left':(mag + li_w) + 'px'},400);
			}
		}
	});

	setInterval(function(){
		$('.gallery_js .prev').trigger('click');

	},3000);

	$('.col_2 .down .list li').click(function(){
		var $me = $(this).find('img'),
		    src = $me.attr('src'),
		    info = $me.attr('data-info');
		var link = $me.attr('link');
		$(this).addClass('active').siblings().removeClass('active');
		var $show = $me.closest('.down').find('.show');
		$show.find('img').attr('src',src);
		$show.find('img').click(function(){
  			location.href=link;
		});
		$show.find('p').html(info);
		$show.find('p').click(function(){
  			location.href=link;
		});
	});

});