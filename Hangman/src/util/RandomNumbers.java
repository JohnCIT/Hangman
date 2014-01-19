package util;

public class RandomNumbers {
	
	public static int getRandomNumberInRange(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
}
