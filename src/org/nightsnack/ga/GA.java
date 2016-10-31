package org.nightsnack.ga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Random;
import java.util.Scanner;

public class GA {
	
	private int scale; //种群规模，自己定义初值50~200
	private int cityNum; //城市数量，染色体长度
	private int MAX_GEN; //最大子代数目5000次以上
	private int[][] distance;  //两点间距离矩阵
	private int bestT; //最佳子代的出现代数
	private int bestLength;  //最佳路径的长度
	private int[] bestTour;  //最佳路径的实现方法
	
	//初始种群，父代种群，行数表示种群中个体数量（种群规模），列数表示染色体长度，每一个元素是一个基因片段（城市 数目）
	private int[][] oldPopulation;
	private int[][] newPopulation; //每次分裂后产生的新种群
	private int[] fitness;  //种群中每个个体的适应度
	
	private float[] Pi; //种群中各个个体的累计概率
	private float Pc;  //交叉概率
	private float Pm;  //变异概率
	private int t;  //当前代数
	
	private double[] latitude;
	private double[] longitude;
	

	public GA() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param scale  种群规模
	 * @param cityNum 城市数量
	 * @param mAX_GEN  运行代数
	 * @param pc 交叉的概率
	 * @param pm 变异的概率
	 */
	
	public GA(int scale, int cityNum, int mAX_GEN, float pc, float pm) {
		super();
		this.scale = scale;
		this.cityNum = cityNum;
		MAX_GEN = mAX_GEN;
		Pc = pc;
		Pm = pm;
	}
	
	public void init(String filename) throws IOException
	{
		double[] x;
		double[] y;
		String line;
		
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		distance = new int[cityNum][cityNum];
		x = new double[cityNum];
		y = new double[cityNum];
		
		double[] latitude = new double[cityNum];
		double[] longitude = new double[cityNum];
		this.latitude = new double[cityNum];
		this.longitude = new double[cityNum];
		for (int i = 0; i < cityNum; i++) {
			line = bufferedReader.readLine();
			String[] str = line.split("\\s+");
			double pi = 3.141592;
			x[i] = Double.valueOf(str[1]);
			y[i] = Double.valueOf(str[2]);
			this.latitude[i] = Math.floor(x[i])+(x[i]-Math.floor(x[i]))/60*100;
			this.longitude[i] = Math.floor(y[i])+(y[i]-Math.floor(y[i]))/60*100;
			double deg = (int) (x[i]+0.5);
			double min = x[i] - deg;
			latitude[i] = pi * (deg+5.0*min/3.0)/180.0;
			deg = (int) (y[i]+0.5);
			min = y[i] - deg;
			longitude[i] = pi * (deg+5.0*min/3.0)/180.0;
		}
		double RRR = 6378.388;
		for (int i = 0; i < cityNum-1; i++) {
			distance[i][i] = 0;
//			for (int j = i+1; j < cityNum; j++) {
//				double distance_d = Math.sqrt(Math.pow((x[j]-x[i]), 2)+Math.pow(y[j]-y[i], 2));
//				distance[i][j] = distance[j][i] = (int) Math.ceil(distance_d);
//			}
			for (int j = i + 1; j < cityNum; j++) {  
                  double q1 = Math.cos(longitude[i] - longitude[j]);
                  double q2 = Math.cos(latitude[i] - latitude[j]);
                  double q3 = Math.cos(latitude[i] + latitude[j]);
                  distance[i][j] = distance[j][i] = (int) (RRR * Math.acos( 0.5*((1.0+q1)*q2-(1.0-q1)*q3))+1.0);
            }
		}
		distance[cityNum-1][cityNum-1] = 0;
		
		bestLength = Integer.MAX_VALUE;
		bestTour = new int[cityNum+1];
		bestT = 0;
		t = 0;
		
		newPopulation = new int[scale][cityNum];
		oldPopulation = new int[scale][cityNum];
		fitness = new int[scale];
		Pi = new float[scale];
		
		random = new Random();
		
		
//		for (int i = 0; i < distance.length; i++) {
//			for (int j = 0; j < distance[i].length; j++) {
//				System.out.print(distance[i][j] + " ");
//			}
//			System.out.println();
//		}


	}
	
