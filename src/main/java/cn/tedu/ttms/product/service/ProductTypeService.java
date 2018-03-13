package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeService {
	List<Map<String,Object>> findObjects();
	
	public void deleteObject(Integer id);
	
	List<Node> findZtreeNodes();
	
	void saveObject(ProductType entity);
	
	void updateObject(ProductType entity);
	
	ProductType findObjectById(Integer id);
}
