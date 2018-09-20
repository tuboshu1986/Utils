/**
 * 浏览器对象
 */
var BOMUtils = {};

/**
 * 获取请求URL中的参数
 */
BOMUtils.getQueryStringArgs = function(){
	var qs = (location.search.length>0?location.search.substring(1):"");
	var args = {};
	var items = qs.length>0?qs.split("&"):[];
	var item = null;
	var name = null;
	var value = null;
	for(var i=0;i<items.length;i++){
		item = items[i].split("=");
		name = item[0];
		value = item[1];
		args[name] = value;
	}
	return args;
};

