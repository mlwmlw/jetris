public class cubeI extends cube
{
	
	public void Rotate(boolean all[][])
	{
		if(state==0)//橫
		{
			setCubeSpace(space,0,0,0);
			setCubeSpace(space,1,-2,0);
			setCubeSpace(space,2,-1,0);
			setCubeSpace(space,3,1,0);
			state=1;
			if(kickwall(all))//轉了撞牆
			{
					Rotate(all);
				//System.out.println("轉到牆壁理了調整");
			}
			setFloor(0,1,2,3);
			setRightFloor(3);
			setLeftFloor(1);
			
		}
		else//直
		{
			setCubeSpace(space,0,0,0);
			setCubeSpace(space,1,0,-1);
			setCubeSpace(space,2,0,1);
			setCubeSpace(space,3,0,2);
			state=0;
			if(kickwall(all))//轉了撞牆
			{
					Rotate(all);
				//System.out.println("轉到牆壁理了調整");
			}
			setFloor(3);
			setRightFloor(0,1,2,3);
			setLeftFloor(0,1,2,3);
			
			
		}
	}

	cubeI(int x,int y)
	{
		cx=x;
		cy=y;
		setCubeSpace(space,0,0,0);
		setCubeSpace(space,1,-2,0);
		setCubeSpace(space,2,-1,0);
		setCubeSpace(space,3,1,0);
		state=1;
		setFloor(0,1,2,3);
		setRightFloor(3);
		setLeftFloor(1);
	}
	
	
	
	
}