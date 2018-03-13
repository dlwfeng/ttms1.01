/**
 * 定义table表头及每列元素数据信息
 */
var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];//treegrid

$(document).ready(function(){
	$("#formHead")
	.on("click",".btn-delete",doDeleteObject)
	.on("click",".btn-add,.btn-update",doLoadEditPage)
	doGetObjects();
});

function doLoadEditPage(){
	var url="type/editUI.do";
	var id = getSelectedId();
	//update初始值0
	$(".content").data("update",0);
	if($(this).hasClass("btn-update")){
		if(id == null){
			alert("至少选择一个分类");
			return;
		}
		var title = "修改分类";
		$(".content").data("update",1);//
		$(".content").data("id",id);	
	}
	$(".content").load(url,function(){
		$("#titleId").html(title);		
	});
}

function doDeleteObject(){
	var id = getSelectedId();
	var url="type/doDeleteObject.do";
	var params={"id":id};
	if(id == null){
		alert("至少选择一个分类");
		return;
	}
	//2.根据id值删除分类信息
	$.post(url,params,function(result){
		if(result.state==1){
			alert(result.message);
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}

function getSelectedId(){
	var selections = $("#typeTable").bootstrapTreeTable("getSelections");
//	console.log("list:selections:");
	console.log(selections);
	if(selections.length == 0){		
		return;		
	}
	console.log(selections[0].id);
	return selections[0].id;
}

function doGetObjects(){
	var tableId = "typeTable";
	var url = "type/doFindObjects.do";
	//TreeTable是在table.js中定义
	var table = new TreeTable(tableId,url,columns);
	//设置在哪一列上展开树结构（0表示第一列）
	table.setExpandColumn(2);
	//初始化table对象（底层会发起异步请求然后更新页面）
	table.init();
}