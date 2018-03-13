package product.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.TeamService;

public class TestTeamService extends TestBase{
	@Test
	public void testFindPageObjects(){
		//获得TeamService对象
		TeamService teamService = ctx.getBean("teamServiceImpl",TeamService.class);
		//2.分页查询数据
		Map<String,Object> map = teamService.findPageObjects("环球",1);
		List<Project>list = (List<Project>)map.get("list");
		PageObject pageObject = (PageObject)map.get("pageObject");
		//3.对获取数据进行测试
		//Assert.assertEquals(1, list.size());
		//Assert.assertEquals(1, pageObject.getPageCount());
		System.out.println("list="+list);
	}
}
