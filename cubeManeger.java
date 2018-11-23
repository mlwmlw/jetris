public class cubeManeger implements Runnable
{
	Player player;
	cube aircube;
	place Place;
	tetris Tetris;
	int x=0,y=0;
	cube rand[]=new cube[7];
	cubeManeger(Player p,place pl,tetris t)
	{
		player=p;
		Place=pl;
		Tetris=t;
		
		
	}
	public void getRandomcube() //取得隨機方塊
	{
		
		rand[0] =new cubeI(5,0);
		rand[1] =new cubeJ(5,0);
		rand[2] =new cubeL(5,0);
		rand[3] =new cubeO(5,0);
		rand[4] =new cubeS(5,0);
		rand[5] =new cubeT(5,0);
		rand[6] =new cubeZ(5,0);
		int a=((int)Math.round((Math.random()*100))%7);
		//System.out.println(a+"");
		aircube=rand[a];
	}
	public cube getAirCube()
	{
		return aircube;
	}
	public void run()
	{
				getRandomcube();
				aircube.Rotate(Place.getPlace());
				while(true)
				{										
						try
						{
							Thread.sleep(50);
						}
						catch(Exception e)
						{
						}
						if(Tetris.Pause)
						{
							continue;
						}
						y+=aircube.getSpeed();
						
						
						//判斷壓到小朋友
						
						if(player.isCube(aircube.getLine(y)))
						{
							System.out.println("遊戲結束");
							Tetris.jp.repaint();
							Tetris.die();
							break;
						}
						
						
						
						
						
						
						if(y>=30)
						{
							aircube.setY(aircube.getCenter()[1]+y/30);
							y=0;

							if(aircube.isfloor(Place.getPlace(),0))
							{
								aircube.setY(aircube.getCenter()[1]-1);
								
								try
								{
									switch(Place.addCube(aircube))//將落地之方塊加入場景
									{
										case 1:
														Tetris.setScore(100);
														break;
										case 2:
														Tetris.setScore(300);
														break;
										case 3:
														Tetris.setScore(700);
														break;
										case 4:
														Tetris.setScore(1200);
														break;
										
									}
									
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									 System.out.println("遊戲結束");
									 Tetris.jp.repaint();
									 Tetris.die();
									 break;
								}
								
								Tetris.update(Tetris.getGraphics());
								//Place.show();
								getRandomcube();//取新方塊
							}

						}
						Tetris.update(Tetris.getGraphics());
				}
		}

	
	
	
	
	
	
	
	
}