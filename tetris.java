import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class tetris extends JFrame
{
	JPanel jp=new Full();//繪圖介面
	place Place=new place();//場地建立
	Player player=new Player(Place.getPlace());//小人
	cubeManeger CM;//方塊管理員
	boolean Live;
	boolean Pause;
	Color bgcolor;
	KeyListener KL;
	Image my=Toolkit.getDefaultToolkit().getImage("P1.gif");//主人翁
	JLabel score=new JLabel("分數: 0");
	int ScoreInt=0;
	JLabel Ltime=new JLabel("存活時間: 0 秒");
	timer_event AL;
	Timer timer;
	int MAXSCORE=0;
	int MAXTIME=0;
	tetris()
	{
		super("搖滾泡泡");
		//來點選單吧
		setLayout(null);
		setJMenuBar(new mymenu(this));
		add(jp);//加入繪圖介面
		jp.setBounds(0,50,370,630);
		
		add(score);
		score.setFont(new Font("新細明體",Font.BOLD,20));
		score.setBounds(0,0,130,50);
		score.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		add(Ltime);
		AL=new timer_event(Ltime);
		Ltime.setFont(new Font("新細明體",Font.BOLD,20));
		Ltime.setBounds(130,0,200,50);
		Ltime.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		timer=new Timer(1000,AL);		
		timer.start();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setSize(370,680);
		setVisible(true);
		setResizable(false);
		
		
		Place.setPlayer(player);//與player互相參考
		CM =new cubeManeger(player,Place,this);
		KL=new GameAction(player,CM,Place);
		addKeyListener(KL);
		//new cubeManeger(player,Place,this).getRandomcube();
		
		
                            //throws HeadlessException
	}

	public static void main(String argc[])
	{
		tetris frame=new tetris();
		frame.Live=true;//遊戲開始
		new Thread(frame.CM).start();
	}
	public void setScore(int x)
	{
		ScoreInt+=x;
		score.setText("分數: "+(ScoreInt));	
		
	}
	public void RE()
	{
			if(!Live)//如果遊戲結束
			{
					Live=true;
					remove(jp);
					jp=new Full();
					add(jp);
					jp.setBounds(0,50,370,630);					
					//整套修改jp的動作
					jp.repaint();		
					repaint();
					jp.updateUI();
					
					ScoreInt=0;
					score.setText("分數: "+ScoreInt);	
					
					Place=new place();//場地建立
					player=new Player(Place.getPlace());//小人
					Place.setPlayer(player);//與player互相參考
					CM =new cubeManeger(player,Place,this);
					KL=new GameAction(player,CM,Place);
					addKeyListener(KL);
					//jp.update(getGraphics());	
					
					Ltime.setText("存活時間: 0 秒");
					AL=new timer_event(Ltime);
					timer=new Timer(1000,AL);		
					timer.start();
					Ltime.updateUI();
					jp.repaint();		
					new Thread(CM).start();
					
			}
	}	
	public void die()
	{
			timer.stop();
			removeKeyListener(KL);
			Live=false;
			//System.out.println("LIVE: die!!"+Live);
			if(ScoreInt>MAXSCORE &&AL.getTime()>MAXTIME)
			{
				MAXTIME=AL.getTime();
				MAXSCORE=ScoreInt;
			}
			
	}
	public void Pause()
	{
			timer.stop();
			//System.out.println("LIVE:"+Live);
			if(Live)//活著才能暫停!!很合理的吧
			{
				
				removeKeyListener(KL);
				Pause	=!Pause;
				jp.repaint();
			
			if(!Pause)
			{
				timer.start();
				addKeyListener(KL);
			}
			}
			
	}	
	
	
	
	
	class Full extends JPanel //遊戲用框架
	{
		Image img;
		//double buffer
		
		
		public int x=0,y=0;
		
		
		
		
		Full()
		{
			super();

			
		}
		public void update(Graphics g)
		{
			Image doubleImage;
			Graphics doubleG;
			
			doubleImage=createImage(370,606);		
			doubleG=doubleImage.getGraphics();
			paintComponent(doubleG);
			//doubleG.dispose();
			g.drawImage(doubleImage, 0, 0, null);

//				paintComponent(g);
		}
		
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			g.setColor(bgcolor);
			g.fillRect(0,0,370,630);
			
			img=Toolkit.getDefaultToolkit().getImage("cube.gif");
			for(int i=0;i<4;i++)//畫個方塊
			{
				try
				{	
					g.drawImage(img,(CM.getAirCube().getCubeSpace()[i][0]+CM.getAirCube().getCenter()[0])*30+x,(CM.getAirCube().getCubeSpace()[i][1]+CM.getAirCube().getCenter()[1])*30+y,this);
				}catch(NullPointerException e){};
			}	
			
			//畫個人
			g.drawImage(my,player.cx,player.cy,this);
			
			for(int i=0;i<19;i++)//畫個場地	
			{
				for(int j=0;j<12;j++)
				{
					if(Place.getPlace()[j][i])
					{
						if(i==18||j==0||j==11)
							g.drawImage(Toolkit.getDefaultToolkit().getImage("place.gif"),j*30,i*30,this);//場地
						else
							g.drawImage(img,j*30,i*30,this);//靜置的方塊
					}
				}		
			}
			if(!Live)
			{
				g.setColor(new Color(10,10,10,200));
				g.fillRect(0,0,370,630);
				g.setColor(new Color(255,255,255,200));
				g.setFont(new Font("Arial",Font.BOLD,30));
				g.drawString("Game Over",95,280);
			}
			else if(Pause)
			{
				g.setColor(new Color(10,10,10,200));
				g.fillRect(0,0,370,630);
				g.setColor(new Color(255,255,255,200));
				g.setFont(new Font("Arial",Font.BOLD,30));
				g.drawString("PAUSE",95,280);
			}
			else
			{
				
			}

		}	

	}
}