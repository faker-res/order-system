package com.tuniu.ord.vo;

import java.io.Serializable;
import java.util.List;

public class ProductsVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4231745155056376522L;

	private CheckLossTuniuInfoVo tuniuInfo;
	
	private CheckLossAgencyInfoVo agencyInfo;
	
	private List<CheckLossDepartVo> departDates;
	
	private List<CheckLossTouristVo> lossTourist;
	
	private CheckLossResourceVo resource;
	
	public List<CheckLossDepartVo> getDepartDates() {
		return departDates;
	}

	public void setDepartDates(List<CheckLossDepartVo> departDates) {
		this.departDates = departDates;
	}

	public List<CheckLossTouristVo> getLossTourist() {
		return lossTourist;
	}

	public void setLossTourist(List<CheckLossTouristVo> lossTourist) {
		this.lossTourist = lossTourist;
	}

	public CheckLossTuniuInfoVo getTuniuInfo() {
		return tuniuInfo;
	}

	public void setTuniuInfo(CheckLossTuniuInfoVo tuniuInfo) {
		this.tuniuInfo = tuniuInfo;
	}

	public CheckLossAgencyInfoVo getAgencyInfo() {
		return agencyInfo;
	}

	public void setAgencyInfo(CheckLossAgencyInfoVo agencyInfo) {
		this.agencyInfo = agencyInfo;
	}

	public CheckLossResourceVo getResource() {
		return resource;
	}

	public void setResource(CheckLossResourceVo resource) {
		this.resource = resource;
	}
	
}
