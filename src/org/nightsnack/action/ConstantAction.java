package org.nightsnack.action;

import java.io.IOException;

import org.nightsnack.ga.GA;
import org.nightsnack.model.Constant;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ConstantAction implements ModelDriven<Constant> {
	private Constant constant;
	private int BestT;
	private int BestLength;
	private int[] BestTour;
	
	private double[] latitude;
	private double[] longitude;

	//输入常量
	public String input() {
		System.out.println("input");
		return "success";
	}
	
	public String list() {
		System.out.println("城市数量："+constant.getCityNum());
		System.out.println("种群大小："+constant.getScale());
		System.out.println("3："+constant.getMaxGen());
		System.out.println("4："+constant.getPc());
		System.out.println("5："+constant.getPm());
//		ActionContext.getContext().put("cityNum", constant.getCityNum());
		
		return "success";
	}
	
	public String calculate() {
		GA ga = new GA(constant.getScale(), constant.getCityNum(), constant.getMaxGen(), constant.getPc(), constant.getPm());
		try {
			ga.init("/Users/chiyexiao/Documents/data2.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ga.solve();	
		this.BestLength = ga.getBestLength();
		this.BestT = ga.getBestT();
		this.BestTour = ga.getBestTour();
		this.longitude = ga.getLongitude();
		this.latitude = ga.getLatitude();
		
		return "success";
	}

	@Override
	public Constant getModel() {
		// TODO Auto-generated method stub
		if (constant == null) {
			constant = new Constant();
		}
		return constant;
	}
	
//getters&setters

	public int getBestT() {
		return BestT;
	}

	public void setBestT(int bestT) {
		BestT = bestT;
	}

	public int getBestLength() {
		return BestLength;
	}

	public void setBestLength(int bestLength) {
		BestLength = bestLength;
	}

	public int[] getBestTour() {
		return BestTour;
	}

	public void setBestTour(int[] bestTour) {
		BestTour = bestTour;
	}

	public double[] getLatitude() {
		return latitude;
	}

	public void setLatitude(double[] latitude) {
		this.latitude = latitude;
	}

	public double[] getLongitude() {
		return longitude;
	}

	public void setLongitude(double[] longitude) {
		this.longitude = longitude;
	}

}
