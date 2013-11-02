/*
 * 创建日期 2013-3-14
 * 
 * 成都天和软件公司
 * 电话：028-85425861
 * 传真：028-85425861-8008
 * 邮编：610041
 * 地址：成都市武侯区航空路6号丰德万瑞中心B座1001
 * 版权所有
 */
package com.wy.shopping.pojo;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Property;
import net.tsz.afinal.annotation.sqlite.Table;
import net.tsz.afinal.annotation.sqlite.Transient;

/**
 * 店面商家vo
 * @author WangYi
 * @createtime 2013-3-14 上午10:28:00
 * 最后修改时间 : 
 * 更新记录:
 */
@Table(name="HotelDetailVo")
public class HotelDetailVo {

	@Transient
	private static final long serialVersionUID = 3096402369780693417L;

	@Id(column = "ID")
	private int id;

	@Property(column = "SID")
	private int sid;
	
	/**酒店类型*/
	@Property(column="TYPE")
	private int type;
	
	/**店名*/
	@Property(column="NAME")
	private String name;
	
	/**店面地址*/
	@Property(column="ADDRESS")
	private String address;
	
	/**店面联系电话*/
	@Property(column="PHONENUM")
	private String phoneNum;

	/**店面图标*/
	@Property(column="URL")
	private String url;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public HotelDetailVo() {
		super();
	}

	public HotelDetailVo(int id, int sid, int type, String name,
			String address, String phoneNum, String url) {
		super();
		this.id = id;
		this.sid = sid;
		this.type = type;
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.url = url;
	}

	@Override
	public String toString() {
		return "HotelDetailVo [address=" + address + ", id=" + id + ", name="
				+ name + ", phoneNum=" + phoneNum + ", sid=" + sid + ", type="
				+ type + ", url=" + url + "]";
	}

}
