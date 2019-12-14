package cn.kgc.tangcco.tcbd1017.lo.commons.jdbc;

 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRang{
	// 当前页码�?
	private int pageNumber;
	// 每页记录�?
	private int pageSize;
}
