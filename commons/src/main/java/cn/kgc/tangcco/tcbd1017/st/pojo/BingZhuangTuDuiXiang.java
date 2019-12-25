package cn.kgc.tangcco.tcbd1017.st.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZHOUxq<br>;
 * @version v1.0<br>
 * @创建时间：2019年12月18日  上午11:22:02<br>
 * @类描述：	饼状图对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BingZhuangTuDuiXiang {
	/**
	 * 仓库类型名
	 */
	private String name;
	/**
	 * 卖出仓库总个数
	 */
	private int count;
}
