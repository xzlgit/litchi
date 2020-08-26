package cn.litchi.litchiapiserver.entity;

import java.io.Serializable;
import java.util.List;

public class NodeIdAndDateList implements Serializable{
	
	private Long nodeId;
	private List<String> years;
	private List<String> dates;
	
	
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public List<String> getYears() {
		return years;
	}
	public void setYears(List<String> years) {
		this.years = years;
	}
	
	
	

}
