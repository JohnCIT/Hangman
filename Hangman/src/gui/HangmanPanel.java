package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import controllers.GameController;

public class HangmanPanel extends JPanel{
	
	static int whatToDraw = 0;

	
	// Override the paint method so I can draw the hang man and the word
	public void paint(Graphics g) {
		super.paint(g);
		
		// Draw the word
		drawWord(g);
		
		// Draw the hangman
		drawHangman(g);
		
		
	}
	
	
	
	
	
	
	private void drawWord(Graphics g) {
		
		// Save the default font to reset after
		Font temp = g.getFont();
		
		// Make a new font to draw the String
		Font custom = new Font(Font.MONOSPACED, Font.BOLD, 21);
		
		// Set the font to the graphics
		g.setFont(custom);
		
		
		if (GameController.displayWord.length() != 0) {
			g.drawString(GameController.displayWord, 10, getHeight() - 10);
		}
		else {
			g.setColor(new Color(141, 179, 249));
			g.drawString("RANDOM_WORD", 10, getHeight() - 5);
		}
		
		
		// Reset the font
		g.setFont(temp);		
	}
	
	
	private void drawHangman(Graphics g){
		
		// Convert to graphics 2d
		Graphics2D g2 = (Graphics2D) g;
		
		// get width and height
		int width  				= getWidth();
		int height 				= getHeight();
		int startingPosX 		= width / 20;
		int startingPosY 		= height -50;
		int bottomLineLength 	= startingPosX * 8;
		int verticalLineX 		= (startingPosX * 4)/2;
		int verticalLineTop 	= startingPosY / 6;
		int connectorLength 	= startingPosX * 3;
		int ropeLength 			= verticalLineTop * 2;
		int topLineWidth 		= startingPosX * 16;
		int circleSize 			= 40;
		int neckPos 			= ropeLength + circleSize;
		int bottomOfBody 		= neckPos + 60;
		int midBody 			= neckPos + 15;
		int armLength 			= neckPos + 35;
		
		// Set the thickness of the line
		g2.setStroke( new BasicStroke(6));
		
		if (whatToDraw > 0) {
			// Draws the bottom line
			g2.drawLine(startingPosX, startingPosY, bottomLineLength, startingPosY);
		} 
		
		if (whatToDraw > 1) {
			// Vertical line
			g2.drawLine(verticalLineX, startingPosY,verticalLineX, verticalLineTop);
		}
		
		if (whatToDraw > 2) {
			// Base Connector line
			g2.drawLine((startingPosX * 5), startingPosY, verticalLineX, startingPosY / 2);
		}
		
		if (whatToDraw > 3) {
			// Draw top line
			g2.drawLine(verticalLineX, verticalLineTop, startingPosX * 16, verticalLineTop);
		}
		
		if (whatToDraw > 4) {
			// Draw top connector line
			g2.drawLine(verticalLineX, startingPosY/4, connectorLength, verticalLineTop);
		}
		
		if (whatToDraw > 5) {
			// Draw rope
			g2.drawLine(topLineWidth, verticalLineTop, topLineWidth, ropeLength);
		}
		
		if (whatToDraw > 6) {
			// Draw head
			g2.drawOval(topLineWidth - (circleSize / 2), ropeLength, circleSize, circleSize);
			g2.drawOval(topLineWidth - (circleSize / 2) + 10, ropeLength + 10, 5, 5); // Draw the left eye
			g2.drawOval(topLineWidth - (circleSize / 2) + 25, ropeLength + 10, 5, 5); // Draw the right eye
			g2.drawLine(topLineWidth - (circleSize / 2) + 10, neckPos - 10, topLineWidth + 10, neckPos - 10); // Draw mouth
		}
		
		if (whatToDraw > 7) {
			// Draw body
			g2.drawLine(topLineWidth, neckPos, topLineWidth, bottomOfBody);
		}
		
		if (whatToDraw > 8) {
			// Draw left leg
			g2.drawLine(topLineWidth, bottomOfBody, topLineWidth - 25, bottomOfBody + 50);
		}
		
		if (whatToDraw > 9) {
			// Draw right leg
			g2.drawLine(topLineWidth, bottomOfBody, topLineWidth + 25, bottomOfBody + 50);
		}
		
		if (whatToDraw > 10) {
			// Draw left arm
			g2.drawLine(topLineWidth, midBody, topLineWidth - 25, armLength);			
		}
		
		if (whatToDraw > 11) {
			// Draw right arm
			g2.drawLine(topLineWidth, midBody, topLineWidth + 25, armLength);
		}
		
		
	}
		

}
