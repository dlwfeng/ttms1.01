package cn.tedu.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{
	@Autowired
	private ProductTypeDao productTypeDao;
	@Override
	public List<Map<String,Object>> findObjects(){
		return productTypeDao.findObjects();
	}
	@Override
	public void deleteObject(Integer id) {
		//判定id有效
		//if(id == null || id <= 0)
			//throw new ServiceException("id无效，id="+id);
		int count = productTypeDao.hasChilds(id);
		if(count > 0)
			throw new ServiceException("有子元素不删除");
		int rows = productTypeDao.deleteObject(id);
		if(rows < 1)
			throw new ServiceException("删除失败");
	}
	
	@Override
	public List<Node> findZtreeNodes() {
		return productTypeDao.findZtreeNodes();
	}
	
	@Override
	public void saveObject(ProductType entity) {
		//System.out.println("before:"+entity);
		if(entity == null)
			throw new ServiceException("保存对象不能为空");
		int rows = productTypeDao.insertObject(entity);
		if(rows < 1)
			throw new ServiceException("数据保存失败");
		//System.out.println("after:"+entity);
	}
	@Override
	public void updateObject(ProductType entity) {
		System.out.println(entity);
		if(entity == null)
			throw new ServiceException("修改对象不能为空");
		int rows = productTypeDao.updateObject(entity);
		if(rows <= 0)
			throw new ServiceException("update error");		
	}
	
	@Override
	public ProductType findObjectById(Integer id) {
		if(id == null)
			throw new ServiceException("id不能为空");
		ProductType entity = productTypeDao.findObjectById(id);
		if(entity == null)
			throw new ServiceException("对象不存在");
		return entity;
	}

}