	public void inputMatrix() {
		Scanner sca = new Scanner(System.in);
		System.out.println("城市数量：");
		this.cityNum = sca.nextInt();
		distance = new int[cityNum][cityNum];
		for (int i = 0; i < distance.length; i++) {
			System.out.println("输入行矩阵：");
			String str = sca.next();
			String[] row = str.split(",");
			for (int j = 0; j < distance[i].length; j++) {
				distance[i][j] = Integer.valueOf(row[j]);
			}
		}
		
		for (int i = 0; i < distance.length; i++) {
		for (int j = 0; j < distance[i].length; j++) {
			System.out.print(distance[i][j] + " ");
		}
		    System.out.println();
	    }
		bestLength = Integer.MAX_VALUE;
		bestTour = new int[cityNum+1];
		bestT = 0;
		t = 0;
		System.out.println("子代规模：");
		this.scale = sca.nextInt();
		newPopulation = new int[scale][cityNum];
		oldPopulation = new int[scale][cityNum];
		fitness = new int[scale];
		Pi = new float[scale];
		
		random = new Random(); 
		
		MAX_GEN = 50000;
		Pc = 0.8f;
		Pm = 0.001f;
	}
	
    private static void swap(int[] index, int i, int j) {
        int tmp = index[i];
        index[i] = index[j];
        index[j] = tmp;
    }
	
	public void initGroup() {
		
		Random rnd = random;
        if (rnd == null)
            random = rnd = new Random(); // harmless race.
		for (int i = 0; i < scale; i++) {
			int[] index = new int[cityNum];
			for (int k = 0; k < index.length; k++) {
				index[k] = k;
			}
			for (int j = cityNum; j > 1; j--) {
				swap(index,j-1,rnd.nextInt(j));
			}
			oldPopulation[i] = index;
		}


	}
	
	/**
	 * 评估每条染色体好坏，就是计算按照该染色体顺序走完全程的总路程
	 * @param chromosome
	 * @return
	 */
	public int evaluate(int[] chromosome) {
		int len = 0;
		for (int i = 1; i < cityNum; i++) {
			len += distance[chromosome[i-1]][chromosome[i]];
		}
		
		len += distance[chromosome[cityNum-1]][chromosome[0]];
		return len;
	}
	
	public void giveMark() {
		double sumFitness = 0;
		double[] eachRate = new double[scale];
		for (int i = 0; i < oldPopulation.length; i++) {
			fitness[i] = evaluate(oldPopulation[i]);
			eachRate[i] = 10.0 /(double)fitness[i];
			sumFitness += eachRate[i];
		}
		//单个得分
		double[] mark = new double[scale];
		for (int i = 0; i < scale; i++) {
			mark[i] = eachRate[i]/sumFitness;
			if(i==0)
				Pi[i] = (float) mark[i];
			else
				Pi[i] = (float) (Pi[i-1]+mark[i]);
		}
//      for(int k=0;k<scale;k++) { System.out.println(fitness[k]+" "+Pi[k]); } 

	}
	
	/**
	 * 选择当前代中最优子代直接复制到后代中
	 */
	public void selectBestGe() {
		double bestFitness = fitness[0];
		int maxid = 0;
		for (int i = 0; i < scale; i++) {
			if (fitness[i]<bestFitness) {
				bestFitness = fitness[i];
				maxid = i;
			}
		}
		
		if (this.bestLength > bestFitness) {
			this.bestLength = (int) bestFitness;
			this.bestT = t;
			for (int i = 0; i < cityNum; i++) {  
                bestTour[i] = oldPopulation[maxid][i];  
            } 
		}
		this.cpGeneration(maxid, 0);
	}
	
	/**
	 * 原封不动克隆子代进入下一代
	 * @param oldpos 在父代中的位置
	 * @param newpos 子代中的位置
	 */
	public void cpGeneration(int oldpos, int newpos) {
        int i;  
//        this.newPopulation[newpos] = this.oldPopulation[oldpos];  区分二维数组 的深浅拷贝！
        for (i = 0; i < cityNum; i++) {  
            newPopulation[newpos][i] = oldPopulation[oldpos][i]; 
        }
	}
	
	/**
	 * 赌轮法选择策略
	 */
	public void roulette() {
		int r_id = 0;
		for (int i = 1; i < scale; i++) {
			double ran = random.nextInt(Integer.MAX_VALUE) % 1000 / 1000.0;
			for (int j = 0; j < scale; j++) {
				if (Pi[j] >= ran) {
					r_id = j;
					break;
				}
			}
			this.cpGeneration(r_id, i);
		}
	}
	
