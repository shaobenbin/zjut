$(function(){
	$('.map .op').on('click', '.prev', function() {
		var me = $(this),
			ms = $('.ms'),
			cur = ms.find('.active').index(),
			li = $('.map li');

		if (cur <= 0) {
			cur = 2;
		} else {
			cur -= 1;
		}

		if (!ms.find('.m').is(':animated')) {
			ms.find('.active').fadeOut(300).removeClass('active');
			ms.find('.m').eq(cur).fadeIn(300).addClass('active');
			li.filter('.active').removeClass('active');
			li.eq(cur).addClass('active');
		}
		return false;
	});

	$('.map .op').on('click', '.next', function() {
		var me = $(this),
			ms = $('.ms'),
			cur = ms.find('.active').index(),
			li = $('.map li');

		if (cur >= 2) {
			cur = 0;
		} else {
			cur += 1;
		}

		if (!ms.find('.m').is(':animated')) {
			ms.find('.active').fadeOut(300).removeClass('active');
			ms.find('.m').eq(cur).fadeIn(300).addClass('active');
			li.filter('.active').removeClass('active');
			li.eq(cur).addClass('active');
		}
		return false;
	});

	$('.map .op').on('click', 'li', function(){
		if ($(this).is('.active')) return false;
		var cur = $(this).index(),
			m = $('.ms .m');

		m.filter('.active').fadeOut(300);
		m.eq(cur).fadeIn(300);
		m.eq(cur).addClass('active')
					.siblings().removeClass('active');
		$(this).addClass('active');
		$(this).siblings().removeClass('active');
		return false;
	})
});