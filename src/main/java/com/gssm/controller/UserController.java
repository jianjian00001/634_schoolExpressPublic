package com.gssm.controller;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gssm.dao.UserDaoImpl;
import com.gssm.entity.User;
import com.gssm.util.JsonUtil2;
import com.gssm.util.Pager;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
	/**
	 * 依赖注入 start dao/===
	 */
	@Autowired
	private UserDaoImpl userDao;

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	// --------------------------------------- 华丽分割线 ------------------------------//

	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list.action")
	public String list(User user, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pager<User> pagers = userDao.findByEntity(user);
		model.addAttribute("pagers", pagers);
		return "admin/user/user_list";
	}

	/**
	 * 跳转添加页面
	 */
	@RequestMapping(value = "/add.action")
	public String add(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "admin/user/user_add";
	}

	/**
	 * 跳至修改页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/edit.action")
	public String edit(Integer id, Model model) {
		User bean = userDao.load(id);
		model.addAttribute("bean", bean);
		return "admin/user/user_edit";
	}

	/**
	 * 添加信息保存
	 */
	@RequestMapping(value = "/save.action")
	public String save(User user, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		userDao.insert(user);
		return "redirect:/user/list.action";
	}

	/**
	 * 添加信息保存
	 */
	@RequestMapping(value = "/reg.action")
	public String reg(User user, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		userDao.insert(user);
		return "/login";
	}


	/**
	 * 保存修改信息
	 */
	@RequestMapping(value = "/update.action")
	public String update(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userDao.update(user);
		return "redirect:/user/list.action";
	}

	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/del.action")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 真正删除
		userDao.deleteById(id);
		// 状态删除
		// User user = userDao.load(id);
		// load.setDel(1);
		// userDao.update(load);
		return "redirect:/user/list.action";
	}

		// --------------------------------------- 华丽分割线------------------------------------------------------//

	/**
	 * 分页查询 返回list json(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String findByObjByEntity(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 分页查询
		Pager<User> pagers = userDao.findByEntity(user);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", user);
		return jsonObject.toString();
	}


	// --------------------------------------- 华丽分割线------------------------------	//
}
