public abstract class cube
{
	int cx=0;//30*x 
	int cy=0;//30*y
	int space[][]=new int[4][2];
	//int fakespace[][]=new int[4][2];
	private int speed=6;
	private boolean gspeed=false,sspeed=false;
	int state=0;
	int floor[][];
	int leftfloor[][];
	int rightfloor[][];
	public abstract void Rotate(boolean all[][]);//旋轉
	public void move(boolean all[][],int x)//移動
	{
		setX(getCenter()[0]+x);
		if(x>0)
		{
			if(isfloor(all,2))
			{
				setX(getCenter()[0]-1);
				
			}
		}
		else
		{
			if(isfloor(all,1))
			{
				setX(getCenter()[0]+1);
			}
		}
	}
	
	public void down(boolean all[][])//一被呼叫直到落地為止加速落地
	{
		int tempspeed=0;
		if(!isfloor(all,0)&&speed!=30)//還沒落地而且還沒加速過速度
		{
			tempspeed=getSpeed();
			setSpeed(30);
		}
		else
		{
			setSpeed(tempspeed);
		}
		setSpeed(tempspeed);
	}
	
	public boolean isfloor(boolean all[][],int select)//落地確認
	{
		int tempfloor[][];
		switch(select)//牆壁定義 地板:0 左牆壁:1 右牆壁:2
		{
			case 0:
							tempfloor=floor;
							break;
			case 1:
							tempfloor=leftfloor;
							break;
			case 2:
							tempfloor=rightfloor;
							break;
			default:
							tempfloor=floor;
							break;
		}
		boolean yes=false;
	
		for(int i=0;i<tempfloor.length;i++)
		{
			
			try
			{
			if(all[tempfloor[i][0]+cx][tempfloor[i][1]+cy])
				yes=true;
			}
			catch(ArrayIndexOutOfBoundsException e)//跳到牆壁裡去了
			{
				//	System.out.println("這裡什麼時後會發生阿");
				
					yes=true;
				
			}
			//System.out.println((tempfloor[i][0]+cx)+" "+(tempfloor[i][1]+cy));

		}
		return yes;
	}

	public void setState(int s)//設定狀態
	{
		state=s;
	}
	public void setFloor(int ... num)//哪幾塊會碰到地板設定
	{
		floor=new int[num.length][2];
		for(int i=0;i<num.length;i++)
		{
			floor[i][0]=space[num[i]][0];
			floor[i][1]=space[num[i]][1];
		}
	}
	public void setLeftFloor(int ... num)//哪幾塊會接觸地板 算出地板 vargc
	{
		leftfloor=new int[num.length][2];
		for(int i=0;i<num.length;i++)
		{
			leftfloor[i][0]=space[num[i]][0];//肉體碰觸
			leftfloor[i][1]=space[num[i]][1];
		}
	}
	public void setRightFloor(int ... num)//哪幾塊會接觸地板 算出地板 vargc
	{
		rightfloor=new int[num.length][2];
		for(int i=0;i<num.length;i++)
		{
			rightfloor[i][0]=space[num[i]][0];
			
			rightfloor[i][1]=space[num[i]][1];
		}
	}
	
	public int[] getCenter()//取得中心 此定義之中心為方塊目前位於場景中之點
	{
		int center[]=new int[2];
		center[0]=cx;
		center[1]=cy;
		return center;
	}
	public void setCenter(int x,int y)//設定中心
	{
		cx=x;
		cy=y;
	}
	public void setX(int x)//設定中心
	{
		cx=x;

	}
	public void setY(int y)//設定中心
	{
		cy=y;
	}
	void setCubeSpace(int[][] sp,int i,int x,int y)
	{
		sp[i][0]=x;
		sp[i][1]=y;
	}
	public int[][] getCubeSpace()
	{
		return space;
	}
	public boolean kickwall(boolean all[][])
	{
		
		boolean yes=false;
		for(int i=0;i<4;i++)
		{
			try
			{
				//System.out.println("all[x][y]"+all[space[i][0]+cx][space[i][1]+cy]+" ");
				if(all[space[i][0]+cx][space[i][1]+cy])
				{		
						yes=true;//碰到牆了
				}
				if(cy<4)
						yes=false;
			}
			catch(ArrayIndexOutOfBoundsException e)//轉到牆壁
			{
				//System.out.println("BoundsException");
					yes=false;//碰到牆了	
			}
			
		}
		

		return yes;
	}
	public synchronized int getSpeed()//得到速度 需與設定速度同步
	{
				gspeed=true;
				if(sspeed)
				{
					notify(); 
					try{
					wait();
					}catch(InterruptedException e){};
					
				}

				return speed;

	}
	public synchronized void setSpeed(int s)//改變速度需與得到速度同步
	{	
				
				if(gspeed&&!sspeed)
				{
					sspeed=true;
					try{
					wait();
					}catch(InterruptedException e){};
					
					speed=s;
					sspeed=false;
					notify(); 
					
				}
				else
					speed=s;
				
		
	}
	public int[][] getLine(int y)
	{
		int i,j;
		int s[][]=new int[24][2];
		for(i=0;i<4;i++)
		{
			for(j=0;j<3;j++)
			{
				s[(i*3)+j][0]=(space[i][0]+cx)*30+j*15;
				s[(i*3)+j][1]=(space[i][1]+cy)*30+y;
				s[12+(i*3)+j][0]=(space[i][0]+cx)*30+j*15;
				s[12+(i*3)+j][1]=(space[i][1]+cy)*30+y-30;
			}
		}
		return s;
	}
}