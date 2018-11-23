public class cubeZ extends cube
{
	
	public void Rotate(boolean all[][])
	{
		if(state==0)//橫
		{
			setCubeSpace(space,0,0,0);
			setCubeSpace(space,1,-1,0);
			setCubeSpace(space,2,0,1);
			setCubeSpace(space,3,1,1);
			state++;
			if(kickwall(all))//轉了撞牆
			{
					Rotate(all);
				//System.out.println("轉到牆壁理了調整");
			}
			setFloor(1,2,3);
			setRightFloor(0,3);
			setLeftFloor(1,2);
			
		}
		else if(state==1)//直
		{
			setCubeSpace(space,0,0,0);
			setCubeSpace(space,1,1,-1);
			setCubeSpace(space,2,1,0);
			setCubeSpace(space,3,0,1);
			state=0;
			if(kickwall(all))//轉了撞牆
			{
					Rotate(all);
				//System.out.println("轉到牆壁理了調整");
			}
			setFloor(2,3);
			setRightFloor(1,2,3);
			setLeftFloor(0,1,3);
		}
	
	}

	cubeZ(int x,int y)
	{
		cx=x;
		cy=y;
		setCubeSpace(space,0,0,0);
		setCubeSpace(space,1,-1,0);
		setCubeSpace(space,2,0,1);
		setCubeSpace(space,3,1,1);
		state=0;
		setFloor(1,2,3);
		setRightFloor(0,3);
		setLeftFloor(1,2);
	}
	
	
	
	
}