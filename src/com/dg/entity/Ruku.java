package com.dg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RUKU")
public class Ruku {
	@Id
	@Column(length=32)
	private String rid;/*�����*/
	
	@Column(length=32)
	private String did;/*ҩƷ���*/
	
	@Column(length=32)
	private String rdname;/*ҩƷ����*/
	
	@Column(length=32)
	private String dvender;/*��������*/
	
	@Column(length=32)
	private String date_begin;/*��������*/
	
	@Column(length=32)
	private String date_end;/*ʧЧ����*/
	
	@Column(length=32)
	private String rdate;/*���ʱ��*/
	
	@Column(length=32)
	private String rtype;/*��������*/
	
	@Column(length=32)
	private String rnumber;/*�������*/
	
	@Column(length=32)
	private String rprice;/*�������*/
	
	@Column(length=32)
	private String realname;/*�����Ա����*/
	
	public Ruku(){
		
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getRdname() {
		return rdname;
	}

	public void setRdname(String rdname) {
		this.rdname = rdname;
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

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getRnumber() {
		return rnumber;
	}

	public void setRnumber(String rnumber) {
		this.rnumber = rnumber;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public String getRprice() {
		return rprice;
	}

	public void setRprice(String rprice) {
		this.rprice = rprice;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}


}
