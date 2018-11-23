public class Player //小朋友||主人翁
{
	int cx=100;
	int cy=0;
	final int gravity=1;//重力
	private int speed=10;//速度
	int jump_start=-12; //跳躍初始速度
	boolean isjump=false;
	boolean islive=true;
	boolean tempall[][];
	Player(boolean all[][])
	{
		tempall=all;
		if(cy==0)
		{
			cy=400;
			haveGravity(all);
		}
					
	}
	public void haveGravity(boolean all[][])
	{
			new Thread(new Runnable()
			{
					int jump_speed=5;
					public void run()
					{
						while(!isfloor(tempall))
						{
								jump_speed+=gravity;
								try{
								Thread.sleep(50);
								}catch(Exception e){};
																	
								cy=cy+jump_speed;
						}
						cy=cy-cy%30;
						//System.out.println("pleyer location:"+cx +" "+cy);		
						isjump=false;
					}
					
			}
			).start();		
			
	}
	public void move(boolean all[][],int x)
	{
		if(!ifIsfloor(all,x,0))
			cx=cx+x;
		if(!isjump&&!ifIsfloor(all,0,1))//沒有在跳 而且往下移不是地板
			haveGravity(all);
	}
	public void jump(boolean all[][])
	{
		if(!isjump)
		{
			tempall=all;
			isjump=true;
			if(!ifIsfloor(tempall,0,-1))
				new Thread(new Runnable()
				{
						
						int jump_speed=jump_start;
						public void run()
						{
							
							while(!isfloor(tempall))
							{
									//System.out.println("cy:"+cy);		
									jump_speed+=gravity;
									try{
									Thread.sleep(50);
									}catch(Exception e){};
																		
									cy=cy+jump_speed;
							}
							if(ifIsfloor(tempall,0,(cy%30)))
							{
								cy=cy-cy%30;
								isjump=false;//落地
							}
							else//撞到天花板
							{
								cy=cy+cy%30;
								haveGravity(tempall);
							}
							
							
							
						}
										
				}
				).start();
			
		}
	}
	public boolean isfloor(boolean all[][])//怕到時候會有碰到地板跟左右的分別
	{
		boolean yes=false;

		for(int i=0;i<60;i++)//人物邊線碰撞偵測
		{
				for(int j=0;j<30;j++)
				{
					if(i==0||i==59 ||j==0||j==29)//上下左右線
					{
						try
						{
						if(all[(cx+j)/30][(cy+i)/30])
							yes=true;
						}catch(ArrayIndexOutOfBoundsException e){}//跳太高偵測..
					}
				}
		}
		
		return yes;
		
	}
	
	public boolean ifIsfloor(boolean all[][],int x,int y)//假如做了某些移動是否碰到牆壁
	{
		boolean yes=false;

		for(int i=0;i<60;i++)//人物邊線碰撞偵測
		{
				for(int j=0;j<30;j++)
				{
					if(i==0||i==59 ||j==0||j==29)//上下左右線
					{
						try
						{
							if(all[(cx+x+j)/30][(cy+y+i)/30])
								yes=true;
						}
						catch(ArrayIndexOutOfBoundsException e)
						{
							yes=true;
						}
					}
				}
		}
		
		if(yes)
			return true;
		else
			return false;
		
	}
	public boolean isCube(int s[][])//有沒有碰到方塊的地板身體某幾點呢
	{
		boolean yes=false;

		for(int i=0;i<60;i++)//人物邊線碰撞偵測
		{
				for(int j=0;j<30;j++)
				{
					if(i==0||i==59 ||j==0||j==29)//上下左右線
					{
						try
						{
							for(int k=0;k<s.length;k++)
							{
								if(s[k][0]==(cx+j)&&s[k][1]==(cy+i))
								{
									yes=true;
									break;
								}
							}	
							
						}catch(ArrayIndexOutOfBoundsException e){}//跳太高偵測..
					}
				}
		}

		return yes;
		
	}
	public synchronized int getSpeed()
	{
		return speed;
		
	}
}