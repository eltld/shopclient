/*
 * 创建日期 2013-3-13
 * 
 * 成都天和软件公司
 * 电话：028-85425861
 * 传真：028-85425861-8008
 * 邮编：610041
 * 地址：成都市武侯区航空路6号丰德万瑞中心B座1001
 * 版权所有
 */
package com.wy.shopping.pojo;

import net.tsz.afinal.annotation.sqlite.Property;
import net.tsz.afinal.annotation.sqlite.Table;
import net.tsz.afinal.annotation.sqlite.Transient;

/**
 * 订购的菜单vo
 * @author WangYi
 * @createtime 2013-3-13 上午11:48:14 最后修改时间 : 更新记录:
 */
@Table(name="FoodsDetailVo")
public class FoodsDetailVo  {
	
	@Transient
	private static final long serialVersionUID = -4127136376453409760L;
	
	@Property(column="ID")
	private int id;
	
	/**所属酒店的id*/
	@Property(column="PARENTID")
	private  Long parentId;
	
	/** 菜单图标 */
	@Property(column="NAME")
	private String name;

	/** 菜品对应的图片URL地址 */
	@Property(column="IMAGEURL")
	private String imageUrl;

	/** 价格 */
	@Property(column="PRICE")
	private float price;

	/** 介绍 */
	@Property(column="INTRODUCE")
	private String introduce;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FoodsDetailVo() {
		super();
	}
	
	public FoodsDetailVo(String name, String imageUrl, float price,
			String introduce) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.price = price;
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "FoodsDetailVo [ id=" + id
				+ ", imageUrl=" + imageUrl + ", introduce=" + introduce
				+ ", name=" + name + ",  price=" + price
				+ "]";
	}
}
