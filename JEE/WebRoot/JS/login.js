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
		for(var i=0;i<result.length;i++){
			alert(result[i]);
		}
	}
	
	
	$("input[id='test']").click(function(){
		alert("测试");
		var mycars=new Array("Saab","Volvo","BMW");
		alert(mycars);
		alert(JSON.stringify(mycars));
		var jsonArray = [{"userName":"you","password":"123"},{"userName":"hey","password":"456"}];
		var send_data = {"data":JSON.stringify(jsonArray)};
		var remote_url = "/JEE/servlet/LoginServlet";
		ajax("POST",remote_url,send_data,callback);
	});
	
});