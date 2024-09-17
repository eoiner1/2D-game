//Eoin McMahon 20387436
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


import util.GameObject;


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

   (MIT LICENSE ) e.g do what you want with this :-)

 * Credits: Kelly Charles (2020)
 */
public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0;

	Model gameworld =new Model();

	private static final int GAME_WIDTH = 1000;
	private static final int GAME_HEIGHT = 1000;
	private boolean isLevel2Selected = false;
	private boolean isLevel3Selected = false;

	private Image gameOverImage;
	private Image gameWonImage;
	private Image level2Background;
	private Image level3Background;

	private ReplayButton replayButton;
	private next nextLevelButton;


	public Viewer(Model World) {
		this.gameworld=World;
		try {
			gameOverImage = ImageIO.read(new File("res/Game_Over.png"));
			gameWonImage = ImageIO.read(new File("res/win-screen.png"));
			level2Background = ImageIO.read(new File("res/63rd.png"));
			level3Background = ImageIO.read(new File("res/level3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		replayButton = new ReplayButton(this);
		replayButton.setVisible(false);
		nextLevelButton = new next(this);
		nextLevelButton.setVisible(false);
		add(replayButton);
		add(nextLevelButton);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {

		this.repaint();
		// TODO Auto-generated method stub

	}

	public void setLevel2Selected(boolean selected) {
		isLevel2Selected = selected;
		gameworld.setIsLevel2Selected(isLevel2Selected);
		gameworld.reset();
		repaint();
	}
	public void setLevel3Selected(boolean selected) {
		isLevel3Selected = selected;
		gameworld.setIsLevel3Selected(isLevel3Selected);
		gameworld.reset();
		repaint();
	}
	public boolean IsLevel2Selected() {
		return isLevel2Selected;
	}

	public boolean IsLevel3Selected() {
		return isLevel3Selected;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		CurrentAnimationTime++;

		if (!gameworld.isGameOver() && !gameworld.isGameWon()) {

			if (isLevel2Selected) {
				drawLevel2(g);
			}
			else if (isLevel3Selected) {
				drawLevel3(g);
			}
			else {
				drawLevel1(g);
			}

			int x = (int) gameworld.getPlayer().getCentre().getX();
			int y = (int) gameworld.getPlayer().getCentre().getY();
			int width = (int) gameworld.getPlayer().getWidth();
			int height = (int) gameworld.getPlayer().getHeight();
			String texture = gameworld.getPlayer().getTexture();


			drawPlayer(x, y, width, height, texture, g);

			int numHearts = gameworld.getNumHearts();
			drawHearts(g, numHearts);


			gameworld.getBullets().forEach((temp) ->
			{
				drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(), g);
			});
			gameworld.getLeftBullets().forEach((temp) ->
			{
				drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(), g);
			});
			gameworld.getRightBullets().forEach((temp) ->
			{
				drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(), g);
			});


			gameworld.getEnemies().forEach((temp) ->
			{
				drawEnemies((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(), g);

			});

			gameworld.getEnemiesRight().forEach((temp) ->
			{
				drawEnemies((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(), g);

			});
		} else if (gameworld.isGameOver()){
				drawGameOver(g);
			if (replayButton != null) {
				int buttonX = (getWidth() - replayButton.getWidth()) / 2; // Center horizontally
				int buttonY = getHeight() - replayButton.getHeight() - 200; // Place above the bottom
				replayButton.setBounds(buttonX, buttonY, replayButton.getWidth(), replayButton.getHeight());
				replayButton.setVisible(true);
			}
		}
		else if (gameworld.isGameWon()){
			SoundPlayer.stopSound();
			drawGameWon(g);
			if (replayButton != null) {
				int buttonX = (getWidth() - replayButton.getWidth()) / 2; // Center horizontally
				int buttonY = getHeight() - replayButton.getHeight() - 200; // Place above the bottom
				replayButton.setBounds(buttonX, buttonY, replayButton.getWidth(), replayButton.getHeight());
				replayButton.setVisible(true);
			}
			if (nextLevelButton != null) {
				int buttonX = (getWidth() - nextLevelButton.getWidth()) / 2; // Center horizontally
				int buttonY = getHeight() - nextLevelButton.getHeight() - 100; // Place above the bottom
				nextLevelButton.setBounds(buttonX, buttonY, nextLevelButton.getWidth(), nextLevelButton.getHeight());
				nextLevelButton.setVisible(true);
			}
		}
	}

	private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%40)/10))*50;
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+49, 50, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void drawLevel1(Graphics g)
	{
		File TextureToLoad = new File("res/o-block.jpg");
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			 g.drawImage(myImage, 0,0, 1000, 1000, 0 , 0, 1000, 1000, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drawLevel2(Graphics g)
	{
		File TextureToLoad = new File("res/63rd.png");
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, 0,0, 1000, 1000, 0 , 0, 1000, 1000, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void drawLevel3(Graphics g)
	{
		File TextureToLoad = new File("res/level3.png");
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, 0,0, 1000, 1000, 0 , 0, 1000, 1000, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//64 by 128
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 63, 127, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) {
		File TextureToLoad = new File(texture);
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%40)/10))*50;
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+49, 50, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void drawHearts(Graphics g, int numHearts) {
		int heartWidth = 30;
		int heartHeight = 30;
		int spacing = 5;
		int x = 10;
		int y = 10;

		// Draw the hearts
		for (int i = 0; i < numHearts; i++) {
			try {
				Image heartImage = ImageIO.read(new File("res/heart.jpg"));
				g.drawImage(heartImage, x + (heartWidth + spacing) * i, y, heartWidth, heartHeight, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void drawGameOver(Graphics g) {
		g.drawImage(gameOverImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
	}
	private void drawGameWon(Graphics g) {
		g.drawImage(gameWonImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
	}



}


/*
 *
 *
 *              VIEWER HMD into the world

                                      .
                                         .
                                             .  ..
                               .........~++++.. .  .
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:.....
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8.....
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,..........
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................
     .................,,,,,,,,,,,,,,,,.......................
       .................................................
           ....................................
               ....................   .


                                                                 GlassGiant.com
                                                                 */
