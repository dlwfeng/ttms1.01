$(document).ready(function(){
	$("#modal-dialog").on("click",".ok",doSaveOrUpdate);
	//在模态框隐藏后
	$("#modal-dialog").on("hidden.bs.modal",function(){
		console.log("==hidden.bs.midal==");
		//移除OK上注册的事件，防止数据多次提交
		$("#modal-dialog").off("click",".ok");
		//移除绑定的idKey
		$("#modal-dialog").removeData("idKey");
	});
	//获取idKey
	var id = $("#modal-dialog").data("idKey");
	if(id)
		doFindObjectById(id);
});

/*根据id执行查询操作*/
function doFindObjectById(id){
	var url="project/doFindObjectById.do";
	var params={"id":id};
	$.getJSON(url,params,function(result){
		if(result.state == 1){
			doInitFormData(result.data);
		}else if(result.state == 0){
			alert(result.message);
		}
	});
}

/*写入数据*/
function doInitFormData(data){
	$("#nameId").val(data.name);
	$("#codeId").val(data.code);
	$("#beginDateId").val(data.beginDate);
	$("#endDateId").val(data.endDate);
	$("#noteId").html(data.note);
	$("#editFormId input[name='valid'").each(function(){
		//迭代每个选项，值相等的设置选中
		if($(this).val() == data.valid){
			$(this).prop("checked",true);
		}
	})
}

/*保存或修改数据*/
function doSaveOrUpdate(){
	//0.验证表单数据是否为空
	if(!$("#editFormId").valid())
		return;
	//1.获得表单数据
	var params = getEditFormDate();
	console.log(params);
	//2.异步提交表达数据
	var insertUrl = "project/doSaveObject.do";
	var updateUrl = "project/doUpdateObject.do";
	//获取模态框上绑定到id
	var id = $("#modal-dialog").data("idKey");
	//根据id判定
	var url = id ? updateUrl : insertUrl;
	//若修改，将id赋值
	if(id)
		params.id = id;
	$.post(url,params,function(result){
		if(result.state == 1){
			//隐藏模态框
			$("#modal-dialog").modal("hide");
			//显示相关信息
			alert(result.message);
			//重新查询数据
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}

function getEditFormDate(){
	var params = {
		name:$("#nameId").val(),
		code:$("#codeId").val(),
		beginDate:$("#beginDateId").val(),
		endDate:$("#endDateId").val(),
		valid:$("input[type='radio']:checked").val(),
		note:$("#noteId").val()
	}
	return params;
}