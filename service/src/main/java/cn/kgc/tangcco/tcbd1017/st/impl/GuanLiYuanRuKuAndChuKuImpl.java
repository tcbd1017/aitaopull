package cn.kgc.tangcco.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.dao.impl.ChuKuDaoImpl;
import cn.kgc.tangcco.dao.impl.GogoodSrecordDaoImpl;
import cn.kgc.tangcco.dao.impl.GoodsDaoImpl;
import cn.kgc.tangcco.dao.impl.JianCeDaoImpl;
import cn.kgc.tangcco.dao.impl.ShopDaoImpl;
import cn.kgc.tangcco.dao.impl.comeGoodsRecordDaoImpl;
import cn.kgc.tangcco.dao.impl.warehouse_shopDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.pojo.Chuku;
import cn.kgc.tangcco.pojo.Comegoodsrecord;
import cn.kgc.tangcco.pojo.Gogoodsrecord;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.pojo.RecordMonthAndSum;
import cn.kgc.tangcco.pojo.SelectCountByType;
import cn.kgc.tangcco.pojo.Shop;
import cn.kgc.tangcco.pojo.WarehouseShop;
import cn.kgc.tangcco.service.GuanLiYuanRuKuAndChuKu;

public class GuanLiYuanRuKuAndChuKuImpl implements GuanLiYuanRuKuAndChuKu {

	
	
	private  ChuKuDaoImpl  chuku =new ChuKuDaoImpl();
	private JianCeDaoImpl jiance = new JianCeDaoImpl();
	private GoodsDaoImpl goods = new GoodsDaoImpl();
	private comeGoodsRecordDaoImpl come = new comeGoodsRecordDaoImpl();
	private warehouse_shopDaoImpl ws = new warehouse_shopDaoImpl();
	private ShopDaoImpl shop = new ShopDaoImpl();
	private GogoodSrecordDaoImpl go = new GogoodSrecordDaoImpl();
	
	
	@Override   //查看入库状态表
	public Map<String, Object> ChaKanRuKuBiao(Map<String, Object> map) {
		// map需要账号
		// 如果是 商家 需要商家uuid
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			BaseDBUtils.startTransaction();
			map2.put("status", "failed");
			map2.put("data", null);
			map2.put("code", 0);
			map2.put("msg", "");
			map2.put("count", 0);
			List<Jiance> selectZhuCe = jiance.selectZhuCe(map);
			if (selectZhuCe.size() > 0 && selectZhuCe != null) {
				map2.put("status", "success");
				map2.put("data", selectZhuCe);
				map2.put("count", selectZhuCe.size());
			}
			BaseDBUtils.closeAll();
		} catch (Exception e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map2;
	}

