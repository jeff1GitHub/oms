package com.og.oms.common;

/**
 * @author 研发 Oscar 2017-11-25
 */
public class Response {

	/**
	 * 客服任务数量
	 */
	private Integer csCount;
	
	/**
	 * 运维任务数量
	 */
	private Integer opCount;

	public Response(Integer csCount, Integer opCount) {
		this.csCount = csCount;
		this.opCount = opCount;
	}

	public Integer getCsCount() {
		return csCount;
	}

	public void setCsCount(Integer csCount) {
		this.csCount = csCount;
	}

	public Integer getOpCount() {
		return opCount;
	}

	public void setOpCount(Integer opCount) {
		this.opCount = opCount;
	}
}