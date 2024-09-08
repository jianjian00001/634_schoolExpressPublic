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

import com.gssm.dao.DqorderDaoImpl;
import com.gssm.entity.Dqorder;
import com.gssm.entity.Order;
import com.gssm.util.JsonUtil2;
import com.gssm.util.Pager;

@Controller
@RequestMapping(value = "/dqorder")
public class DqorderController extends BaseController{
	/**
	 * 依赖注入 start dao/===
	 */
	@Autowired
	private DqorderDaoImpl dqorderDao;

	public DqorderDaoImpl getDqorderDao() {
		return dqorderDao;
	}

	public void setDqorderDao(DqorderDaoImpl dqorderDao) {
		this.dqorderDao = dqorderDao;
	}

	// --------------------------------------- 华丽分割线 ------------------------------//

	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list.action")
	public String list(Dqorder dqorder, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pager<Dqorder> pagers = dqorderDao.findByEntity(dqorder);
		model.addAttribute("pagers", pagers);
		return "admin/dqorder/dqorder_list";
	}


	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list2.action")
	public String list2(Dqorder dqorder, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		dqorder.setStatus(0);
		Pager<Dqorder> pagers = dqorderDao.findByEntity(dqorder);
		model.addAttribute("pagers", pagers);
		return "admin/dqorder/dqorder_list2";
	}

	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list3.action")
	public String list3(Dqorder dqorder, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pager<Dqorder> pagers = dqorderDao.findByEntity(dqorder);
		model.addAttribute("pagers", pagers);
		return "admin/dqorder/dqorder_list3";
	}

	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list4.action")
	public String list4(Dqorder dqorder, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pager<Dqorder> pagers = dqorderDao.findByEntity(dqorder);
		model.addAttribute("pagers", pagers);
		return "admin/dqorder/dqorder_list2";
	}

	/**
	 * 跳转添加页面
	 */
	@RequestMapping(value = "/add.action")
	public String add(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "admin/dqorder/dqorder_add";
	}

	/**
	 * 跳至修改页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/edit.action")
	public String edit(Integer id, Model model) {
		Dqorder bean = dqorderDao.load(id);
		model.addAttribute("bean", bean);
		return "admin/dqorder/dqorder_edit";
	}

	/**
	 * 跳至修改页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/pingjia1.action")
	public String pingjia1(Integer id, Model model) {
		Dqorder bean = dqorderDao.load(id);
		model.addAttribute("bean", bean);
		return "admin/dqorder/dqorder_pingjia1";
	}

	/**
	 * 跳至修改页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/pingjia2.action")
	public String pingjia2(Integer id, Model model) {
		Dqorder bean = dqorderDao.load(id);
		model.addAttribute("bean", bean);
		return "admin/dqorder/dqorder_pingjia2";
	}
	/**
	 * 添加信息保存
	 */
	@RequestMapping(value = "/save.action")
	public String save(Dqorder dqorder, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		dqorderDao.insert(dqorder);

		model.addAttribute("msg", "订单创建成功");

		return "common/succ";
	}

	/**
	 * 保存修改信息
	 */
	@RequestMapping(value = "/update1.action")
	public String update1(Dqorder dqorder, Model model, HttpServletRequest request, HttpServletResponse response) {

		Dqorder dqordertt = new Dqorder();
		dqordertt.setYid(dqorder.getYid());
		dqordertt.setYname(dqorder.getYname());
		Pager<Dqorder> pagers = dqorderDao.findByEntity1(dqordertt);
		if(pagers.getDatas().size() > 0){
			model.addAttribute("msg", "用户评分太低不能接单");
			return "common/succ";
		}
		dqorderDao.update(dqorder);
		model.addAttribute("msg", "操作成功");
		return "common/succ";
	}


	/**
	 * 保存修改信息
	 */
	@RequestMapping(value = "/update.action")
	public String update(Dqorder dqorder, Model model, HttpServletRequest request, HttpServletResponse response) {
		dqorderDao.update(dqorder);
//		return "redirect:/dqorder/list.action";
		model.addAttribute("msg", "操作成功");
		return "common/succ";
	}

	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/del.action")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 真正删除
		dqorderDao.deleteById(id);
		// 状态删除
		// Dqorder dqorder = dqorderDao.load(id);
		// load.setDel(1);
		// dqorderDao.update(load);
		return "redirect:/dqorder/list.action";
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
	public String findByObjByEntity(Dqorder dqorder, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 分页查询
		Pager<Dqorder> pagers = dqorderDao.findByEntity(dqorder);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", dqorder);
		return jsonObject.toString();
	}


	// --------------------------------------- 华丽分割线------------------------------	//
}
