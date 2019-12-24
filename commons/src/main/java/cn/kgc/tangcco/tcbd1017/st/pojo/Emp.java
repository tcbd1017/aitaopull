package cn.kgc.tangcco.tcbd1017.st.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 员工类
 * 
 *
 */
public class Emp {

	/**
	    * 员工id
	    */
	    private Integer empId;

	    /**
	    * 账号
	    */
	    private String empAccount;

	    /**
	    * 密码
	    */
	    private String empPassword;

	    /**
	    * 员工姓名
	    */
	    private String empName;

	    /**
	    * 员工手机号
	    */
	    private String empPhone;

	    /**
	    * 员工性别 1男  2女
	    */
	    private Integer empGender;

	    /**
	    * 状态 1存在  2不存在
	    */
	    private Integer empFlag;

	    /**
	    * 角色id（外键）
	    */
//	    private Integer empRoleId;
	    private Role role;
	    
	    private Function f;
	    
}