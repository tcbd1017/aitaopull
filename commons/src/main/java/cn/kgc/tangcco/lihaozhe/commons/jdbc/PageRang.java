package cn.kgc.tangcco.lihaozhe.commons.jdbc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRang{
	// 当前页码号
	private int pageNumber;
	// 每页记录数
	private int pageSize;
}
