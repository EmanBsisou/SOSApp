package com.apb.sosApp.model;

public class Responser {
	private String responserId;
	private String responserName;
	private String vehicalNumber;
	private String deviceId;
	public String getResponserId() {
		return responserId;
	}
	public void setResponserId(String responserId) {
		this.responserId = responserId;
	}
	public String getResponserName() {
		return responserName;
	}
	public void setResponserName(String responserName) {
		this.responserName = responserName;
	}
	public String getVehicalNumber() {
		return vehicalNumber;
	}
	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Override
	public String toString() {
		return "ResponserRegistration [responserId=" + responserId + ", responserName=" + responserName + ", vehicalNumber="
				+ vehicalNumber + ", deviceId=" + deviceId + "]";
	}
	
	
}
