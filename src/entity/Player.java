package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;   //players position on the world map
		worldY = gp.tileSize * 21;    //same as above
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
		
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
//	public void update() {
//		
//		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
//			if(keyH.upPressed == true) {
//				direction = "up";
//	    		//playerY -= playerSpeed;	    		
//	    	}
//	    	else if(keyH.downPressed == true) {
//	    		direction = "down";	    		
//	    	}
//	    	else if(keyH.leftPressed == true) {
//	    		direction = "left";
//	    	}
//	    	else if(keyH.rightPressed == true) {
//	    		direction = "right";	
//	    	}
//			
//			//CHECK TILE COLLISION
//			boolean collisionOn = false;
//			gp.cChecker.checkTile(this);
//			
//			//IF COLLIAION IS FALSE, PLAYER CAN MOVE
//			if(collisionOn == false) {
//				
//				switch(direction) {
//				case "up":
//					worldY = worldY - speed;
//					break;
//				case "down":
//					worldY = worldY + speed;
//					break;
//				case "left":
//					worldX = worldX - speed;
//					break;
//				case "right":
//					worldX = worldX + speed;
//					break;
//				}
//			}
//			
//			spriteCounter++;
//			if(spriteCounter > 12) {
//				if(spriteNum == 1) {
//					spriteNum = 2;
//				}
//				else if(spriteNum == 2) {
//					spriteNum = 1;
//				}
//				spriteCounter = 0;
//		}
//		if(keyH.upPressed == true) {
//			direction = "up";
//    		//playerY -= playerSpeed;
//    		y = y - speed;
//    	}
//    	else if(keyH.downPressed == true) {
//    		direction = "down";
//    		y = y + speed;
//    	}
//    	else if(keyH.leftPressed == true) {
//    		direction = "left";
//    		x = x - speed;
//    	}
//    	else if(keyH.rightPressed == true) {
//    		direction = "right";
//    		x = x + speed;
//    	}
//		
//		spriteCounter++;
//		if(spriteCounter > 12) {
//			if(spriteNum == 1) {
//				spriteNum = 2;
//			}
//			else if(spriteNum == 2) {
//				spriteNum = 1;
//			}
//			spriteCounter = 0;
	public void update() {
	    if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
	        if (keyH.upPressed) {
	            direction = "up";
	        } else if (keyH.downPressed) {
	            direction = "down";
	        } else if (keyH.leftPressed) {
	            direction = "left";
	        } else if (keyH.rightPressed) {
	            direction = "right";
	        }

	        // Check for collision
	        boolean collisionOn = false;
	        gp.cChecker.checkTile(this); // Check collision

	        // If no collision, move the player
	        if (!collisionOn) {
	            switch (direction) {
	                case "up":
	                    worldY -= speed;
	                    break;
	                case "down":
	                    worldY += speed;
	                    break;
	                case "left":
	                    worldX -= speed;
	                    break;
	                case "right":
	                    worldX += speed;
	                    break;
	            }
	        }

	        // Handle sprite animation
	        spriteCounter++;
	        if (spriteCounter > 12) {
	            if (spriteNum == 1) {
	                spriteNum = 2;
	            } else if (spriteNum == 2) {
	                spriteNum = 1;
	            }
	            spriteCounter = 0;
	        }
	    }
	}

	public void draw(Graphics2D g2) {
//           g2.setColor(Color.white);
//    	
//    	   g2.fillRect(x, y, gp.tileSize, gp.tileSize);
//    	
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			 break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}

}
