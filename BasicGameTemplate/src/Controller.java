//Eoin McMahon 20387436
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

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
 */ 

//Singeton pattern
public class Controller implements KeyListener {
        
	   private static boolean KeyAPressed= false;
	   private static boolean KeySPressed= false;
	   private static boolean KeyDPressed= false;
	   private static boolean KeyWPressed= false;
	   private static boolean KeySpacePressed= false;

	   private static boolean KeyRPressed= false;

		private static boolean KeyUpPressed= false;
		private static boolean KeyLeftPressed= false;
		private static boolean KeyRightPressed= false;
	   
	   private static final Controller instance = new Controller();
	   
	 public Controller() { 
	}
	 
	 public static Controller getInstance(){
	        return instance;
	    }
	   
	@Override
	public void keyTyped(KeyEvent e) { 
		 
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{ 
		switch (e.getKeyChar()) 
		{
			case 'a':setKeyAPressed(true);break;  
			case 's':setKeySPressed(true);break;
			case 'w':setKeyWPressed(true);break;
			case 'd':setKeyDPressed(true);break;
			case ' ':setKeySpacePressed(true);break;
			case 'r':setKeyRPressed(true);break;
			default:
		        break;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setKeyUpPressed(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			setKeyLeftPressed(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			setKeyRightPressed(true);
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		switch (e.getKeyChar()) 
		{
			case 'a':setKeyAPressed(false);break;  
			case 's':setKeySPressed(false);break;
			case 'w':setKeyWPressed(false);break;
			case 'd':setKeyDPressed(false);break;
			case ' ':setKeySpacePressed(false);break;
			case 'r':setKeyRPressed(false);break;
		    default:
		        break;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setKeyUpPressed(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			setKeyLeftPressed(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			setKeyRightPressed(false);
		}
	
	}


	public boolean isKeyAPressed() {
		return KeyAPressed;
	}


	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}


	public boolean isKeySPressed() {
		return KeySPressed;
	}


	public void setKeySPressed(boolean keySPressed) {
		KeySPressed = keySPressed;
	}


	public boolean isKeyDPressed() {
		return KeyDPressed;
	}


	public void setKeyDPressed(boolean keyDPressed) {
		KeyDPressed = keyDPressed;
	}


	public boolean isKeyWPressed() {
		return KeyWPressed;
	}


	public void setKeyWPressed(boolean keyWPressed) {
		KeyWPressed = keyWPressed;
	}


	public boolean isKeySpacePressed() {
		return KeySpacePressed;
	}

	public boolean isKeyRPressed() {
		return KeyRPressed;
	}
	public void setKeyRPressed(boolean keyRPressed) {
		KeyRPressed = keyRPressed;
	}


	public void setKeySpacePressed(boolean keySpacePressed) {
		KeySpacePressed = keySpacePressed;
	}

	public boolean isKeyUpPressed() {
		return KeyUpPressed;
	}
	public void setKeyUpPressed(boolean keyUpPressed) {
		KeyUpPressed = keyUpPressed;
	}
	public boolean isKeyLeftPressed() {
		return KeyLeftPressed;
	}
	public void setKeyLeftPressed(boolean keyLeftPressed) {
		KeyLeftPressed = keyLeftPressed;
	}
	public boolean isKeyRightPressed() {
		return KeyRightPressed;
	}
	public void setKeyRightPressed(boolean keyRightPressed) {
		KeyRightPressed = keyRightPressed;
	}
	 
}

/*
 * 
 * KEYBOARD :-) . can you add a mouse or a gamepad 

 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@

  @@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@     @@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@     @@@     @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@     @@@   W   @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@@    @@@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N@@@@@@@@@@@@@@@@@@@@@@@@@@@

@@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@    

@@@   A   @@@  S     @@  D     @@      @@@     @@@     @@@     @@@     @@@     @@@    

@@@@ @  @@@@@@@@@@@@ @@@@@@@    @@@@@@@@@@@@    @@@@@@@@@@@@     @@@@   @@@@@   

    @@@     @@@@    @@@@    @@@@    $@@@     @@@     @@@     @@@     @@@     @@@

    @@@ $   @@@      @@      @@ /Q   @@ ]M   @@@     @@@     @@@     @@@     @@@

    @@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@       @@@                                                @@@       @@@       @

@       @@@              SPACE KEY       @@@        @@ PQ     

@       @@@                                                @@@        @@        

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 
 * 
 * 
 * 
 */
