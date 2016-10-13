/*
 * Translated default messages for the jQuery validation plugin.
 * Language: CN
 * Author: Fayland Lam <fayland at gmail dot com>
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
		remote: "请修正该字段",
		email: "请输入正确格式",
		url: "输入合法的网址",
		date: "输入合法的日期",
		dateISO: "合法的日期 (ISO).",
		number: "合法的数字",
		digits: "只能输入整数",
		creditcard: "合法的信用卡号",
		equalTo: "输入相同的值",
		accept: "拥有合法后缀名的字符串",
		maxlength: jQuery.format("最多是 {0}位 的字符串"),
		minlength: jQuery.format("最少是 {0} 位 的字符串"),
		rangelength: jQuery.format("介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.format("介于 {0} 和 {1} 之间的值"),
		max: jQuery.format("最大为 {0} 的值"),
		min: jQuery.format("最小为 {0} 的值")
});

jQuery.validator.addMethod("mobile", function(value, element) {  
	var length = value.length;  
	return this.optional(element) || (length == 11 && /^[1][358][0-9]{9}$/.test(value));  
	}, "手机号码格式错误!");    
jQuery.validator.addMethod("idNumber", function(value, element) {   
    var tel = /^(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
    return this.optional(element) || (tel.test(value));
}, "身份证格式不正确");

jQuery.validator.addMethod("goodsPrice", function(value, element) {   
    var tel = /^\d+\.\d{2}$/;
    return this.optional(element) || (tel.test(value));
}, "价钱格式不正确,保留两位小数（xx.xx）");


jQuery.validator.addMethod("month", function(value, element) {   
    //var tel = /^[1-9]$)|(^[1][0-2]$)$/;
    return this.optional(element) || (tel.test(value));
}, "月份输入不正确");
jQuery.validator.addMethod("year", function(value, element) {   
    var tel = /^\d{4}.*/;
    return this.optional(element) || (tel.test(value));
}, "年度输入不正确");