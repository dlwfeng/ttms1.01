package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Project;

/** 项目模块持久对象 */
public interface ProjectDao {
	/** 获取所有项目信息 */
	List<Project> findObjects();
	/**
	 * @param name 查询时用户输入的项目名称
	 * @param valid 查询时用户输入的转状态
	 * @param startIndex 分页查询时的起始位置
	 * @param pageSize 每页最多显示多少条记录	 
	 * @return 当前页数据
	 */
	List<Project> findPageObjects(
		@Param("name")String name,
		@Param("valid")Integer valid,
		@Param("startIndex")int startIndex,
		@Param("pageSize")int pageSize);
	
	/**
	 * 返回总条数
	 * @param name 查询时用户输入的项目名称
	 * @param valid 查询时用户输入的转状态
	 * @return
	 */
	int getRowCount(
		@Param("name")String name,
		@Param("valid")Integer valid);
	
	/**
	 * 根据ids修改状态
	 * @param valid 状态
	 * @param ids 
	 * @return
	 */
	int validById(
		@Param("valid")Integer valid,
		@Param("ids")String[] ids);
	
	/**
	 * 根据ids删除
	 * @param ids
	 * @return
	 */
	int deleteById(@Param("ids")String[] ids);
	
	/**
	 * 插入项目
	 * @param entity
	 * @return
	 */
	int insertObject(Project entity);
	
	/**
	 * 修改项目
	 * @param entity
	 */
	int updateObject(Project entity);
	
	/**
	 * 通过id查找项目
	 * @param id
	 * @return project
	 */
	Project findObjectById(Integer id);
	
}
