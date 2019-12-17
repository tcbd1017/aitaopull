package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.PostageInfoDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.PostageInfoService;
import cn.kgc.tangcco.tcbd1017.on.pojo.PostageInfo;

/**
 * @author LIU KAI
 * @version 1.0 2019年12月14日 下午1:32:20 </br>
 */

public class PostageInfoServiceImpl implements PostageInfoService {
	private static PostageInfoDao postageInfoDao;
	private static ClassPathXmlApplicationContext path;
	static {
		path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			postageInfoDao = (PostageInfoDao) path.getBean(PostageInfoDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> queryPostageInfosByBuyerId(Map<String, Object> map) {
		Map<String, Object> info = new LinkedHashMap<String, Object>();
		info.put("msg", "");
		info.put("code", 0);
		info.put("data", new ArrayList<PostageInfo>());
		info.put("status", "failed");
		try {
			List<PostageInfo> list = postageInfoDao.selectPostageInfosByBuyerId(map);
			if (list != null) {
				info.put("status", "success");
				info.put("data", list);
			}
			BaseDBUtils.closeAll();
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}

	@Override
	public Map<String, Object> addPostageInfo(Map<String, Object> map) {
		Map<String, Object> info = new LinkedHashMap<String, Object>();
		info.put("status", "failed");
		// 新增前查看新增的状态是否是3（ 默认收件地址）
		try {
			if (!ObjectUtils.isEmpty(map.get("postage_info_status")) && (int) map.get("postage_info_status") == 3) {
				// 开启事务
				BaseDBUtils.startTransaction();
				// 查询该买家是否存在默认收件地址
				int count = postageInfoDao.selectCountByStatus(map);
				if (count > 0) {
					// 若存在 查到该信息
					PostageInfo postageInfo = postageInfoDao.selectPostageInfoByStatus(map);
					postageInfo.setPostage_info_status(2);
					map.put("postageInfo", postageInfo);
					// 将该信息的状态改为普通地址
					int status = postageInfoDao.updatePostageInfosByStatus(map);
				}
			}
			// 添加收货信息表
			int postageInfoCount = postageInfoDao.insertPostageInfo(map);
			// 添加收货信息和买家中间表
			int buyerAndPostageInfoCount = postageInfoDao.insertbuyerAndPostageInfo(map);
			if (postageInfoCount > 0 && buyerAndPostageInfoCount > 0) {
				info.put("status", "success");
				BaseDBUtils.commitAndClose();
				return info;
			}
		} catch (SQLException e) {
			e.printStackTrace();
				try {
					BaseDBUtils.rollbackAndClose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		return info;
	}

	@Override
	public Map<String, Object> modifyPostageInfo(Map<String, Object> map) {
		Map<String, Object> info = new LinkedHashMap<String, Object>();
		info.put("status", "failed");
		// 修改前查看修改的状态是否是3（ 默认收件地址）
		try {
			if (!ObjectUtils.isEmpty(map.get("postage_info_status")) && (int) map.get("postage_info_status") == 3) {
				// 开启事务
				BaseDBUtils.startTransaction();
				// 查询该买家是否存在默认收件地址
				int count = postageInfoDao.selectCountByStatus(map);
				if (count > 0) {
					// 若存在 查到该信息
					PostageInfo postageInfo = postageInfoDao.selectPostageInfoByStatus(map);
					postageInfo.setPostage_info_status(2);
					map.put("postageInfo", postageInfo);
					// 将该信息的状态改为普通地址
					int status = postageInfoDao.updatePostageInfosByStatus(map);
				}
				int postageInfoCount = postageInfoDao.updatePostageInfo(map);
				if (postageInfoCount > 0) {
					info.put("status", "success");
					BaseDBUtils.commitAndClose();
					return info;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
				try {
					BaseDBUtils.rollbackAndClose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		return info;
	}

	@Override
	public Map<String, Object> modifyPostageInfosByStatus(Map<String, Object> map) {
		Map<String, Object> info = new LinkedHashMap<String, Object>();
		info.put("status", "failed");
		// 修改前查看修改的状态是否是3（ 默认收件地址）
		try {
			if (!ObjectUtils.isEmpty(map.get("postage_info_status")) && (int) map.get("postage_info_status") == 3) {
				// 开启事务
				BaseDBUtils.startTransaction();
				// 查询该买家是否存在默认收件地址
				int count = postageInfoDao.selectCountByStatus(map);
				if (count > 0) {
					// 若存在 查到该信息
					PostageInfo postageInfo = postageInfoDao.selectPostageInfoByStatus(map);
					postageInfo.setPostage_info_status(2);
					map.put("postageInfo", postageInfo);
					// 将该信息的状态改为普通地址
					int status = postageInfoDao.updatePostageInfosByStatus(map);
				}
				map.put("postageInfo", "");
				int postageInfoCount = postageInfoDao.updatePostageInfosByStatus(map);
				if (postageInfoCount > 0) {
					info.put("status", "success");
					BaseDBUtils.commitAndClose();
					return info;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
				try {
					BaseDBUtils.rollbackAndClose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		return info;
	}

	@Override
	public Map<String, Object> removePostageInfosByStatus(Map<String, Object> map) {
		Map<String, Object> info = new LinkedHashMap<String, Object>();
		info.put("status", "failed");
		int postageInfoCount;
		try {
			BaseDBUtils.startTransaction();
			postageInfoCount = postageInfoDao.updatePostageInfosByStatus(map);
			if (postageInfoCount > 0) {
				info.put("status", "success");
				BaseDBUtils.commitAndClose();
				return info;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
				try {
					BaseDBUtils.rollbackAndClose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}

		return info;
	}

}
