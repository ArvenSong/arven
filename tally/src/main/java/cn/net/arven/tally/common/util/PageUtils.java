/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.net.arven.tally.common.util;


import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月4日 下午12:59:00
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	//状态码0
	private int code;

	//message
	private String msg ;

	//总记录数
	private int count;

	//每页记录数
	private int limit;
	//总页数
	private int totalPage;
	//当前页数
	private int page;
	//列表数据
	private List<?> data;

	
	/**
	 * 分页
	 * @param data        列表数据
	 * @param count  总记录数
	 * @param limit    每页记录数
	 * @param page    当前页数
	 */
	public PageUtils(List<?> data, int count, int limit, int page) {
		this.data = data;
		this.count = count;
		this.limit = limit;
		this.page = page;
		this.totalPage = (int)Math.ceil((double)count/limit);
	}
	public PageUtils(int page,int limit){
		this.page = page;
		this.limit = limit;
	}

	public static PageUtils getInstance(){
		return new PageUtils(1,10);
	}

	/**
	 * 分页
	 * @param page
	 */
	public PageUtils(IPage<?> page) {
		this.data = page.getRecords();
		this.count = (int) page.getTotal();
		this.limit = (int) page.getSize();
		this.page = (int) page.getCurrent();
		this.totalPage = (int) page.getPages();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}
