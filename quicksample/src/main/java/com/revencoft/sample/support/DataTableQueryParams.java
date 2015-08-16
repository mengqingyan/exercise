package com.revencoft.sample.support;

import java.util.List;


public class DataTableQueryParams {
	
	private String sEcho;//原样返回
	private int iDisplayStart;//分页起始位置
	private int iDisplayLength;//页面显示记录数
	private int iSortCol_0;//排序的列
	private String sSortDir_0;//asc;desc
	
	private List<String> sortableColumns;
	
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public int getiSortCol_0() {
		return iSortCol_0;
	}
	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}
	public String getsSortDir_0() {
		return sSortDir_0;
	}
	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}
	public List<String> getSortableColumns() {
		return sortableColumns;
	}
	public void setSortableColumns(List<String> sortableColumns) {
		this.sortableColumns = sortableColumns;
	}
	
	public String getSortColumn() {
		if(sortableColumns != null) {
			return sortableColumns.get(iSortCol_0);
		}
		return null;
	}
}
