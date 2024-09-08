package com.gssm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.gssm.common.Const;
import com.gssm.pagehelper.PageHelper;
import com.gssm.util.Pager;
import com.gssm.util.SystemContext;

/**
 * 通用实现类
 * @author 
 *
 * @param <T>
 */
public class BaseDaoImpl<T>{
	
	   //注入SqlSessionTemplate实例
		@Resource(name="sqlSessionTemplate")
	    private SqlSession sqlSession;
		
		/**
		 * 获得当前事物的session
		 */
		public SqlSession getSqlSession(){
			return this.sqlSession;
		}
		public void setSqlSession(SqlSessionTemplate sqlSession) {       
		    this.sqlSession = sqlSession;      
	    } 		
		
	
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;
	
	public Class<?> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	/**
	 * 数据库添加一个对象
	 * @param entity
	 * @return
	 */
	public int insert(T entity) {
		return this.getSqlSession().insert(getClz().getName()+".insert",entity);
		
	}
	
	/**
	 * 数据库添加一个对象
	 * @param sqlId  mybatis 插入操作的sqlId
	 * @param obj  操作对象
	 */
	public void insert(String sqlId, T obj) {
		this.getSqlSession().insert(getClz().getName()+"."+sqlId,obj);
	}

	/**
	 * 删除一个对象
	 * @param id 数据库删除一个对象
	 */
	public void deleteById(Serializable id) {
		this.getSqlSession().delete(getClz().getName()+".delete", id);
		
	}

	/**
	 * 删除对象操作
	 * @param sqlId : 删除操作的sql ID
	 * @param params ：参数为map的对象
	 */
	public void deleteBySqId(String sqlId, Map<String, Object> params) {
		this.getSqlSession().delete(getClz().getName()+"."+sqlId, params);
		
	}
	
	/**
	 * 修改对象
	 * @param entity
	 */
	public void update(T entity) {
		this.getSqlSession().update(getClz().getName()+".update", entity);
		
	}

	/**
	 * 查询对象列表
	 * @param params 参数为map的对象
	 * @return
	 */
	public List<T> list(Map<String, Object> params) {
		return this.getSqlSession().selectList(getClz().getName()+".list", params);
	}

	/**
	 * 查询对象列表
	 * @param sqlId    查询操作的sql ID
	 * @param params   参数为map的对象
	 * @return
	 */
	public List<T> list(String sqlId, Map<String, Object> params) {
		return this.getSqlSession().selectList(getClz().getName()+"."+sqlId, params);
	}

	/**
	 * 查询对象列表
	 * @return
	 */
	public List<T> listAll() {
		return this.getSqlSession().selectList(getClz().getName()+".listAll");
	}

	/**
	 * 查询对象列表 
	 * @param entity
	 * @return
	 */
	public List<T> listAllByEntity(T entity) {
		return this.getSqlSession().selectList(getClz().getName()+".listAllByEntity", entity);
	}

	/**
	 * 加载对象
	 * @param id
	 * @return
	 */
	public T load(Serializable id) {
		return (T)this.getSqlSession().selectOne(getClz().getName()+".load",id);
	}

	public T loadBySqlId(String sqlId, Map<String, Object> params) {
		return (T)this.getSqlSession().selectOne(getClz().getName()+"."+sqlId,params);
	}

	public T loadBySqlId(String sqlId, T entity) {
		return (T)this.getSqlSession().selectOne(getClz().getName()+"."+sqlId,entity);
	}
	/**
	 * 分页
	 */
	public Pager<T> findByMap(Map<String, Object> params) {
		return findByMap(getClz().getName()+".findByMap", params);
	}
	
	/**
	 * 分页
	 */
	public Pager<T> findByMap(String sqlId, Map<String, Object> params) {
		/**
		 * 执行分页
		 */
    	Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Integer pageNum = null;
		if(pageOffset == 0){
			pageNum = 1;
		}else{
			pageNum = pageOffset/pageSize+1;
		}
		PageHelper.startPage(pageNum, pageSize);
		
		List<T> datas = this.getSqlSession().selectList(sqlId, params);
		Pager<T> pages = new Pager<T>(datas);
		return pages;
	}
	
	public Pager<T> findByEntity(T entity) {
		return findByEntity(getClz().getName()+".findByEntity",entity);
	}

	public Pager<T> findByEntity(String sqlId, T entity) {
		/**
		 * 执行分页
		 */
    	Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Integer pageNum = null;
		if(pageOffset == 0){
			pageNum = 1;
		}else{
			pageNum = pageOffset/pageSize+1;
		}
		PageHelper.startPage(pageNum, pageSize);
		List<T> datas = this.getSqlSession().selectList(sqlId, entity);
		Pager<T> pages = new Pager<T>(datas);
		return pages;
	}
	
	public Pager<T> findByCount(Map<String, Object> params) {
		return findByCount(getClz().getName()+".find", params);
	}

	public Pager<T> findByCount(String sqlId, Map<String, Object> params) {
		/**
		 * 执行分页
		 */
    	Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Integer pageNum = null;
		if(pageOffset == 0){
			pageNum = 1;
		}else{
			pageNum = pageOffset/pageSize+1;
		}
		PageHelper.startPage(pageNum, pageSize);
		if(params==null) params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		params.put("sort", sort);
		params.put("order", order);
		List<T> datas = this.getSqlSession().selectList(sqlId, params);
		int totalRecord = this.getSqlSession().selectOne(sqlId+"_count",params);
		Pager<T> pages = new Pager<T>(datas);
		pages.setTotal(totalRecord);
		return pages;
	}

	
//判断空
	public boolean isEmpty(String str) {
		return (null == str) || (str.trim().length() <= 0);
	}

	public boolean isEmpty(Character cha) {
		return (null == cha) || cha.equals(' ');
	}

	public boolean isEmpty(Object obj) {
		return (null == obj);
	}

	public boolean isEmpty(Object[] objs) {
		return (null == objs) || (objs.length <= 0);
	}

	public boolean isEmpty(Collection<?> obj) {
		return (null == obj) || obj.isEmpty();
	}

	public boolean isEmpty(Set<?> set) {
		return (null == set) || set.isEmpty();
	}

	public boolean isEmpty(Serializable obj) {
		return null == obj;
	}

	public boolean isEmpty(Map<?, ?> map) {
		return (null == map) || map.isEmpty();
	}
	

}
