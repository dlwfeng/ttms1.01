package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

@Controller
@RequestMapping("/project/")
public class ProjectController {
	private Logger log = Logger.getLogger(ProjectController.class.getName());
	private int count;
	@Autowired
	private ProjectService projectService;
	/**返回项目列表页面*/
	@RequestMapping("listUI")
	public String listUI(){
		synchronized(this){
			count++;
		}
		return "product/project_list";
	}
	
	/**返回项目编辑页面*/
	@RequestMapping("editUI")
	public String editUI(){
		return "product/project_edit";
	}
	
	/**禁用启用项目信息*/
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer valid,String ids){
		projectService.validById(valid, ids);
		return new JsonResult(valid == 1 ? "启用OK" : "禁用OK");
	}
	
	/**删除项目信息*/
	@RequestMapping("doDeleteById")
	@ResponseBody
	public JsonResult doDeleteById(String ids){
		projectService.deleteById(ids);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("doGetPageObjects")
	@ResponseBody
	public JsonResult doGetPageObjects(String name,Integer valid,Integer pageCurrent){
		Map<String,Object> map = projectService.findPageObjects(name,valid,pageCurrent);
		log.info("name="+name);
		log.info("valid="+valid);
		log.info("pageCurrent="+pageCurrent);
		return new JsonResult(map);
	}
	
	
	/**
	 * spring 发现控制层方法上有ResponseBody注解，
	 * 此时就会启用第三方API(例如jackson,gson),
	 * 将返回的对象转换成JSON字符串
	 */
	@RequestMapping("doGetObjects")
	@ResponseBody
	public List<Project> doGetObjects(){
		List<Project> list = projectService.findObjects();
		return list;
	}
	
	/**
	 * name="东欧游"&code="..."&beginDate="..."
	 * spring 获得参数数据对数据解析调用project对象的setXXX方法将数据存储到project对象
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Project entity){
		projectService.saveObject(entity);
		return new JsonResult("添加成功");
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Project entity){
		projectService.updateObject(entity);
		return new JsonResult("修改成功");		
	}
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Project project = projectService.findObjectById(id);
		return new JsonResult(project);		
	}
}
