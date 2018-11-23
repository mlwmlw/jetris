import java.io.*;
import javax.sound.sampled.*;

public class PlayAudio extends Thread {

	String Audio;
	PlayAudio(String Audio)
	{
		this.Audio=Audio;
	}
	public void run() {
		try 
		{

			// 建立 File 物件
			File sf = new File(Audio);

			// 取得聲音輸入串流
			AudioInputStream astr = AudioSystem.getAudioInputStream(sf);

			// 取得聲音形式
			AudioFormat afmt = astr.getFormat();

			// 建立訊號線資訊物件
			DataLine.Info inf = new DataLine.Info(SourceDataLine.class,afmt);

			// 取得符合指定訊號線資訊的訊號線
			SourceDataLine l = (SourceDataLine) AudioSystem.getLine(inf);

			// 以指定形式開啟訊號線
			l.open(afmt);

			// 開始訊號線的讀寫
			l.start();

			// 讀寫緩衝區
			byte[] buf = new byte[65536];

			// 從聲音串流讀入資料寫入混音器
			for( int n=0; (n=astr.read(buf,0,buf.length))>0; )
			{
					l.write(buf,0,n);
			}

			// 清掉混音器內的資料
			l.drain();

			// 關閉
			l.close();

		} catch (Exception e) {
		e.printStackTrace();
	}


	}
}
