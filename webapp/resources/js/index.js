$(function(){
	//banner区选项卡
	var imgArray = (function() {
        var a = [], banLengh, imgLength, bans, imgs;
        bans = $('.banner .ban');
        banLengh = bans.length;
        for (var i=0; i<banLengh; i++) {
            imgs = bans.eq(i).find('ul img');
            imgLength = imgs.length;
            a[i] = [];
            for (var j=0; j<imgLength; j++) {
                a[i][j] = imgs.eq(j).attr('src');
            }
        }

        return a;
    }());
    var imgIndex = [];
    for (var k=0; k<imgArray.length; k++) {
        imgIndex[k] = 0;
    }
    $('.t_js').on('click',function(){
        var $this = $(this);
        var index = $this.index();

        $this.parent().get(0).className = 'nav color_' + (index + 1);


        if (imgIndex[index] >= imgArray[index].length) {
            imgIndex[index] = 0;
        }
        $this.addClass('cur')
            .siblings().removeClass('cur');
        $('.banner .ban').eq(index).find(' > img').attr('src', imgArray[index][imgIndex[index]++])
                        .end().show(300).siblings('.c_js').hide(300); 
    });


	setInterval(function(){
		var i = $('.t_js.cur').index();
		i < ($('.t_js').length-1) ? i++ : i = 0;
		$('.t_js').eq(i).trigger('click');
	},8000);

	//gallery

	$('.gallery_js .prev').on('click',function(){
		var $this = $(this);
		var $hid = $this.siblings('.hid');
		var li_n = $hid.find('li').length;
		var li_w = $hid.find('li').width();
		var ul_w = li_w * li_n;
		var $ul = $hid.find('ul');
		var mag = parseInt($ul.css('margin-left'),10);
		$ul.css('width',ul_w);
		if(!$ul.is(':animated')){
			if (mag <= li_w - ul_w) {
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
		var li_w = $hid.find('li').width();
		var ul_w = li_w * li_n;
		var $ul = $hid.find('ul');
		var mag = parseInt($ul.css('margin-left'),10);
		$ul.css('width',ul_w);

		if(!$ul.is(':animated')){
			if (mag >= 0) {
				$ul.animate({'margin-left':(li_w - ul_w) +'px'},400);
			} else {
				$ul.animate({'margin-left':(mag + li_w) + 'px'},400);
			}
		}
	});

	setInterval(function(){
		$('.gallery_js .prev').trigger('click');

	},6000);

	//header部分sub_nav
	$('.header .sub_nav .last').hover(function(){
		$(this).addClass('down');
	},function(){
		$(this).removeClass('down');
	});

	$(function(){
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

 
 	$('.ban .pic li img').click(function(){
		$(this).parents('.ban').find('>img').attr('src', $(this).attr('src'));
	});

});