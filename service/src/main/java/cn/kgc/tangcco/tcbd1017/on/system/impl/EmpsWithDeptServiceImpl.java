package cn.kgc.tangcco.tcbd1017.on.system.impl;


import java.util.HashMap;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;

import cn.kgc.tangcco.tcbd1017.on.system.EmpsWithDept;
import cn.kgc.tangcco.tcbd1017.on.system.EmpsWithDeptService;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月20日 下午6:40:38
* @edition 1.0
* @Description 类描述
*/
public class EmpsWithDeptServiceImpl implements EmpsWithDeptService{
	static EmpsWithDept empsWithDept;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			empsWithDept =(EmpsWithDept) classPathXmlApplicationContext.getBean(EmpsWithDept.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> queryEmpsWithDept(Map<String, Object> map1) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		map.put("pageCount", 0);
		int pageCount = 1;
		// 总记录数
		int count = empsWithDept.EmpsWithDeptcount(map1);
		System.out.println(count);
		if (count > 0) {
			// 存储当前页码号和每页记录数
			PageRang pr = (PageRang) map1.get("pr");
			// 每页记录数
			int pageSize = pr.getPageSize();
			// 总页面号
			pageCount = (int) ((count / pageSize != 0) ? Math.ceil((count + 0.0) / pageSize) : (count / pageSize));
			// 存储总页面数
			map.put("pageCount", pageCount);
			map.put("count", count);
			// 保护性代码保护传小于1的页面和大于最大页面的值
			int pageNumber = pr.getPageNumber();
			map.put("pr", pr);}
		if (ObjectUtils.isEmpty(empsWithDept.selectEmpsWithDept(map1))) {
			map.put("data", null);
			return map;
		}else {
			map.put("status", "success");
			map.put("data",empsWithDept.selectEmpsWithDept(map1));
		}
		return map;
	}

}
