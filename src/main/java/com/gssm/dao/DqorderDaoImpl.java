package com.gssm.dao;

import org.springframework.stereotype.Repository;
import com.gssm.entity.Dqorder;
import com.gssm.util.Pager;
@Repository
public class DqorderDaoImpl extends BaseDaoImpl<Dqorder>{
	public Pager<Dqorder> findByEntity1(Dqorder entity) {
		return findByEntity(getClz().getName()+".findByEntity1",entity);
	}
}