	@Override
	// 传入将要入库的货物信息
	public Map<String, Object> UpdateRuKuZhuangTai(Map<String, Object> map2) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if((int)map2.get("zhuangtai")==1) {
			
			 int xiuGaiZhuangTaiRuK = jiance.XiuGaiZhuangTaiRuKu(map2);
			 
			map.put("status", "success");
			System.out.println("入库跳出除了状态不做修改");
			return map;
		}
		try {
			
			map.put("status", "failed");
			map.put("data", null);
			boolean selectHuoWu = goods.selectHuoWu(map2);
			System.out.println(selectHuoWu);
			try {
				if (selectHuoWu == true) {
					int insert = goods.insert(map2);
					if (insert > 0) {
						System.out.println("插入goods表成功");
					}
				} else {
					int update = goods.addUpdateShengYuCount(map2);
					System.out.println(update);
					if (update > 0) {
						System.out.println("修改goods库存成功");
					}
				}

			} catch (SQLException e) {
				System.out.println(1);
				
				e.printStackTrace();
			}
			try {
				int insertJiLu = come.insertJiLu(map2);
				if (insertJiLu > 0) {
					System.out.println("插入记录表成功");
				}

			} catch (SQLException e) {
				
				System.out.println(2);
				e.printStackTrace();
			}
			try {
				int updateCangKuRongLiang = ws.updateJianCangKuRongLiang(map2);
				if (updateCangKuRongLiang > 0) {
					System.out.println("修改容量成功");
				}

			} catch (Exception e) {
				
				System.out.println(3);
				e.printStackTrace();
			}
			int xiuGaiZhuangTaiRuKu = 0;
			
			System.out.println(map2.get("zhuangtai"));
			System.out.println(map2.get("jiance_rukujiluuuid"));
			try {
				xiuGaiZhuangTaiRuKu =  jiance.XiuGaiZhuangTaiRuKu(map2);;

			} catch (Exception e) {
				
				System.out.println(4);
				e.printStackTrace();
			}
			if (xiuGaiZhuangTaiRuKu > 0) {
				System.out.println("修改状态成功");
				map.put("status", "success");
			}
			
		} catch (SQLException e) {
			System.out.println("111111111");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> ChaKanAllCangKuUUid(Map<String,Object>map) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			BaseDBUtils.startTransaction();
			List<WarehouseShop> chakanxiangqingAllCangKu = ws.chakanxiangqingAllCangKu(map);

			map2.put("status", "failed");
			map2.put("data", null);
			if (chakanxiangqingAllCangKu != null) {
				map2.put("status", "success");
				map2.put("data", chakanxiangqingAllCangKu);
			}
			BaseDBUtils.commitAndClose();
		} catch (Exception e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return map2;
	}

	@Override
	public Map<String, Object> ChaKanShopJianLue() {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			BaseDBUtils.startTransaction();
			map2.put("status", "failed");
			map2.put("data", null);
			List<Shop> selectShop = shop.selectShop();
			if (selectShop != null && selectShop.size() > 0) {
				map2.put("status", "success");
				map2.put("data", selectShop);
			}
		} catch (Exception e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map2;
	}

