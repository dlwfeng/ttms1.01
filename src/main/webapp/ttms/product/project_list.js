$(document).ready(function(){
	$("#queryFormId")
	.on("click",".btn-search",doQueryObjects)
	.on("click",".btn-valid,.btn-invalid",doValidById)
	.on("click",".btn-delete",doDeleteById)
	.on("click",".btn-add,.btn-update",doLoadEditPage)
	doGetObjects();
});

/*加载编辑页面*/
function doLoadEditPage(){
	var url = "project/editUI.do";
	var title = "添加项目信息";
	if($(this).hasClass("btn-update")){
		var idValue = $(this).parent().parent().data("id");
		$("#modal-dialog").data("idKey",idValue);
		title = "修改信息：项目编码-" + idValue;
	}
	//$(".content").load(url)
	//本模态框中异步加载显示编辑页面
	//本项目中模态框的定义在index.jsp中，而且默认是隐藏的
	$("#modal-dialog .modal-body").load(url,function(){//call back method
		$("#myModalLabel").html(title);
		//页面加载完成后显示模态框
		$("#modal-dialog").modal("show");	
	})
}

/*禁用或启用项目信息*/
function doValidById(){
	//1.设置valid值
	var valid;
	//点击获得对象
	if($(this).hasClass("btn-valid")){
		valid = 1;//禁用
	}
	if($(this).hasClass("btn-invalid")){
		valid = 0;//启用
	}
	//2.获得选中的checkbox的id值
	var ids = "";
	$("#tbodyId input[name='checkId']").each(function(){
		if($(this).prop("checked")){//判定此input对象是否是选中对象
			if(ids == ""){
				ids += $(this).val();
			}else{
				ids += "," + $(this).val();
			}
		}
	});
	if(ids == ""){
		alert("请至少选择一个");
		return;
	}	
	//3.发送异步请求更新设置
	var url = "project/doValidById.do";
	var params = {
			"valid":valid,
			"ids":ids
	}
	$.post(url,params,function(result){		
		if(result.state == 1){
			alert(result.message);
			//重新执行查询操作
			doGetObjects();	
		} else if(result.state == 0){
			alert(result.message);
		}
			
	})
}

function doDeleteById(){
	//2.获得选中的checkbox的id值
	var ids = "";
	$("#tbodyId input[name='checkId']").each(function(){
		if($(this).prop("checked")){//判定此input对象是否是选中对象
			if(ids==""){
				ids += $(this).val();
			}else{
				ids += "," + $(this).val();
			}
		}
	});
	if(ids == ""){
		alert("请至少选择一个");
		return;
	}	
	//3.发送异步请求更新设置
	var url = "project/doDeleteById.do";
	var params = {
			"ids":ids
	}
	$.post(url,params,function(result){		
		if(result.state == 1){
			alert(result.message);
			//重新执行查询操作
			doGetObjects();	
		} else if(result.state == 0){
			alert(result.message);
		}			
	})
}

/*查询项目*/
function doQueryObjects(){
	//1.初始化当前页码
	$("#pageId").data("pageCurrent",1);
	//2.根据条件查询数据
	doGetObjects();
}

/*查询项目信息*/
function doGetObjects(){
	var url = "project/doGetPageObjects.do";
	var pageCurrent = $("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent = 1;
	var params = {"pageCurrent":pageCurrent};
	params.name = $("#searchNameId").val();
	params.valid = $("#searchValidId").val();
	console.log(params);
	$.getJSON(url,params,function(result){//转换为json对象
		if(result.state == 1){
			//将数据显示在table的tbody位置
			setTableBodyRows(result.data.list);//map中的key对应的值
			//设置分页信息
			setPagination(result.data.pageObject);
		}else if(result.state == 0){
			alert(result.message);
		}
		
	});
}

/*将数据填充在table对象的body中*/
function setTableBodyRows(data){
	//获取tbody对象
	var tBody = $("#tbodyId");
	tBody.empty();
	//迭代数据集result
	for(var i in data){
		//构建tr，td，填充具体数据
		var tr = $("<tr></tr>");
		tr.data("id",data[i].id);
		var tds = "<td><input type='checkbox' name='checkId' value='" + data[i].id + "'></td>" 
			+  "<td>" + data[i].code + "</td>" 
			+  "<td>" + data[i].name + "</td>"
			+  "<td>" + data[i].beginDate + "</td>"
			+  "<td>" + data[i].endDate + "</td>"
			+  "<td>" + (data[i].valid ? "启用" : "禁用") + "</td>"
			+  "<td><input type='button' class='btn btn-default btn-update' value='修改'></td>";
		//将td添加到tr对象中（一行放多个）
		tr.append(tds);
		//将tr追加到tbody中
		tBody.append(tr);
	}
	
}