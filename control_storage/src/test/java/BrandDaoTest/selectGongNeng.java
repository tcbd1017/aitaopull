package BrandDaoTest;
/**
* @author 作者 :牛伟
* @version 创建时间：2019年12月18日 下午8:21:13
* 类说明
*/

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import cn.kgc.tangcco.dao.impl.FunctionDaoImpl;
import cn.kgc.tangcco.pojo.Function;

public class selectGongNeng {
	@Test
	public void selectGongNeng() throws SQLException {
		FunctionDaoImpl functionDaoImpl = new FunctionDaoImpl();
		List<Function> list = functionDaoImpl.selectGongNeng(5);
		for (Function function : list) {
			System.out.println(function);
		}
	}
}
