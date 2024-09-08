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

import com.gssm.dao.YuangongDaoImpl;
import com.gssm.entity.Yuangong;
import com.gssm.util.JsonUtil2;
import com.gssm.util.Pager;

@Controller
@RequestMapping(value = "/yuangong")
public class YuangongController extends BaseController{
	/**
	 * 依赖注入 start dao/===
	 */
	@Autowired
	private YuangongDaoImpl yuangongDao;

	public YuangongDaoImpl getYuangongDao() {
		return yuangongDao;
	}

	public void setYuangongDao(YuangongDaoImpl yuangongDao) {
		this.yuangongDao = yuangongDao;
	}

	// --------------------------------------- 华丽分割线 ------------------------------//

	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list.action")
	public String list(Yuangong yuangong, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pager<Yuangong> pagers = yuangongDao.findByEntity(yuangong);
		model.addAttribute("pagers", pagers);
		return "admin/yuangong/yuangong_list";
	}

	/**
	 * 跳转添加页面
	 */
	@RequestMapping(value = "/add.action")
	public String add(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "admin/yuangong/yuangong_add";
	}

	/**
	 * 跳至修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.action")
	public String edit(Integer id, Model model) {
		Yuangong bean = yuangongDao.load(id);
		model.addAttribute("bean", bean);
		return "admin/yuangong/yuangong_edit";
	}
	
	/**
	 * 添加信息保存
	 */
	@RequestMapping(value = "/save.action")
	public String save(Yuangong yuangong, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		yuangongDao.insert(yuangong);
		return "redirect:/yuangong/list.action";
	}
	
	
	/**
	 * 保存修改信息
	 */
	@RequestMapping(value = "/update.action")
	public String update(Yuangong yuangong, Model model, HttpServletRequest request, HttpServletResponse response) {
		yuangongDao.update(yuangong);
		return "redirect:/yuangong/list.action";
	}

	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/del.action")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 真正删除
		yuangongDao.deleteById(id);
		// 状态删除
		// Yuangong yuangong = yuangongDao.load(id);
		// load.setDel(1);
		// yuangongDao.update(load);
		return "redirect:/yuangong/list.action";
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
	public String findByObjByEntity(Yuangong yuangong, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 分页查询
		Pager<Yuangong> pagers = yuangongDao.findByEntity(yuangong);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", yuangong);
		return jsonObject.toString();
	}
}