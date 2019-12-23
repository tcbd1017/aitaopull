package cn.kgc.tangcco.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 角色
 *
 */
public class Role {

	/**
	    * 角色id
	    */
	    private Integer roleId;

	    /**
	    * 角色名称
	    */
	    private String roleName;

	    public Role(Integer roleId) {
			super();
			this.roleId = roleId;
		}

}
