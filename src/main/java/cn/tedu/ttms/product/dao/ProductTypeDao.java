package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeDao {
	List<Map<String,Object>> findObjects();
	
	/**
	 * 判断id下是否有子元素
	 * @param id
	 * @return
	 */
	int hasChilds(Integer id);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	/**
	 * 查询分类信息中的id,parentId,name
	 * @return
	 */
	List<Node> findZtreeNodes();
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	int insertObject(ProductType entity);
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	int updateObject(ProductType entity);
	
	/**
	 * 根据id找到对象
	 * @param id
	 * @return
	 */
	ProductType findObjectById(Integer id);
}
