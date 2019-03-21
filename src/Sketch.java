import java.util.ArrayList;

public class Sketch {
	
	/**
	 * Returns a String representing a single row of a Hangman sketch
	 * @param triesLeft		the number of tries left
	 * @param lineNumber	the desired row of the Hangman sketch
	 * @return	String representing a single row of a Hangman sketch
	 */
	public static String getSketch(int triesLeft, int lineNumber) {
		int sketchNumber = 10 - triesLeft;
		int index = lineNumber - 1;
		ArrayList<String[]> sketches = buildSketchList();
		
		return sketches.get(sketchNumber)[index];		
	}
	
	/**
	 * Returns an ArrayList of Hangman sketches, each consisting of a String[]
	 * containing each row of the sketch.
	 * @return	ArrayList of Hangman sketches
	 */
	public static ArrayList<String[]> buildSketchList() {
		ArrayList<String[]> sketchList = new ArrayList<>();
		String[] sketch0 = new String[9];
		sketch0[0] = ("                  ");		
		sketch0[1] = ("                  ");		
		sketch0[2] = ("                  ");		
		sketch0[3] = ("                  ");		
		sketch0[4] = ("                  ");		
		sketch0[5] = ("                  ");		
		sketch0[6] = ("                  ");		
		sketch0[7] = ("                  ");		
		sketch0[8] = ("                  ");		
		sketchList.add(sketch0);
		
		String[] sketch1 = new String[9];
		sketch1[0] = ("                  ");		
		sketch1[1] = ("                  ");		
		sketch1[2] = ("                  ");		
		sketch1[3] = ("                  ");		
		sketch1[4] = ("                  ");		
		sketch1[5] = ("                  ");		
		sketch1[6] = ("                  ");		
		sketch1[7] = ("                  ");		
		sketch1[8] = ("   --------------|");
		sketchList.add(sketch1);

		String[] sketch2 = new String[9];
		sketch2[0] = ("                 |");		
		sketch2[1] = ("                ||");		
		sketch2[2] = ("                ||");		
		sketch2[3] = ("                ||");		
		sketch2[4] = ("                ||");		
		sketch2[5] = ("                ||");		
		sketch2[6] = ("                ||");		
		sketch2[7] = ("                ||");		
		sketch2[8] = ("   --------------|");
		sketchList.add(sketch2);
		
		String[] sketch3 = new String[9];
		sketch3[0] = ("        ---------|");		
		sketch3[1] = ("                ||");		
		sketch3[2] = ("                ||");		
		sketch3[3] = ("                ||");		
		sketch3[4] = ("                ||");		
		sketch3[5] = ("                ||");		
		sketch3[6] = ("                ||");		
		sketch3[7] = ("                ||");		
		sketch3[8] = ("   --------------|");
		sketchList.add(sketch3);

		String[] sketch4 = new String[9];
		sketch4[0] = ("       .---------|");		
		sketch4[1] = ("       |        ||");		
		sketch4[2] = ("       |        ||");		
		sketch4[3] = ("                ||");		
		sketch4[4] = ("                ||");		
		sketch4[5] = ("                ||");		
		sketch4[6] = ("                ||");		
		sketch4[7] = ("                ||");		
		sketch4[8] = ("   --------------|");
		sketchList.add(sketch4);

		String[] sketch5 = new String[9];
		sketch5[0] = ("       .---------|");		
		sketch5[1] = ("       |        ||");		
		sketch5[2] = ("       |        ||");		
		sketch5[3] = ("       ()       ||");		
		sketch5[4] = ("                ||");		
		sketch5[5] = ("                ||");		
		sketch5[6] = ("                ||");		
		sketch5[7] = ("                ||");		
		sketch5[8] = ("   --------------|");
		sketchList.add(sketch5);

		String[] sketch6 = new String[9];
		sketch6[0] = ("       .---------|");		
		sketch6[1] = ("       |        ||");		
		sketch6[2] = ("       |        ||");		
		sketch6[3] = ("       ()       ||");		
		sketch6[4] = ("       ||       ||");		
		sketch6[5] = ("                ||");		
		sketch6[6] = ("                ||");		
		sketch6[7] = ("                ||");		
		sketch6[8] = ("   --------------|");
		sketchList.add(sketch6);

		String[] sketch7 = new String[9];
		sketch7[0] = ("       .---------|");		
		sketch7[1] = ("       |        ||");		
		sketch7[2] = ("       |        ||");		
		sketch7[3] = ("       ()       ||");		
		sketch7[4] = ("      /||       ||");		
		sketch7[5] = ("                ||");		
		sketch7[6] = ("                ||");		
		sketch7[7] = ("                ||");		
		sketch7[8] = ("   --------------|");
		sketchList.add(sketch7);

		String[] sketch8 = new String[9];
		sketch8[0] = ("       .---------|");		
		sketch8[1] = ("       |        ||");		
		sketch8[2] = ("       |        ||");		
		sketch8[3] = ("       ()       ||");		
		sketch8[4] = ("      /||\\      ||");		
		sketch8[5] = ("                ||");		
		sketch8[6] = ("                ||");		
		sketch8[7] = ("                ||");		
		sketch8[8] = ("   --------------|");
		sketchList.add(sketch8);

		String[] sketch9 = new String[9];
		sketch9[0] = ("       .---------|");		
		sketch9[1] = ("       |        ||");		
		sketch9[2] = ("       |        ||");		
		sketch9[3] = ("       ()       ||");		
		sketch9[4] = ("      /||\\      ||");		
		sketch9[5] = ("       /        ||");		
		sketch9[6] = ("      /         ||");		
		sketch9[7] = ("                ||");		
		sketch9[8] = ("   --------------|");
		sketchList.add(sketch9);

		String[] sketch10 = new String[9];
		sketch10[0] = ("       .---------|");		
		sketch10[1] = ("       |        ||");		
		sketch10[2] = ("       |        ||");		
		sketch10[3] = ("       ()       ||");		
		sketch10[4] = ("      /||\\      ||");		
		sketch10[5] = ("       /\\       ||");		
		sketch10[6] = ("      /  \\      ||");		
		sketch10[7] = ("                ||");		
		sketch10[8] = ("   --------------|");
		sketchList.add(sketch10);
		
		return sketchList;
	}
	
	
	
}