	public void crossOver(int c1, int c2) {
		int ran1 = random.nextInt(Integer.MAX_VALUE) % cityNum;
		int ran2 = ran1;
		do {
			ran2 = random.nextInt(Integer.MAX_VALUE) % cityNum;
		} while (ran2==ran1);
		
		if (ran2<ran1) {
			int temp = ran2;
			ran2 = ran1;
			ran1 = temp;
		}
//		ran2 = 47; ran1 = 9;
		int dif = ran2 - ran1;
		int[] Gh1 = new int[dif];
		int[] Gh2 = new int[dif];
		for (int k = 0, i = ran1; i < ran2; i++,k++) {
			Gh1[k] = newPopulation[c2][i];
			Gh2[k] = newPopulation[c1][i];
			newPopulation[c2][i] = Gh2[k];
			newPopulation[c1][i] = Gh1[k];
		}
	
		//防止重复 染色体1
		int flag1 = 0;
		do {
			flag1 = 0;
			for (int i = 0; i < ran1;i++) {
				int j;
				for (j = 0; j < Gh1.length; j++) {
					if (newPopulation[c1][i] == Gh1[j]) {
						flag1++;
						break;
					}
				}
				if (j!=Gh1.length)
					newPopulation[c1][i] = Gh2[j];
			}
		} while (flag1!=0);
		
		do {
			flag1 = 0;
			for (int i = ran2; i < cityNum; i++) {
				int j;
				for (j = 0; j < Gh1.length; j++) {
					if (newPopulation[c1][i] == Gh1[j]) {
						flag1++;
						break;
					}
				}
				if (j!=Gh1.length)
					newPopulation[c1][i] = Gh2[j];
			}
		} while (flag1!=0);
		//防止重复 染色体2
		int flag2 = 0;
		do {
			flag2 = 0;
			for (int i = 0; i < ran1;i++) {
				int j;
				for (j = 0; j < Gh2.length; j++) {
					if (newPopulation[c2][i] == Gh2[j]) {
						flag2++;
						break;
					}
				}
				if (j!=Gh2.length) {
					newPopulation[c2][i] = Gh1[j];
				}
			}
		} while (flag2!=0);
		do {
			flag2 = 0;
			for (int i = ran2; i < cityNum; i++) {
				int j;
				for (j = 0; j < Gh2.length; j++) {
					if (newPopulation[c2][i] == Gh2[j]) {
						flag2++;
						break;
					}
				}
				if (j!=Gh2.length)
					newPopulation[c2][i] = Gh1[j];
			}
		} while (flag2!=0);
		
		/* 测试输出
		System.out.println(ran2+" "+ran1);
		for (int i = 0; i < newPopulation[c1].length; i++) {
			System.out.print(newPopulation[c1][i]+" ");
		}
		System.out.println();
		for (int i = 0; i < newPopulation[c2].length; i++) {
			System.out.print(newPopulation[c2][i]+" ");
		}
		System.out.println();
		for (int i = 0; i < Gh1.length; i++) {
			System.out.print(Gh1[i]+" ");
		}
		System.out.println();
		for (int i = 0; i < Gh2.length; i++) {
			System.out.print(Gh2[i]+" ");
		}
		System.out.println();
		*/
	}
	/**
	 * PMX部分交叉策略
	 * @param k1 染色体编号1
	 * @param k2 染色体编号2
	 */
	public void crossOver1(int k1, int k2) {  
		int i, j, k, flag;  
        int ran1, ran2, temp;  
        int[] Gh1 = new int[cityNum];  
        int[] Gh2 = new int[cityNum];  
        // Random random = new Random(System.currentTimeMillis());  
  
        ran1 = random.nextInt(65535) % cityNum;  
        ran2 = random.nextInt(65535) % cityNum;  
        while (ran1 == ran2) {  
            ran2 = random.nextInt(65535) % cityNum;  
        }  
  
        if (ran1 > ran2)// 确保ran1<ran2  
        {  
            temp = ran1;  
            ran1 = ran2;  
            ran2 = temp;  
        }  
  
        // 将染色体1中的第三部分移到染色体2的首部  
        for (i = 0, j = ran2; j < cityNum; i++, j++) {  
            Gh2[i] = newPopulation[k1][j];  
        }  
  
        flag = i;// 染色体2原基因开始位置  
  
        for (k = 0, j = flag; j < cityNum;)// 染色体长度  
        {  
            Gh2[j] = newPopulation[k2][k++];  
            for (i = 0; i < flag; i++) {  
                if (Gh2[i] == Gh2[j]) {  
                    break;  
                }  
            }  
            if (i == flag) {  
                j++;  
            }  
        }  
  
        flag = ran1;  
        for (k = 0, j = 0; k < cityNum;)// 染色体长度  
        {  
            Gh1[j] = newPopulation[k1][k++];  
            for (i = 0; i < flag; i++) {  
                if (newPopulation[k2][i] == Gh1[j]) {  
                    break;  
                }  
            }  
            if (i == flag) {  
                j++;  
            }  
        }  
  
        flag = cityNum - ran1;  
  
        for (i = 0, j = flag; j < cityNum; j++, i++) {  
            Gh1[j] = newPopulation[k2][i];  
        }  
  
        for (i = 0; i < cityNum; i++) {  
            newPopulation[k1][i] = Gh1[i];// 交叉完毕放回种群  
            newPopulation[k2][i] = Gh2[i];// 交叉完毕放回种群  
        }  
    }
	
