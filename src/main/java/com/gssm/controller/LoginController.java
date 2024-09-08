package com.gssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gssm.dao.AdminDaoImpl;
import com.gssm.dao.UserDaoImpl;
import com.gssm.dao.YuangongDaoImpl;
import com.gssm.entity.Admin;
import com.gssm.entity.User;
import com.gssm.entity.Yuangong;
import com.gssm.util.Pager;

@Controller
public class LoginController extends BaseController{

	@Autowired
	private AdminDaoImpl adminDao;
	
	public AdminDaoImpl getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDaoImpl adminDao) {
		this.adminDao = adminDao;
	}
	@Autowired
	private YuangongDaoImpl yuangongDao;

	public YuangongDaoImpl getYuangongDao() {
		return yuangongDao;
	}

	public void setYuangongDao(YuangongDaoImpl yuangongDao) {
		this.yuangongDao = yuangongDao;
	}
	
	@Autowired
	private UserDaoImpl userDao;

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	/**
	 * 登陆接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login.action")
	public String list(@RequestParam String userName, @RequestParam String userPw, @RequestParam Integer userType, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
			System.out.println("userType" + userType);
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (userType == 0)//
			{
				Admin admin = new Admin();
				admin.setUsername(userName);
				admin.setUserpwd(userPw);
				Pager<Admin> pagers = adminDao.findByEntity(admin);
				if (pagers.getTotal() == 0) {
					request.setAttribute("msg", "用户名或者密码错误!");
					return "login";
				} else {
					admin = (Admin) pagers.getDatas().get(0);
					session.setAttribute("userType", 0);
					session.setAttribute("admin", admin);
					return "admin/index";
				}
			}
			if (userType == 1)//用户登录
			{
				Yuangong user = new Yuangong();
				user.setLoginname(userName);
				user.setPwd(userPw);
				Pager<Yuangong> pagers = yuangongDao.findByEntity(user);
				if (pagers.getTotal() == 0) {
					request.setAttribute("msg", "用户名或者密码错误!");
					return "login";
				} else {
					user = (Yuangong) pagers.getDatas().get(0);
					session.setAttribute("userType", 1);
					session.setAttribute("yuangong", user);
					return "admin/index";
				}
			}
			if (userType == 2)//用户登录
			{
				User user = new User();
				user.setLoginname(userName);
				user.setPwd(userPw);
				Pager<User> pagers = userDao.findByEntity(user);
				if (pagers.getTotal() == 0) {
					request.setAttribute("msg", "用户名或者密码错误!");
					return "login";
				} else {
					user = (User) pagers.getDatas().get(0);
					session.setAttribute("userType", 2);
					session.setAttribute("user", user);
					return "admin/index";
				}
			}
			return "admin/index";
	}
}