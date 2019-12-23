package cn.kgc.tangcco.lihaozhe.commons.pagerange;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Pagger {
	//每页记录数
	private int pageSize;
	//总纪录数
	private long rowCount;
	//总页数
	private int pageCount;
	//当前页索引
	private int pageIndex;
	//当前页码号
	private int pageNum;
	//是否首页
	private boolean firstPage;
	//是否末页
	private boolean lastPage;
	public Pagger(int pageSize, long rowCount, int pageNum) {
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.pageNum = pageNum;
		this.pageCount = (int)Math.ceil(rowCount * 1.0 / pageSize);
	}
	
}
