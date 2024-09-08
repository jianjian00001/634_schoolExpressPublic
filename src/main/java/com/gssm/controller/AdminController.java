package com.gssm.controller;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gssm.dao.AdminDaoImpl;
import com.gssm.entity.Admin;
import com.gssm.util.JsonUtil2;
import com.gssm.util.Pager;
/**
 * 
 * @author thinkpad
 * @version 1.0
 * @deprecated 管理员操作管理
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController{
	/**
	 * 依赖注入 start dao/===
	 */
	@Autowired
	private AdminDaoImpl adminDao;

	public AdminDaoImpl getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDaoImpl adminDao) {
		this.adminDao = adminDao;
	}

	// --------------------------------------- 华丽分割线 ------------------------------//

	/**
	 * @deprecated 分页查询 返回list对象(通过对象)
	 * @deprecated list()
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list.action")
	public String list(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pager<Admin> pagers = adminDao.findByEntity(admin);
		model.addAttribute("pagers", pagers);
		return "admin/admin/admin_list";
	}

	/**
	 *  * @author thinkpad
 	 *  @version 1.0
      * @deprecated 跳转添加页面
	 */
	@RequestMapping(value = "/add.action")
	public String add(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "admin/admin/admin_add";
	}

	/**
	 * 
	 * 	@author thinkpad
 	 *  @version 1.0
      * @deprecated 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/edit.action")
	public String edit(Integer id, Model model) {
		Admin bean = adminDao.load(id);
		model.addAttribute("bean", bean);
		return "admin/admin/admin_edit";
	}
	
	/**
	 * 	@author thinkpad
 	 *  @version 1.0
      * @deprecated 添加信息保存
	 */
	@RequestMapping(value = "/save.action")
	public String save(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		adminDao.insert(admin);
		return "redirect:/admin/list.action";
	}
	
	
	/**
	 * 	@author thinkpad
 	 *  @version 1.0
      * @deprecated 保存修改信息
	 */
	@RequestMapping(value = "/update.action")
	public String update(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		adminDao.update(admin);
		return "redirect:/admin/list.action";
	}

	/**
	 * 	@author thinkpad
 	 *  @version 1.0
      * @deprecated 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/del.action")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		Admin bean = adminDao.load(id);
		if(!"admin".equals(bean.getUsername())){
			// 真正删除
			adminDao.deleteById(id);
		}
		// 状态删除
		// Admin admin = adminDao.load(id);
		// load.setDel(1);
		// adminDao.update(load);
		return "redirect:/admin/list.action";
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
	public String findByObjByEntity(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 分页查询
		Pager<Admin> pagers = adminDao.findByEntity(admin);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", admin);
		return jsonObject.toString();
	}
}

