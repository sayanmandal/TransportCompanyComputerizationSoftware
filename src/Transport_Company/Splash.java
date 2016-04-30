package Transport_Company;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;

public class Splash{
	public static SplashScreen loadingScreen;
	public static java.awt.geom.Rectangle2D.Double loadingTextArea;
	public static java.awt.geom.Rectangle2D.Double loadingProgressArea;
	public static Graphics2D loadingGraphics;
	
	public static void main(String[] args){
		
		loadingMethod();
		mainMethod();
		
		if(loadingScreen!=null){
			loadingScreen.close();
		}
	}
	
	public static void loadingMethod(){
		loadingScreen = SplashScreen.getSplashScreen();
		if(loadingScreen != null){
			Dimension dim = loadingScreen.getSize();
			int ht = dim.height;
			int wd = dim.width;
			
			loadingTextArea = new Rectangle2D.Double(15, ht*0.7, wd*0.4, 30);
			loadingProgressArea = new Rectangle2D.Double(15, ht*0.85, wd*0.4, 25);
			
			loadingGraphics = loadingScreen.createGraphics();
		}
	}
	
	public static void loadingText(String string){
		if(loadingScreen != null){
			loadingGraphics.setPaint(Color.CYAN);
			loadingGraphics.fill(loadingTextArea);
			
			loadingGraphics.setPaint(Color.BLACK);
			loadingGraphics.drawString(string, (int) loadingTextArea.getX()+10, (int) loadingTextArea.getY()+10);
			
			loadingScreen.update();
		}
	}
	
	public static void loadingprogress(int prog){
		if(loadingScreen != null){
			loadingGraphics.setPaint(Color.lightGray);
			loadingGraphics.fill(loadingProgressArea);
			
			loadingGraphics.setPaint(Color.BLUE);
			loadingGraphics.draw(loadingProgressArea);
			
			int x = (int) loadingProgressArea.getMinX();
			int y = (int) loadingProgressArea.getMinY();
			
			int width = (int) loadingProgressArea.getWidth();
			int height = (int) loadingProgressArea.getHeight();
			
			int doneprog = prog*width/100;
			
			loadingGraphics.setPaint(Color.BLUE);
			loadingGraphics.fillRect(x , y , doneprog, height);
			
			loadingScreen.update();
		}
	}
	
	public static void mainMethod(){
		for(int i = 1 ; i <= 5; i++){
			loadingText("Loading Resources "+i);
			loadingprogress(i*10);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}