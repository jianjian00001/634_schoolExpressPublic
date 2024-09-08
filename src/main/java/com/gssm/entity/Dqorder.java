package com.gssm.entity;

/**
 * 代取件
 */
public class Dqorder {

	private Integer id; // ID
	private String orderno; // 快递编号
	private Integer userid; // 用户ID
	private String content; // 快递描述
	private String username; // 用户名
	private String userphone; // 联系电话
	private String shijian; // 时间
	private Integer status; // 状态
	private String yname; // 送物品人员名称
	private String yphone; // 送物品人员电话
	private Integer yid; // 雇员ID
	private String pingjia; // 客户评价
	private String pingjia2; // 快递员评价

	private Integer pingfen;
	private Integer pingfen2;

	public Integer getPingfen() {
		return pingfen;
	}

	public void setPingfen(Integer pingfen) {
		this.pingfen = pingfen;
	}

	public Integer getPingfen2() {
		return pingfen2;
	}

	public void setPingfen2(Integer pingfen2) {
		this.pingfen2 = pingfen2;
	}

	public Dqorder() {
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public String getYname() {
		return yname;
	}

	public void setYname(String yname) {
		this.yname = yname;
	}

	public String getYphone() {
		return yphone;
	}

	public void setYphone(String yphone) {
		this.yphone = yphone;
	}

	public Integer getYid() {
		return yid;
	}

	public void setYid(Integer yid) {
		this.yid = yid;
	}

	public String getPingjia() {
		return pingjia;
	}

	public void setPingjia(String pingjia) {
		this.pingjia = pingjia;
	}

	public String getPingjia2() {
		return pingjia2;
	}

	public void setPingjia2(String pingjia2) {
		this.pingjia2 = pingjia2;
	}

}