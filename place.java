public class place
{
	boolean place[][]=new boolean[12][19];//場地
	Player player;//方塊消掉時呼叫小朋友

	place()
	{

		for(int i=0;i<12;i++)
		{
			for(int j=0;j<19;j++)
			{
				if(i==0 ||i==11)
					place[i][j]=true;
				else
				{
					if(j==18)
						place[i][j]=true;
				}
			}
		}			
	}	
	public void setPlayer(Player p)
	{
		player=p;
	}
	public void show()
	{
		int i,j;
		for(i=0;i<19;i++)
		{
			for(j=0;j<12;j++)
			{
				if(place[j][i])
					System.out.print("■");
				else
					System.out.print("□");
				
			}
			System.out.println("");
		}
	}
	public boolean getPlace(int x,int y)//多載get point
	{
		return place[x][y];
	}
	public boolean[][] getPlace()//多載get全部
	{
		return place;
	}
	public void setPlace(int x,int y)
	{
		place[x][y]=true;
	}
	public void setYGround(int y)
	{
		for(int i=1;i<11;i++)
		{
			place[i][y]=true;
		}
	}
	public void clearYGround(int y)
	{
		for(int i=1;i<11;i++)
		{
			place[i][y]=false;
		}
	}
	public int addCube(cube c)
	{

		for(int i=0;i<4;i++)
		{
			//System.out.println("新放置場地"+(c.getCubeSpace()[i][0]+c.getCenter()[0])+" "+(c.getCubeSpace()[i][1]+c.getCenter()[1]));
			setPlace(c.getCubeSpace()[i][0]+c.getCenter()[0],c.getCubeSpace()[i][1]+c.getCenter()[1]);
		}
		
		return checkPlace();
	}
	public int checkPlace()//確認場地是否有該消除之行數
	{
		int i,j,k;
		int line=0;
		for(i=0,k=0;i<18;i++)
		{
			k=0;
			for(j=1;j<11;j++)
			{
				if(place[j][i])
					k++;
			}
			if(k==10)
			{
				line++;
				//	show();
				drop(i);
				if(!player.isjump&&!player.ifIsfloor(getPlace(),0,1))//沒有在跳 而且往下移不是地板
					player.haveGravity(getPlace());
				//show();
			}
		}
		return line;
		
	}
	public void drop(int x)//消除方塊與將之上方塊下降
	{
		int level=-1;
		clearYGround(x);
		for(int i=0;i<18;i++)
		{
			for(int j=1;j<11;j++)
			{
				if(place[j][i])//算出最高層
				{
					level=i;
					break;
				}
				
			}
			if(level!=-1)
			{
				break;
			}
		}
				
		for(int i=x-1;i>=level-1;i--)
		{
			for(int j=1;j<11;j++)
			{
				place[j][i+1]=false;
				if(i!=level-1)
				{
					place[j][i+1]=place[j][i];

				}
				else
					place[j][i]=false;
			}
		}
	}
	
	
	
}