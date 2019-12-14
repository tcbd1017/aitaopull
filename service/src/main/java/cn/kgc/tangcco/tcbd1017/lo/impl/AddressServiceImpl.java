package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.AddressDao;
import cn.kgc.tangcco.tcbd1017.lo.AddressService;
import cn.kgc.tangcco.tcbd1017.lo.UserAndAddressDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.suiji.SuiJi;

 
public class AddressServiceImpl implements AddressService {

	UserAndAddressDao uaad=new UserAndAddressDaoImpl();
	AddressDao ad=new AddressDaoImpl();
	@Override
	public Map<String, Object> selectByUserId(Map<String, Object> map) {
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		
		List selectAddressByUserid = uaad.selectAddressByUserid(map);
		if(selectAddressByUserid!=null&&selectAddressByUserid.size()>0) {
			info.put("data", selectAddressByUserid);
			info.put("status", "success");
		}
		
		return info;
	}

	@Override
	public Map<String, Object> updateByAddressId(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		int updateAddressByAddressId = ad.updateAddressByAddressId(map);
		if(updateAddressByAddressId>0) {
			info.put("status", "success");
		}
		return info;
	}

	@Override
	public Map<String, Object> deleteByAddressId(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		int deleteAddressByAddressid = uaad.deleteAddressByAddressid(map);
		if(deleteAddressByAddressid>0) {
			info.put("status", "success");
		}
		return info;
	}

	@Override
	public Map<String, Object> addByUserId(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		String logistics_address_uuid = SuiJi.getRandomNumber(13);
		map.put("logistics_address_uuid", logistics_address_uuid);
		int addAddress = ad.addAddress(map);
		if(addAddress>0) {
			int addAddressidAndUserid = uaad.addAddressidAndUserid(map);
			if(addAddressidAndUserid>0) {
				info.put("status", "success");
			}
		}
		return info;
	}

}
