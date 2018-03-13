package cn.tedu.ttms.product.controller;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.service.TeamService;


@Controller
@RequestMapping("/team/")
public class TeamController {
	private Logger log = Logger.getLogger(ProjectController.class.getName());
	@Autowired
	private TeamService teamService;
	
	@RequestMapping("listUI")
	public String listUI(){
		return "product/team_list";
	}
	
	/**返回项目编辑页面*/
	@RequestMapping("editUI")
	public String editUI(){
		return "product/team_edit";
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent){
		log.info("name="+name);
		log.info("pageCurrent="+pageCurrent);
		return new JsonResult(teamService.findPageObjects(name,pageCurrent));
	}

}
