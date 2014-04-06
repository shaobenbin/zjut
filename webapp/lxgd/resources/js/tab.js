var locationUrl = location.href.trim();
if(locationUrl.indexOf('tab')>=0){
	var id = locationUrl.substring(locationUrl.indexOf('tab')+4,locationUrl.indexOf('tab')+5);
	$('.tabBody').eq(parseInt(id)).addClass('active').siblings().removeClass('active');
	$('.leftNav').find('li').eq(parseInt(id)).addClass('active').siblings().removeClass('active');
}