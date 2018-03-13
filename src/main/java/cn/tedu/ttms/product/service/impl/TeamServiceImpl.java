package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.service.TeamService;
@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
    private TeamDao teamDao;
	@Override
   public Map<String, Object> findPageObjects(String name, Integer pageCurrent) {
        //1.获取当前页数据
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<Map<String,Object>> list=
				teamDao.findPageObjects(name, startIndex, pageSize);
		//2.计算并封装分页信息
		PageObject pageObject=new PageObject();
		int rowCount=teamDao.getRowCount(name);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setStartIndex(startIndex);
		//3.封装当前页数据和分页信息
		Map<String,Object> map=
				new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
}
}
