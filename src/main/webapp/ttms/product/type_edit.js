var zTree;
var setting = {
	data : {			
		simpleData : {
			enable : true,
			idKey : "id",  //节点数据中保存唯一标识的属性名称
			pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
			rootPId : null  //根节点id
		}
	}
}

$(document).ready(function(){
	//上级分类
	$("#editTypeForm").on("click",".load-product-type",loadZtreeNodes);
	//上级分类界面
	$("#typeLayer")
	.on("click",".btn-cancle,.btn_cancle",doHideZtreeNodes)
	.on("click",".btn-confirm",doSetType);
	//Back
    $("#btn-return").click(function(){
    	doBack();
    });
    //Save
    $("#btn-save").click(function(){
    	doSaveOrUpdate();
    });
    //判断新增或修改
    if($(".content").data("update")){
    	doFindObjectById($(".content").data("id"));	
    }    	
});

/*根据id执行查询操作*/
function doFindObjectById(id){
	var url="type/doFindObjectById.do";
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
	$("#typeNameId").val(data.name);
	$("#parentNameId").val(data.parentName);
	$("#parentNameId").data("parentId",data.parentId);
	$("#typeSortId").val(data.sort);
	$("#typeNoteId").html(data.note);	
}

/*设置上级分类信息*/
function doSetType(){
	//1.获得选中的数据信息
	var nodes = zTree.getSelectedNodes();
	//2.将数据信息填充在form表单中
	$("#parentNameId").val(nodes[0].name);
	$("#parentNameId").data("parentId",nodes[0].id);
	//3.隐藏zTree对象。
	doHideZtreeNodes();
}

/*加载zTree*/
function loadZtreeNodes(){
	//显示窗口
	$("#typeLayer").css("display","block");	
	//异步加载数据
	var url = "type/doFindZtreeNodes.do";
	$.getJSON(url,function(result){
		if(result.state == 1){
			//jquary.zTree.js
			zTree = $.fn.zTree.init($("#typeTree"),setting,result.data);
		}else{
			alert(result.message);
		}
	});
}

/*SaveOrUpdate*/
function doSaveOrUpdate(){
	//获取表单
	if(!$("#editTypeForm").valid())
		return;
	var params = getEditFormData();
	console.log(params);
	//保存数据
	//var url = "type/doSaveObject.do";
	var insertUrl = "type/doSaveObject.do";
	var updateUrl = "type/doUpdateObject.do";
	//判断新增或修改
	var url = $(".content").data("update") ? updateUrl : insertUrl;
	console.log("url:" + url);
	$.post(url,params,function(result){
		if(result.state == 1){
			alert(result.message);
			doBack();
		}else{
			alert(result.message);
		}
	});
}

/*获取表单数据*/
function getEditFormData(){
	var params = {
		id:$(".content").data("id"),
		name:$("#typeNameId").val(),
		parentId:$("#parentNameId").data("parentId"),
		sort:$("#typeSortId").val(),
		note:$("#typeNoteId").val()
	}
	return params;
}

/*隐藏zTree*/
function doHideZtreeNodes(){
	$("#typeLayer").css("display","none");	
}

/*返回页面*/
function doBack(){
	var url="type/listUI.do";
	$(".content").load(url);
}

