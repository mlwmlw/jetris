public class cubeO extends cube
{
	
	public void Rotate(boolean all[][])
	{
		if(state==0)//橫
		{
			setCubeSpace(space,0,0,0);
			setCubeSpace(space,1,1,0);
			setCubeSpace(space,2,1,1);
			setCubeSpace(space,3,0,1);
			state=0;
			if(kickwall(all))//轉了撞牆
			{
					Rotate(all);
				//System.out.println("轉到牆壁理了調整");
			}
			setFloor(2,3);
			setRightFloor(1,2);
			setLeftFloor(0,3);
			
		}
	}

	cubeO(int x,int y)
	{
		cx=x;
		cy=y;
		setCubeSpace(space,0,0,0);
		setCubeSpace(space,1,1,0);
		setCubeSpace(space,2,1,1);
		setCubeSpace(space,3,0,1);
		state=0;
		setFloor(2,3);
		setRightFloor(1,2);
		setLeftFloor(0,3);
	}
	
	
	
	
}