	/**
	 * 变异函数
	 * @param cid 要变异的染色体id
	 */
	public void mutate(int cid) {
		int ran1 = random.nextInt(Integer.MAX_VALUE) % cityNum;
		int ran2 = ran1;
		do {
			ran2 = random.nextInt(Integer.MAX_VALUE) % cityNum;
		} while (ran2==ran1);
		int temp = newPopulation[cid][ran1];
		newPopulation[cid][ran1] = newPopulation[cid][ran2];
		newPopulation[cid][ran2] = temp;
		
//		System.out.println(ran1+" "+ran2);
//		for (int i = 0; i < newPopulation[cid].length; i++) {
//			System.out.print(newPopulation[cid][i]+" ");
//		}
//		System.out.println();
	}
	
    public void OnCVariation(int k) {  
        int ran1, ran2, temp;  
        int count;// 对换次数  
  
        // Random random = new Random(System.currentTimeMillis());  
        count = random.nextInt(65535) % cityNum;  
  
        for (int i = 0; i < count; i++) {  
  
            ran1 = random.nextInt(65535) % cityNum;  
            ran2 = random.nextInt(65535) % cityNum;  
            while (ran1 == ran2) {  
                ran2 = random.nextInt(65535) % cityNum;  
            }  
            temp = newPopulation[k][ran1];  
            newPopulation[k][ran1] = newPopulation[k][ran2];  
            newPopulation[k][ran2] = temp;  
        }  
  
        /* 
         * for(i=0;i<L;i++) { printf("%d ",newGroup[k][i]); } printf("\n"); 
         */  
    }  
	
	/**
	 * 进化函数
	 */
	public void evolution () {
		//先选出第一代，复制到子代里
		this.selectBestGe();
		this.roulette();
		int k;
		for(k = 1; k < scale-1; k = k+2) {
			float ran1 = random.nextFloat();
			float ran2 = random.nextFloat();
			float ran3 = random.nextFloat();
			if (ran1 < Pc) {
				this.crossOver1(k, k+1);
			}
			if (ran2 < Pm) {
				this.OnCVariation(k);
			}
			if (ran3 < Pm) {
				this.OnCVariation(k+1);
			}
			
		}
		if (k == scale - 1)// 剩最后一个染色体没有交叉L-1  
        {  
            float r = random.nextFloat();// /产生概率  
            if (r < Pm) {  
                OnCVariation(k);  
            }  
        }

	}
	
	public void solve() {
		this.initGroup();
		this.giveMark();
		for (t = 0; t < this.MAX_GEN; t++) {
			this.evolution();
			
			// 将新种群newGroup复制到旧种群oldGroup中，准备下一代进化  
            for (int k = 0; k < scale; k++) {  
                for (int i = 0; i < cityNum; i++) {  
                    oldPopulation[k][i] = newPopulation[k][i];  
                }  
            }
			
			this.giveMark();
		}
		
		System.out.println("最后种群...");  
        for (int k = 0; k < scale; k++) {  
            for (int i = 0; i < cityNum; i++) {  
                System.out.print(oldPopulation[k][i] + ",");  
            }  
            System.out.println();  
            System.out.println("---" + fitness[k] + " " + Pi[k]);  
        }  
  
        System.out.println("最佳长度出现代数：");  
        System.out.println(bestT);  
        System.out.println("最佳长度");  
        System.out.println(bestLength);  
        System.out.println("最佳路径：");  
        for (int i = 0; i < cityNum; i++) {  
            System.out.print(bestTour[i] + ",");  
        }
		

	}
	

	public double[] getLatitude() {
		return latitude;
	}

	public double[] getLongitude() {
		return longitude;
	}

	private Random random;  //用来生成随机数
	

	
	public int getBestT() {
		return bestT;
	}

	public int getBestLength() {
		return bestLength;
	}

	public int[] getBestTour() {
		return bestTour;
	}


}















