	@Override
	public Map<String, Object> ChaXunCangKuJianLue(Map<String,Object>map) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			BaseDBUtils.startTransaction();
			map2.put("status", "failed");
			map2.put("data", null);
			List<WarehouseShop> chakanxiangqingAllCangKu = ws.chakanxiangqingAllCangKu(map);
			if(chakanxiangqingAllCangKu!=null && chakanxiangqingAllCangKu.size()>0) {
				map2.put("status", "success");
				map2.put("data", chakanxiangqingAllCangKu);
			}
		
		} catch (

		Exception e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map2;
	}

	@Override
	public Map<String, Object> ChaXunYueFenYuMeiYuChuHuoLiang() {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			BaseDBUtils.startTransaction();
			map2.put("status", "failed");
			map2.put("data", null);
			List<RecordMonthAndSum> fenLeiChaXun = go.FenLeiChaXun();
			if(fenLeiChaXun!=null && fenLeiChaXun.size()>0) {
				map2.put("status", "success");
				map2.put("data", fenLeiChaXun);
			}
		} catch (

		Exception e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map2;
	}
	
	@Override
	public Map<String, Object> ChaXunLeiBieYuLeiBieChuHuoLiang() {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			BaseDBUtils.startTransaction();
			map2.put("status", "failed");
			map2.put("data", null);
			  List<SelectCountByType> selectRChuKuJiLuBingZhuangTu = go.selectRChuKuJiLuBingZhuangTu();
			if(selectRChuKuJiLuBingZhuangTu!=null && selectRChuKuJiLuBingZhuangTu.size()>0) {
				map2.put("status", "success");
				map2.put("data", selectRChuKuJiLuBingZhuangTu);
			}
		} catch ( Exception e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map2;
	}
	
	
	@Override
	//查询出库表
	public Map<String, Object> ChaXunSuoYouChuHuo(Map<String, Object> map) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			BaseDBUtils.startTransaction();
			
			map2.put("data", null);
			map2.put("code", 0);
			map2.put("msg", "");
			map2.put("count", 0);
			List<Chuku> selectChuKu = chuku.selectChuKu(map);
			if(selectChuKu!=null && selectChuKu.size()>0) {
				
				map2.put("data", selectChuKu);
				map2.put("count", selectChuKu.size());
			}
		} catch (Exception e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map2;
	}

	@Override
	public Map<String, Object> XiuGaiChuKuZhuangTai(Map<String, Object> map2) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if((int)map2.get("zhuangtai")==2) {
			int  a= chuku.XiuGaiZhuangTaiChuKu(map2);
			map.put("status", "success");
			System.out.println("库存不足，只改状态");
			return map;
		}
		
		
		
		try {
			BaseDBUtils.startTransaction();
			map.put("status", "failed");
			map.put("data", null);
			
				int addUpdateShengYuCount = goods.jianUpdateShengYuCount(map2);
				if (addUpdateShengYuCount>0) {
					System.out.println("修改货物库存成功");
				}
				int insertJiLuChuKu = go.insertJiLuChuKu(map2);
				if (insertJiLuChuKu>0) {
					System.out.println("插入出库记录表成功");
				}
				int updateaddCangKuRongLiang = ws.updateaddCangKuRongLiang(map2);
				if(updateaddCangKuRongLiang>0) {
					System.out.println("修改仓库剩余容量成功");
				}
			
			int xiuGaiZhuangTaiChuKu = 0;
			try {
				xiuGaiZhuangTaiChuKu = chuku.XiuGaiZhuangTaiChuKu(map2);
			} catch (Exception e) {
				BaseDBUtils.rollbackAndClose();
				System.out.println(4);
				e.printStackTrace();
			}
			if (xiuGaiZhuangTaiChuKu > 0) {
				map.put("status", "success");
			}
			BaseDBUtils.commitAndClose();
		} catch (SQLException e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				System.out.println(5);
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map;
	}

	@Override//查询月份 与 每月的 进货量
	public Map<String, Object> ChaXunJinHuoZheXianTu() {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			map2.put("status", "failed");
			map2.put("data",null);
			 List<RecordMonthAndSum> selectRuKuJiLu2 = come.selectRuKuJiLu2();
			if (selectRuKuJiLu2.size()>0) {
				map2.put("status", "success");
				map2.put("data", selectRuKuJiLu2);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return map2;
	}

	@Override
	public Map<String, Object> ChaZunJinHuoBingZhuangTu() {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			map2.put("status", "failed");
			map2.put("data",null);
			 List<SelectCountByType> selectRuKuJiLu2 = come.selectRuKuJiLuBingZhuangTu();
			if (selectRuKuJiLu2.size()>0) {
				map2.put("status", "success");
				map2.put("data", selectRuKuJiLu2);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return map2;
	}

	@Override
	public Map<String, Object> ChaJinHuoJiLuBiao(Map<String, Object> map) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			map2.put("status", "failed");
			map2.put("data",null);
			map2.put("msg", "");
			map2.put("count", 0);
			map2.put("code", 0);
			Map<String, Object> selectRuKuJiLu = come.selectRuKuJiLu(map);
			List<Comegoodsrecord>list=(List<Comegoodsrecord>) selectRuKuJiLu.get("list");
			if (list.size()>0) {
				map2.put("status", "success");
				map2.put("data", list);
				map2.put("count", list.size());
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return map2;
	}

	@Override
	public Map<String, Object> ChaChuHuoJiLuBiao(Map<String, Object> map) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			map2.put("status", "failed");
			map2.put("data",null);
			map2.put("msg", "");
			map2.put("count", 0);
			map2.put("code", 0);
			Map<String, Object> selectRuKuJiLu = go.selectChuKuJiLu(map);
			List<Gogoodsrecord>list=(List<Gogoodsrecord>) selectRuKuJiLu.get("list");
			if (list.size()>0) {
				map2.put("status", "success");
				map2.put("data", list);
				map2.put("count", list.size());
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return map2;
	}
	
	
	
}
