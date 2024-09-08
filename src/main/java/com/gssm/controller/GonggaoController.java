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

import com.gssm.dao.GonggaoDaoImpl;
import com.gssm.entity.Gonggao;
import com.gssm.util.JsonUtil2;
import com.gssm.util.Pager;

@Controller
@RequestMapping(value = "/gonggao")
public class GonggaoController extends BaseController{
	/**
	 * 依赖注入 start dao/===
	 */
	@Autowired
	private GonggaoDaoImpl gonggaoDao;

	public GonggaoDaoImpl getGonggaoDao() {
		return gonggaoDao;
	}

	public void setGonggaoDao(GonggaoDaoImpl gonggaoDao) {
		this.gonggaoDao = gonggaoDao;
	}

	// --------------------------------------- 华丽分割线 ------------------------------//

	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list.action")
	public String list(Gonggao gonggao, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pager<Gonggao> pagers = gonggaoDao.findByEntity(gonggao);
		model.addAttribute("pagers", pagers);
		return "admin/gonggao/gonggao_list";
	}

	/**
	 * 跳转添加页面
	 */
	@RequestMapping(value = "/add.action")
	public String add(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "admin/gonggao/gonggao_add";
	}

	/**
	 * 跳至修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.action")
	public String edit(Integer id, Model model) {
		Gonggao bean = gonggaoDao.load(id);
		model.addAttribute("bean", bean);
		return "admin/gonggao/gonggao_edit";
	}
	
	/**
	 * 添加信息保存
	 */
	@RequestMapping(value = "/save.action")
	public String save(Gonggao gonggao, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		gonggaoDao.insert(gonggao);
		return "redirect:/gonggao/list.action";
	}
	
	
	/**
	 * 保存修改信息
	 */
	@RequestMapping(value = "/update.action")
	public String update(Gonggao gonggao, Model model, HttpServletRequest request, HttpServletResponse response) {
		gonggaoDao.update(gonggao);
		return "redirect:/gonggao/list.action";
	}

	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/del.action")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 真正删除
		gonggaoDao.deleteById(id);
		// 状态删除
		// Gonggao gonggao = gonggaoDao.load(id);
		// load.setDel(1);
		// gonggaoDao.update(load);
		return "redirect:/gonggao/list.action";
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
	public String findByObjByEntity(Gonggao gonggao, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 分页查询
		Pager<Gonggao> pagers = gonggaoDao.findByEntity(gonggao);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", gonggao);
		return jsonObject.toString();
	}


	// --------------------------------------- 华丽分割线------------------------------	// 
}