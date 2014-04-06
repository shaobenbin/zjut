// JavaScript Document

/**
 * @author Mr.Think
 * @author blog http://mrthink.net/
 * @2010.07.23
 * 可自由转载及使用,但请注明版权归属
 */
$(function(){
    //@Mr.Think***变量
    var $cur = 1;//初始化显示的版面
    var $i = 5;//每版显示数
    var $len = $('.showbox>ul>li').length;//计算列表总长度(个数)
    var $pages = Math.ceil($len / $i);//计算展示版面数量
    var $w = $('.ibox').width();//取得展示区外围宽度
    var $showbox = $('.showbox');
    var $num = $('span.num li')
    var $pre = $('span.pre')
    var $next = $('span.next');
    var $autoFun;
	//@Mr.Think***调用自动滚动
    autoSlide();
	//@Mr.Think***向前滚动
    $pre.click(function(){
        if (!$showbox.is(':animated')) {  //判断展示区是否动画
            if ($cur == 1) {   //在第一个版面时,再向前滚动到最后一个版面
                $showbox.animate({
                    left: '-=' + $w * ($pages - 1)
                }, 2000); //改变left值,切换显示版面,500(ms)为滚动时间,下同
                $cur = $pages; //初始化版面为最后一个版面
            }
            else { 
                $showbox.animate({
                    left: '+=' + $w
                }, 2000); //改变left值,切换显示版面
                $cur--; //版面累减
            }
            $num.eq($cur - 1).addClass('numcur').siblings().removeClass('numcur'); //为对应的版面数字加上高亮样式,并移除同级元素的高亮样式
        }
    });
    //@Mr.Think***向后滚动
    $next.click(function(){
        if (!$showbox.is(':animated')) { //判断展示区是否动画
            if ($cur == $pages) {  //在最后一个版面时,再向后滚动到第一个版面
                $showbox.animate({
                    left: 0
                }, 2000); //改变left值,切换显示版面,500(ms)为滚动时间,下同
                $cur = 1; //初始化版面为第一个版面
            }
            else {
                $showbox.animate({
                    left: '-=' + $w
                }, 2000);//改变left值,切换显示版面
                $cur++; //版面数累加
            }
            $num.eq($cur - 1).addClass('numcur').siblings().removeClass('numcur'); //为对应的版面数字加上高亮样式,并移除同级元素的高亮样式
        }
    });
    //@Mr.Think***数字点击事件
    $num.click(function(){
        if (!$showbox.is(':animated')) { //判断展示区是否动画
            var $index = $num.index(this); //索引出当前点击在列表中的位置值
            $showbox.animate({
                left: '-' + ($w * $index) 
            }, 2000); //改变left值,切换显示版面,500(ms)为滚动时间
            $cur = $index + 1; //初始化版面值,这一句可避免当滚动到第三版时,点击向后按钮,出面空白版.index()取值是从0开始的,故加1
            $(this).addClass('numcur').siblings().removeClass('numcur'); //为当前点击加上高亮样式,并移除同级元素的高亮样式
        }
    });
    //@Mr.Think***停止滚动
    clearFun($showbox);
    clearFun($pre);
    clearFun($next);
    clearFun($num);
    //@Mr.Think***事件划入时停止自动滚动
    function clearFun(elem){
        elem.hover(function(){
            clearAuto();
        }, function(){
            autoSlide();
        });
    }
    //@Mr.Think***自动滚动
    function autoSlide(){
        $next.trigger('click');
        $autoFun = setTimeout(autoSlide, 5000);//此处不可使用setInterval,setInterval是重复执行传入函数,这会引起第二次划入时停止失效
    }
    //@Mr.Think***清除自动滚动
    function clearAuto(){
        clearTimeout($autoFun);
    }
});




//滑动广告
function advTab(i,obj1,obj2){
	$(obj1).addClass("active").siblings().removeClass("active");
	$(obj2).stop().animate({marginLeft:-$(obj2).parent().width()*i});
}
$(function(){
	proClick();
})

var proi=-1;
function proClick(){
	if(proi<3){
		proi++;
	}else{
		proi=0
	}
	
	$("#proSelect a").eq(proi).mousemove();
	
	setTimeout(proClick,4000)
}

//
$(function(){
	$(".close").click(function(){
		$("#divQQbox").hide();
	});
});