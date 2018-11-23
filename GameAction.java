import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameAction extends KeyAdapter {

	boolean KeyW;
	boolean KeyA;
	boolean KeyS;
	boolean KeyD;

	boolean KeyUp;
	boolean KeyRight;
	boolean KeyLeft;
	boolean KeyDown;
	boolean run = true;
	Player player;
	cubeManeger CM;
	cube Cube;
	place Place;

	GameAction(Player p, cubeManeger c, place pl) {
		player = p;
		CM = c;
		Place = pl;
	}

	public void keyReleased(KeyEvent e) {
		switch ((int) e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			KeyRight = false;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = false;
			break;
		case KeyEvent.VK_UP:
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = false;
			break;
		case KeyEvent.VK_W:
			KeyW = false;
			break;
		case KeyEvent.VK_A:
			KeyA = false;
			break;
		case KeyEvent.VK_S:
			KeyS = false;
			break;
		case KeyEvent.VK_D:
			KeyD = false;
			break;
		}
	}

	public void keyPressed(KeyEvent e) {
		Cube = CM.getAirCube();// 獲得新方塊

		switch ((int) e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:

			if (!KeyRight) {
				KeyRight = true;
				new Thread(new Runnable() {
					public void run() {
						while (true) {
							if (run) {

								CM.Tetris.my = Toolkit.getDefaultToolkit().getImage("right1.gif");
							} else {
								CM.Tetris.my = Toolkit.getDefaultToolkit().getImage("right2.gif");
							}
							run = !run;
							player.move(Place.getPlace(), player.getSpeed());
							try {
								Thread.sleep(50);
							} catch (Exception ee) {
							}
							;
							if (!KeyRight || !CM.Tetris.Live) {
								CM.Tetris.my = Toolkit.getDefaultToolkit().getImage("P1.gif");
								break;
							}

						}

					}
				}).start();

			}
			break;
		case KeyEvent.VK_LEFT:

			if (!KeyLeft) {
				KeyLeft = true;
				new Thread(new Runnable() {
					public void run() {
						while (true) {
							if (run) {

								CM.Tetris.my = Toolkit.getDefaultToolkit().getImage("left1.gif");
							} else {
								CM.Tetris.my = Toolkit.getDefaultToolkit().getImage("left2.gif");
							}
							run = !run;
							player.move(Place.getPlace(), -player.getSpeed());
							try {
								Thread.sleep(50);
							} catch (Exception ee) {
							}
							;
							if (!KeyLeft || !CM.Tetris.Live) {
								CM.Tetris.my = Toolkit.getDefaultToolkit().getImage("P1.gif");
								break;
							}
						}

					}
				}).start();

			}
			break;
		case KeyEvent.VK_UP:

			if (!KeyUp) {
				KeyUp = true;
				new Thread(new Runnable() {
					public void run() {
						while (true) {
							player.jump(Place.getPlace());
							try {
								Thread.sleep(50);
							} catch (Exception ee) {
							}
							;
							if (!KeyUp)
								break;
						}

					}
				}).start();

			}
			break;
		case KeyEvent.VK_DOWN:

			if (!KeyDown) {
				KeyDown = true;
				new Thread(new Runnable() {
					public void run() {
						while (true) {

							try {
								Thread.sleep(50);
							} catch (Exception ee) {
							}
							;
							if (!KeyDown)
								break;
						}

					}
				}).start();

			}
			break;
		case KeyEvent.VK_W:

			if (!KeyW) {
				KeyW = true;
				new Thread(new Runnable() {
					public void run() {
						new PlayAudio("type.wav").start();
						Cube.Rotate(Place.getPlace());
						try {
							//Thread.sleep(50);
						} catch (Exception ee) {
						}
						;

					}
				}).start();

			}

			break;
		case KeyEvent.VK_A:

			if (!KeyA) {
				KeyA = true;
				new Thread(new Runnable() {

					public void run() {
						while (true) {
							new PlayAudio("MOVE2.wav").start();

							Cube.move(Place.getPlace(), -1);
							try {
								Thread.sleep(200);
							} catch (Exception ee) {
							}
							;
							if (!KeyA) {

								break;
							}
						}

					}
				}).start();

			}
			break;
		case KeyEvent.VK_S:

			if (!KeyS) {
				KeyS = true;
				new Thread(new Runnable() {
					public void run() {
						while (true) {
							Cube.down(Place.getPlace());
							try {
								Thread.sleep(100);
							} catch (Exception ee) {
							}
							;
							if (!KeyS)
								break;
						}

					}
				}).start();

			}
			break;
		case KeyEvent.VK_D:

			if (!KeyD) {

				KeyD = true;
				new Thread(new Runnable() {

					public void run() {
						while (true) {
							new PlayAudio("MOVE2.wav").start();

							Cube.move(Place.getPlace(), 1);
							try {
								Thread.sleep(200);
							} catch (Exception ee) {
							}
							;
							if (!KeyD) {

								break;
							}
						}

					}
				}).start();

			}
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
		e.consume();// 確認這按鈕不會做別的事情
	}

}