package com.dg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CHUKU")
public class Chuku {
	@Id
	@Column(length=32)
	private String cid;/*出库编号*/
	
	@Column(length=32)
	private String did;/*药品编号*/

	@Column(length=32)
	private String cdate;/*出库时间*/
	
	@Column(length=32)
	private String ctype;/*操作类型*/
	
	@Column(length=32)
	private String cnumber;/*出库数量*/
	
	@Column(length=32)
	private String cprice;/*销售金额*/
	
	@Column(length=32)
	private String realname;/*出库人姓名*/

	public Chuku() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	
	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}
	
	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getCnumber() {
		return cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCprice() {
		return cprice;
	}

	public void setCprice(String cprice) {
		this.cprice = cprice;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	
}
