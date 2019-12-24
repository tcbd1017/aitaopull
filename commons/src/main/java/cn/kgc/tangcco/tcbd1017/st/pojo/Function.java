package cn.kgc.tangcco.tcbd1017.st.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 功能进货记录
 *
 */
public class Function {

	/**
	    * 功能表id
	    */
	    private Integer functionId;

	    /**
	    * 功能名称
	    */
	    private String functionName;

	    /**
	    * 功能路径
	    */
	    private String functionPath;

	    /**
	    * 外键角色id
	    */
//	    private Integer functionRoleId;
	    private Role role;

}
