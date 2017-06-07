package com.dg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DRUG")
public class Drug {/*药品信息表*/
	@Id
	@Column(length=32)
	private String did;/*药品编号*/
	
	@Column(length=32)
	private String dname;/*药品名称*/
	
	@Column(length=32)
	private String dcategory;/*药品类别*/
	
	@Column(length=32)
	private String dspec;/*药品规格*/
	
	@Column(length=32)
	private String dvender;/*生产厂家*/
	
	@Column(length=32)
	private String date_begin;/*生产日期*/
	
	@Column(length=32)
	private String date_end;/*失效日期*/
	
	@Column(length=32)
	private String dprice;/*进货单价*/

	@Column(length=32)
	private String retail_price;/*零售价*/
	public Drug() {
		
		// TODO Auto-generated constructor stub
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDcategory() {
		return dcategory;
	}

	public void setDcategory(String dcategory) {
		this.dcategory = dcategory;
	}

	public String getDspec() {
		return dspec;
	}

	public void setDspec(String dspec) {
		this.dspec = dspec;
	}

	public String getDvender() {
		return dvender;
	}

	public void setDvender(String dvender) {
		this.dvender = dvender;
	}

	public String getDate_begin() {
		return date_begin;
	}

	public void setDate_begin(String date_begin) {
		this.date_begin = date_begin;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}

	public String getDprice() {
		return dprice;
	}

	public void setDprice(String dprice) {
		this.dprice = dprice;
	}

	public String getRetail_price() {
		return retail_price;
	}

	public void setRetail_price(String retail_price) {
		this.retail_price = retail_price;
	}
	
}
