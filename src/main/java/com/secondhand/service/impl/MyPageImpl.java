package com.secondhand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.secondhand.dao.AccuseDao;
import com.secondhand.domain.Accuse;
import com.secondhand.service.facade.MyPageFacade;


@Repository
@Service("mypageImpl")
@Transactional
public class MyPageImpl implements MyPageFacade {
	@Autowired
	private AccuseDao accuseDao;
	
	@Override
	public void insertAccuse(Accuse accuse) {
		accuseDao.insertAccuse(accuse);
	}

	@Override
	public int countAccuseById(String accountId) {
		return accuseDao.countAccuseById(accountId);
	}

	@Override
	public String isAccuseAlready(String attacker, String victim) {
		return accuseDao.isAccuseAlready(attacker, victim);
		
	}

	@Override
	public List<Accuse> selectAccuseList(String victim) {
		return accuseDao.selectAccuseList(victim);
	}
}
