/**
 * 
 * Description : obtains image to be loaded into game
 * References : N/A
 * @author jeremy limson, sarah ernst, quinn hobson
 */
package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader{
		
		private BufferedImage image;
		
		public BufferedImage loadImage(String path) throws IOException{
			image = ImageIO.read(getClass().getResource(path));
			return image;
	}

}
