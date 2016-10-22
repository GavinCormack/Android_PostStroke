package com.teamj.poststroke;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;



/**
 * 
 * @author Gavin, Stephen, Eanna
 * 
 * Gavin worked on linking the graphical side to the java side and worked on displaying 
 * the canvas in MyDrawView to the screen
 *
 * Stephen worked on the SharedPreferences part so we could store and retrieve 
 * details about the user. 
 * 
 * Eanna worked on the Panic button side, sending a text message and displaying the user's
 * details afterwards. He also worked on designing the screen, and designing the graph.
 */
public class Scores extends Activity{

	static float[] points = {0,0,0,0,0,0,0,0,0,0,
							0,0,0,0,0,0,0,0,0,0,
							0,0,0,0,0,0,0,0,0,0,
							0,0,0,0,0,0,0,0,0,0,
							0,0,0,0,0,0,0,0,0,0,
							0,0,0,0,0,0,0,0,0,0}; //array of scores
	
	static int num = points.length;
	
	SharedPreferences savedScores;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores);
		
		savedScores = getSharedPreferences("com.DrawTest", Activity.MODE_PRIVATE);
		editor = savedScores.edit();
		
		//sets values of array equal to the saved score for each level
		points[0] = savedScores.getFloat("easyone", 0);
		points[1] = savedScores.getFloat("easytwo", 0);
		points[2] = savedScores.getFloat("easythree", 0);
		points[3] = savedScores.getFloat("easyfour", 0);
		points[4] = savedScores.getFloat("easyfive", 0);
		points[5] = savedScores.getFloat("easysix", 0);
		points[6] = savedScores.getFloat("easyseven", 0);
		points[7] = savedScores.getFloat("easyeight", 0);
		points[8] = savedScores.getFloat("easynine", 0);
		points[9] = savedScores.getFloat("easyten", 0);
		points[10] = savedScores.getFloat("easyeleven", 0);
		points[11] = savedScores.getFloat("easytwelve", 0);
		points[12] = savedScores.getFloat("easythirteen", 0);
		points[13] = savedScores.getFloat("easyfourteen", 0);
		points[14] = savedScores.getFloat("easyfifteen", 0);
		points[15] = savedScores.getFloat("easysixteen", 0);
		points[16] = savedScores.getFloat("easyseventeen", 0);
		points[17] = savedScores.getFloat("easyeighteen", 0);
		points[18] = savedScores.getFloat("easynineteen", 0);
		points[19] = savedScores.getFloat("easytwenty", 0);
		points[20] = savedScores.getFloat("mediumone", 0);
		points[21] = savedScores.getFloat("mediumtwo", 0);
		points[22] = savedScores.getFloat("mediumthree", 0);
		points[23] = savedScores.getFloat("mediumfour", 0);
		points[24] = savedScores.getFloat("mediumfive", 0);
		points[25] = savedScores.getFloat("mediumsix", 0);
		points[26] = savedScores.getFloat("mediumseven", 0);
		points[27] = savedScores.getFloat("mediumeight", 0);
		points[28] = savedScores.getFloat("mediumnine", 0);
		points[29] = savedScores.getFloat("mediumten", 0);
		points[30] = savedScores.getFloat("mediumeleven", 0);
		points[31] = savedScores.getFloat("mediumtwelve", 0);
		points[32] = savedScores.getFloat("mediumthirteen", 0);
		points[33] = savedScores.getFloat("mediumfourteen", 0);
		points[34] = savedScores.getFloat("mediumfifteen", 0);
		points[35] = savedScores.getFloat("mediumsixteen", 0);
		points[36] = savedScores.getFloat("mediumseventeen", 0);
		points[37] = savedScores.getFloat("mediumeighteen", 0);
		points[38] = savedScores.getFloat("mediumnineteen", 0);
		points[39] = savedScores.getFloat("mediumtwenty", 0);
		points[40] = savedScores.getFloat("hardone", 0);
		points[41] = savedScores.getFloat("hardtwo", 0);
		points[42] = savedScores.getFloat("hardthree", 0);
		points[43] = savedScores.getFloat("hardfour", 0);
		points[44] = savedScores.getFloat("hardfive", 0);
		points[45] = savedScores.getFloat("hardsix", 0);
		points[46] = savedScores.getFloat("hardseven", 0);
		points[47] = savedScores.getFloat("hardeight", 0);
		points[48] = savedScores.getFloat("hardnine", 0);
		points[49] = savedScores.getFloat("hardten", 0);
		points[50] = savedScores.getFloat("hardeleven", 0);
		points[51] = savedScores.getFloat("hardtwelve", 0);
		points[52] = savedScores.getFloat("hardthirteen", 0);
		points[53] = savedScores.getFloat("hardfourteen", 0);
		points[54] = savedScores.getFloat("hardfifteen", 0);
		points[55] = savedScores.getFloat("hardsixteen", 0);
		points[56] = savedScores.getFloat("hardseventeen", 0);
		points[57] = savedScores.getFloat("hardeighteen", 0);
		points[58] = savedScores.getFloat("hardnineteen", 0);
		points[59] = savedScores.getFloat("hardtwenty", 0);
	
		
	}
}
