package com.amdocs.pns.model;

public class Plant {
	public int plantId;
	public String plantName;
	public String plantType;
	public String originCountryName;
	public String waterSupplyFrequency;
	public double cost;
	public int sunLightRequired;
	
	public Plant(){
		
	}
	
	public Plant(int pid,String pname,String ptype,String cname,String wsfreq,double cost,int sunreq){
	this.plantId=pid;
	this.plantName=pname;
	this.plantType=ptype;
	this.originCountryName=cname;
	this.waterSupplyFrequency=wsfreq;
	this.cost=cost;
	this.sunLightRequired=sunreq;
	}
	
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getPlantType() {
		return plantType;
	}
	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}
	public String getOriginCountryName() {
		return originCountryName;
	}
	public void setOriginCountryName(String originCountryName) {
		this.originCountryName = originCountryName;
	}
	public String getWaterSupplyFrequency() {
		return waterSupplyFrequency;
	}
	public void setWaterSupplyFrequency(String waterSupplyFrequency) {
		this.waterSupplyFrequency = waterSupplyFrequency;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getSunLightRequired() {
		return sunLightRequired;
	}
	public void setSunLightRequired(int sunLightRequired) {
		this.sunLightRequired = sunLightRequired;
	}
}
