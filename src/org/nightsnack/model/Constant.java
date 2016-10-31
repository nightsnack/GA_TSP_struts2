package org.nightsnack.model;

public class Constant {
	private int scale;
	private int cityNum;
	private int maxGen;
	private float Pc;
	private float Pm;
	
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getCityNum() {
		return cityNum;
	}
	public void setCityNum(int cityNum) {
		this.cityNum = cityNum;
	}
	public int getMaxGen() {
		return maxGen;
	}
	public void setMaxGen(int maxGen) {
		this.maxGen = maxGen;
	}
	public float getPc() {
		return Pc;
	}
	public void setPc(float pc) {
		Pc = pc;
	}
	public float getPm() {
		return Pm;
	}
	public void setPm(float pm) {
		Pm = pm;
	}
	
	public Constant(int scale, int cityNum, int maxGen, float pc, float pm) {
		super();
		this.scale = scale;
		this.cityNum = cityNum;
		this.maxGen = maxGen;
		Pc = pc;
		Pm = pm;
	}
	
 
	public Constant() {
		// TODO Auto-generated constructor stub
	}
}
