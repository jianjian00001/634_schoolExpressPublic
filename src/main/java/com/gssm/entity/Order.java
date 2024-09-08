package com.gssm.entity;

/**
 * 快递订单
 */
public class Order {


    private Integer id;  // ID
    private String orderno;  // 快递编号
    private Integer yuangongid;  // 用户ID
    private String content;  // 快递描述
    private String username;  // 客户名
    private String userphone;  // 客户联系电话
    private String shijian;  // 时间
    private Integer status;  // 状态
	

	public Order() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public Integer getYuangongid() {
		return yuangongid;
	}

	public void setYuangongid(Integer yuangongid) {
		this.yuangongid = yuangongid;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
		
}