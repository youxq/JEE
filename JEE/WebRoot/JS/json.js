$(document).ready(function() {

	/**
	 * ajax
	 */
	function ajax(method, remote_url, send_data, callback) {
		$.ajax({
			type : method,
			url : remote_url,
			data : send_data,
			dataType : 'json',
			success : function(result) {
				if (result == "" || result == null) {
					alert("后台错误");
				} else {
					callback(result); 
					alert("成功");
				}
			},
			error : function(xhr, textStatus, errorThrown) {
				alert("网络错误");
			}

		});
	}

	function callback(result){
		alert(JSON.stringify(result));
		// 弹出data字符串
		alert(result.data);
		// 遍历list
		var list = result.list;
		for(var i=0;i<list.length;i++){
			alert(list[i]);
		}
	}
	
	
	$("input[id='test']").click(function(){
		alert("测试");
		// 创建普通的字符串数组
		var mycars=new Array("Saab","Volvo","BMW");
		// 将数组转化为JSON字符串
		JSON.stringify(mycars);
		// 创建普通的JSON数组
		var jsonArray = [{"userName":"you","password":"123"},{"userName":"hey","password":"456"}];
		// 只有将json数组格式化之后才能传送到后台，所以不管是什么数据最好都stringify一下，这样比较严格
		var send_data = {"data":JSON.stringify(jsonArray)};
		var remote_url = "/JEE/servlet/JSONServlet";
		ajax("POST",remote_url,send_data,callback);
	});
	
});