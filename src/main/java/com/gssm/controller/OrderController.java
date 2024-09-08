package com.gssm.controller;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gssm.entity.User;
import com.gssm.entity.Yuangong;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gssm.dao.OrderDaoImpl;
import com.gssm.entity.Order;
import com.gssm.util.JsonUtil2;
import com.gssm.util.Pager;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController{
	/**
	 * 依赖注入 start dao/===
	 */
	@Autowired
	private OrderDaoImpl orderDao;

	public OrderDaoImpl getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDaoImpl orderDao) {
		this.orderDao = orderDao;
	}

	// --------------------------------------- 华丽分割线 ------------------------------//

	/**
	 * 分页查询 返回list对象(通过对象)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list.action")
	public String list(Order order, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Integer userType = (Integer)session.getAttribute("userType");
		if(2 == userType){
			User user = (User)session.getAttribute("user");
			order.setUsername(user.getRealname());
		}
		if(1 == userType){
			Yuangong user = (Yuangong)session.getAttribute("yuangong");
			order.setYuangongid(user.getId());
		}
		Pager<Order> pagers = orderDao.findByEntity(order);
		model.addAttribute("pagers", pagers);
		return "admin/order/order_list";
	}

	/**
	 * 跳转添加页面
	 */
	@RequestMapping(value = "/add.action")
	public String add(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "admin/order/order_add";
	}

	/**
	 * 跳至修改页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/edit.action")
	public String edit(Integer id, Model model) {
		Order bean = orderDao.load(id);
		model.addAttribute("bean", bean);
		return "admin/order/order_edit";
	}

	/**
	 * 添加信息保存
	 */
	@RequestMapping(value = "/save.action")
	public String save(Order order, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		orderDao.insert(order);

		model.addAttribute("msg", "订单创建成功");

		return "common/succ";
	}


	/**
	 * 保存修改信息
	 */
	@RequestMapping(value = "/update.action")
	public String update(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		orderDao.update(order);
		model.addAttribute("msg", "订单修改成功");
		return "common/succ";
	}

	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/del.action")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 真正删除
		orderDao.deleteById(id);
		// 状态删除
		// Order order = orderDao.load(id);
		// load.setDel(1);
		// orderDao.update(load);
//		return "redirect:/order/list.action";
		model.addAttribute("msg", "订单删除成功");
		return "common/succ";
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
	public String findByObjByEntity(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 分页查询
		Pager<Order> pagers = orderDao.findByEntity(order);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", order);
		return jsonObject.toString();
	}


	// --------------------------------------- 华丽分割线------------------------------	//
}
