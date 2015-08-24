package com.revencoft.sample.support.query;

import java.util.List;

/**
 * 封装databable分页相关参数
 * @author mengqingyan
 * @version
 */
public class DataTableQueryParams {
	
	private String sEcho;//原样返回
	private int iDisplayStart = -1;//分页起始位置
	private int iDisplayLength = -1;//页面显示记录数
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iDisplayLength;
		result = prime * result + iDisplayStart;
		result = prime * result + iSortCol_0;
		result = prime * result + ((sEcho == null) ? 0 : sEcho.hashCode());
		result = prime * result
				+ ((sSortDir_0 == null) ? 0 : sSortDir_0.hashCode());
		result = prime * result
				+ ((sortableColumns == null) ? 0 : sortableColumns.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataTableQueryParams other = (DataTableQueryParams) obj;
		if (iDisplayLength != other.iDisplayLength)
			return false;
		if (iDisplayStart != other.iDisplayStart)
			return false;
		if (iSortCol_0 != other.iSortCol_0)
			return false;
		if (sEcho == null) {
			if (other.sEcho != null)
				return false;
		} else if (!sEcho.equals(other.sEcho))
			return false;
		if (sSortDir_0 == null) {
			if (other.sSortDir_0 != null)
				return false;
		} else if (!sSortDir_0.equals(other.sSortDir_0))
			return false;
		if (sortableColumns == null) {
			if (other.sortableColumns != null)
				return false;
		} else if (!sortableColumns.equals(other.sortableColumns))
			return false;
		return true;
	}
	
	
}
