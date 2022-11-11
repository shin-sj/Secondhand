package com.secondhand.domain;

import java.io.Serializable;

/*
 * MyPage - Accuse Domain Class
 * */
@SuppressWarnings("serial")
public class Accuse  implements Serializable {

	// variable
	private int accuseId;   // Sequence
	private String victim;   // (FK) userId : ������(�α�����, �Ű��ѻ��)
	private String attacker; // (FK) userId : �Ǹ���(�Ű� �����)
	private String reason;  // �Ű� ����
	private int accuseCount; 
	//private boolean isAccuse;
	private String isAccuse;
	
	// setter & getter
	public int getAccuseId() {
		return accuseId;
	}
	public void setAccuseId(int accuseId) {
		this.accuseId = accuseId;
	}
	public String getVictim() {
		return victim;
	}
	public void setVictim(String victim) {
		this.victim = victim;
	}
	public String getAttacker() {
		return attacker;
	}
	public void setAttacker(String attacker) {
		this.attacker = attacker;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getAccuseCount() {
		return accuseCount;
	}
	public void setAccuseCount(int accuseCount) {
		this.accuseCount = accuseCount;
	}
	public String isAccuse() {
		return isAccuse;
	}
	public void setAccuse(String isAccuse) {
		this.isAccuse = isAccuse;
	}

	
		
}
