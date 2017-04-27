package br.com.moip.webhook.manager.to;

import java.util.List;
import java.util.Map;

public class Dashboard {

	private List<Log> threeMostCalled;
	private Map<String, Integer> sumStatus;

	public Dashboard(List<Log> threeMostCalled, Map<String, Integer> sumStatus) {
		this.threeMostCalled = threeMostCalled;
		this.sumStatus = sumStatus;
	}

	public List<Log> getThreeMostCalled() {
		return threeMostCalled;
	}

	public void setThreeMostCalled(List<Log> threeMostCalled) {
		this.threeMostCalled = threeMostCalled;
	}

	public Map<String, Integer> getSumStatus() {
		return sumStatus;
	}

	public void setSumStatus(Map<String, Integer> sumStatus) {
		this.sumStatus = sumStatus;
	}

}
