$(function(){
	$("#queryFormId").on("click",".btn-search",doQueryObjects);
	doGetObjects();
});
function doQueryObjects(){
	$("#pageId").data("pageCurrent",1);
	doGetObjects();
}
function doGetObjects(){
	//初始化url
	var url="team/doFindPageObjects.do?t="+Math.random();
	//初始化参数对象
	var params = {name:$("#searchNameId").val()};
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	//params.pageCurrent=pageCurrent;
	params.pageCurrent=pageCurrent;
	//发送异步启动获取分页数据
	$.post(url,params,function(result){
		if(result.state==1){
			//1.tbodyId对应位置显示team信息
			setTableBodyRows(result.data.list);//map中的key对应的值：当前页的数据
			//设置分页信息(函数定义在了page.js文件中)
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	});
}
function setTableBodyRows(list){
	var tbody=$("#tbodyId");
	tbody.empty();
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		var tds="<td><input type='checkbox' " +
		"name='checkId' value='"+list[i].id+"'></td>"+
		"<td>"+list[i].name+"</td>"+
		"<td>"+list[i].projectName+"</td>"+
		"<td>"+(list[i].valid?"有效":"无效")+"</td>"+
		"<td><input type='button' class='btn btn-default btn-update' value='修改'></td>";
       tr.append(tds);
       tbody.append(tr);
	}	
}

function doDeleteObject(){
	var id = getSelectedId();
	var url = "type/doDeleteObject.do";
	var params = {"id":id};
	$.post(url,params,function(result){
		if(result.state == 1){
			alert(result.message);
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}

function getSelectedId(){
	var selections = $("#typeTable").boostrapTreeTable("getSelections");
	console.log(selections);
	if(selections.length == 0){
		alert("请先选择");
		return;
	}
}




