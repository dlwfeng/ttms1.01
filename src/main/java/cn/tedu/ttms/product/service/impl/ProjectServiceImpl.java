package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectDao projectDao;
	@Override
	public List<Project> findObjects(){
		List<Project> list = projectDao.findObjects();
		return list;
	}
	
	@Override
	public int getRowCount(String name,Integer valid) {
		int rowCount = projectDao.getRowCount(name,valid);
		return rowCount;
	}
	
	@Override
	public void saveObject(Project entity){
		if(entity == null)
			throw new ServiceException("保存对象不能为空");
		int rows = projectDao.insertObject(entity);
		if(rows <= 0)
			throw new ServiceException("insert error");
	}
	
	
	PageObject pageObject = new PageObject();
	@Override
	public Map<String,Object> findPageObjects(
			String name,
			Integer valid,
			int pageCurrent){
		int pageSize = pageObject.getPageSize();
		int startIndex = ( pageCurrent - 1 ) *pageSize;
		//获取当前页数据
		List<Project> list = projectDao.findPageObjects(name,valid,startIndex, pageSize);
		int rowCount = projectDao.getRowCount(name,valid);
		//获取总记录数并封装分页信息		
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		//将当前页数据以及分页信息封装到map
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

	@Override
	public void validById(Integer valid, String ids) {
		//1.对数据进行业务验证	
		if(valid!=0&&valid!=1)
			//在业务层抛出一个异常，通常封装一个业务层Exception
			//若在dao层抛出一个异常，通常封装一个dao层Exception			
			throw new ServiceException("valid的值不合法：valid="+valid);
		if(/*ids==null||ids==""*/StringUtils.isEmpty(ids))
			throw new ServiceException("ids的值不合法：ids="+ids);	
		//2.对参数数据进行处理
		String[] idArray = ids.split(",");
		//3.执行业务更新操作
		int rows = projectDao.validById(valid, idArray);
		//4.验证结果的有效性
		if(rows==0)
			throw new ServiceException("修改失败");		
	}

	@Override
	public void updateObject(Project entity) {
		if(entity == null)
			throw new ServiceException("修改对象不能为空");
		int rows = projectDao.updateObject(entity);
		if(rows <= 0)
			throw new ServiceException("update error");		
	}

	@Override
	public Project findObjectById(Integer id) {
		if(id == null)
			throw new ServiceException("id不能为空");
		Project project = projectDao.findObjectById(id);
		if(project == null)
			throw new ServiceException("对象不存在");
		return project;
	}

	@Override
	public void deleteById(String ids) {
		if(StringUtils.isEmpty(ids))
			throw new ServiceException("ids的值不合法：ids="+ids);	
		//2.对参数数据进行处理
		String[] idArray = ids.split(",");
		//3.执行业务更新操作
		int rows = projectDao.deleteById(idArray);
		//4.验证结果的有效性
		if(rows==0)
			throw new ServiceException("修改失败");	
		
	}
	
}