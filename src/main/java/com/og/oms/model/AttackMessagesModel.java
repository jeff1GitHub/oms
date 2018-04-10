package com.og.oms.model;

import java.util.List;

public class AttackMessagesModel {
	
	private List<AttackMessage> attackMessages;
	
	public List<AttackMessage> getAttacks() {
		return attackMessages;
	}

	public void setAttacks(List<AttackMessage> attackMessages) {
		this.attackMessages = attackMessages;
	}

	public AttackMessagesModel(List<AttackMessage> attackMessages) {
        super();
        this.attackMessages = attackMessages;
    }

    public AttackMessagesModel() {
        super();
    }
}
