import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class mymenu extends JMenuBar
{
		JMenu menu, submenu;
		JMenuItem menuItem;
		Container cont;
		tetris tet;
		JColorChooser tcc;
		mymenu(tetris t)
		{
			super();
			tet=t;
			cont =t.getContentPane();
			
			menu = new JMenu("遊戲");
			add(menu);
			menuItem = new JMenuItem("新遊戲");
			menu.add(menuItem);
			menuItem.addActionListener(new rePlay());
			menuItem = new JMenuItem("暫停/開始");
			menu.add(menuItem);
			menuItem.addActionListener(new Pause());
			menu.addSeparator();

			menuItem = new JMenuItem("最佳紀錄");
			menu.add(menuItem);
			menuItem.addActionListener(new record());
			menuItem = new JMenuItem("選擇背景顏色");
			menuItem.addActionListener(new bgc());
			menu.add(menuItem);					
			menu.addSeparator();
			menuItem = new JMenuItem("結束");
			menu.add(menuItem);
		
			menu = new JMenu("說明");
			add(menu);
			menuItem = new JMenuItem("遊戲方式");
			menu.add(menuItem);
			menuItem.addActionListener(new F1());
			menu.addSeparator();
			menuItem = new JMenuItem("關於作者");
			menu.add(menuItem);		
			menuItem.addActionListener(new hello());
			
			menu.addSeparator();
		
		}
		class bgc implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				tcc=new JColorChooser(Color.black);
				JColorChooser.createDialog(cont,"選擇背景顏色",true,tcc,new bgcevent(),null).setVisible(true);
				tet.update(tet.getGraphics());
				
			}
			
		}
		class bgcevent implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				tet.bgcolor=tcc.getColor();
			}
			
		}
		class rePlay implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				tet.RE();
			}
			
		}
		class Pause implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{

					tet.Pause();

			}
			
		}
		class record implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
					tet.Pause();
					JOptionPane.showMessageDialog(tet.getContentPane(), "最佳紀錄 分數: "+tet.MAXSCORE+" 分　存活時間: "+tet.MAXTIME+" 秒");
			}
			
		}
		class F1 implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
					tet.Pause();
					JOptionPane.showMessageDialog(tet.getContentPane(), "A W S D 控制俄羅斯方塊 上左右控制小朋友\n小朋友不能碰到落下之方塊 ");
			}
			
		}
		class hello implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
					tet.Pause();
					JOptionPane.showMessageDialog(tet.getContentPane(), "四資工二甲\n 哈囉 我是mlwmlw~~~  ");
			}
			
		}


}
