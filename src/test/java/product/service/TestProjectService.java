package product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
import cn.tedu.ttms.product.service.TeamService;

public class TestProjectService extends TestBase{
	
	
	@Test
	public void testFindObjects(){
		//1.获得ProjectService对象
		ProjectService projectService = ctx.getBean("projectServiceImpl",ProjectService.class);
		//2.执行ProjectService对象的findObjects方法
		List<Project> list = projectService.findObjects();
		//3.验证结果是否正确
		Assert.assertNotEquals(0, list.size());
		//4.输出执行结果
		System.out.println(list);
	}
	
	@Test
	public void testFindPageObjects(){
		ProjectService projectService = ctx.getBean("projectServiceImpl",ProjectService.class);
		//2.分页查询数据
		Map<String,Object> map = projectService.findPageObjects("环球",1,1);
		List<Project>list = (List<Project>)map.get("list");
		PageObject pageObject = (PageObject)map.get("pageObject");
		//3.对获取数据进行测试
		//Assert.assertEquals(1, list.size());
		//Assert.assertEquals(1, pageObject.getPageCount());
		System.out.println("list="+list);
	}
	
	@Test
	public void testValidById(){
		ProjectService projectService = ctx.getBean("projectServiceImpl",ProjectService.class);
		Integer valid = 1;
		String ids = "1,3,4";
		projectService.validById(valid, ids);
	}
	
	@Test
	public void testSaveObject(){
		ProjectService projectService = ctx.getBean("projectServiceImpl",ProjectService.class);
		Project entity = new Project();
		entity.setName("东欧游");
		entity.setCode("tt-20171014-CN-BJ-001");
		entity.setBeginDate(new Date());
		entity.setEndDate(new Date());
		entity.setValid(1);
		entity.setNote("德国游");
		projectService.saveObject(entity);
	}
	
	@Test
	public void testUpdateObject(){
		ProjectService projectService = ctx.getBean("projectServiceImpl",ProjectService.class);
		Project entity = projectService.findObjectById(8);
		Assert.assertNotEquals(null,entity);
		entity.setName("123456789");
		projectService.updateObject(entity);
	}
	
	
	

}
