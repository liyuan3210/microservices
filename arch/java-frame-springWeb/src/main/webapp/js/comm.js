/////////////////////////////////////////////////from opertion///////////////////////////////////////////////
$.fn.serializeToJson = function(notEmptyField){
    var obj = {};
    $.each( this.serializeArray(), function(i,o){
        var n = o.name, v = $.trim(o.value);
        if (!(notEmptyField && "" == v)) {
        	obj[n] = (obj[n] === undefined) ? v : $.isArray(obj[n]) ? obj[n].concat(v) : [obj[n], v];
        }
    });
    return obj;
};

function serializeToJson(selector,jsonObject,type, attr) {
	if(!attr)attr='field';
	if(!jsonObject)return false;
     for(var key in jsonObject) {
     	var inputObj = $(selector).find("["+attr+"='"+key+"']");
     	if (inputObj && inputObj.size() > 0 && null != jsonObject[key] && "null" != jsonObject[key]) {
     		if (type && 'text'== type) {
     			inputObj.text(jsonObject[key]);
     		} else {
     			inputObj.val(jsonObject[key]);
     		}
     	}
     }
};
//获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null) return unescape(r[2]); return null; //返回参数值
}
