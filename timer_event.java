import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class timer_event implements ActionListener
{
	int time=0;
	JLabel Lt;//計時器
	timer_event(JLabel t)
	{
		Lt=t;
	}
	public void actionPerformed(ActionEvent e)
	{
		time++;
		Lt.setText("存活時間 "+time+" 秒");
	}
	public int getTime()
	{
		return time;
	}
	
}