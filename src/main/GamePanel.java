package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 Tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this,keyH);
    
//    Set player's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;
    
    
    public GamePanel() {
    	
    	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    	this.setBackground(Color.black);
    	this.setDoubleBuffered(true);
    	this.addKeyListener(keyH);
    	this.setFocusable(true);
    }
    
    public void startGameThread() {
    	
    	gameThread = new Thread(this);
    	gameThread.start();
    }
    
    @Override
    public void run() {
    	
    	double drawInterval = 1000000000/FPS; //0.01666 seconds
    	double nextDrawTime = System.nanoTime() + drawInterval;
    	
    	while(gameThread != null) {
    		
    		//long currentTime = System.nanoTime();
    		//System.out.println("current Time:"+currentTime);
    		//long currentTime2 = System.currentTimeMillis();
    		
   // 		System.out.println("The game loop is running");
    		
    		//1. UPDATE: update information such as character positions
    		update();
    		
    		//2. DRAW: draw the screen with the updated inforamtion
    		repaint();
    		
    		
    		
    		try {
    			double remainingTime = nextDrawTime - System.nanoTime();
    			remainingTime = remainingTime/1000000;
    			
    			if(remainingTime < 0) {
    				remainingTime = 0;
    			}
    			
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    public void update() {
    	
    	player.update();
    }
//    	if(keyH.upPressed == true) {
//    		//playerY -= playerSpeed;
//    		playerY = playerY - playerSpeed;
//    	}
//    	else if(keyH.downPressed == true) {
//    		playerY += playerSpeed;
//    	}
//    	else if(keyH.leftPressed == true) {
//    		playerX -= playerSpeed;
//    	}
//    	else if(keyH.rightPressed == true) {
//    		playerX += playerSpeed;
//    	}
    
    public void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
    	
    	Graphics2D g2 = (Graphics2D)g;
    	
    	tileM.draw(g2);
    	
    	player.draw(g2);
//    	g2.setColor(Color.white);
//    	
//    	g2.fillRect(playerX, playerY,  tileSize, tileSize);
    	
    	g2.dispose();
    }
}
