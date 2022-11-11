package com.secondhand.service.facade;

import java.util.List;
import com.secondhand.domain.Accuse;

public interface MyPageFacade {
	/////////////////////////////////////////////////////////////////////////
	/* accuse */
	/////////////////////////////////////////////////////////////////////////
	void insertAccuse(Accuse accuse);
	
	int countAccuseById(String accounId);
	
	String isAccuseAlready(String attacker, String victim);

	List<Accuse> selectAccuseList(String victim);
}
