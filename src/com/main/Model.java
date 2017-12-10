package com.main;

import com.model.ParallelAlgorithm;
import com.model.TextFileHandler;

public class Model {
	private ParallelAlgorithm parallelAlgorithm;
	private TextFileHandler textHandler;
	
	public Model () {
		this.parallelAlgorithm = new ParallelAlgorithm(this);
		this.textHandler = new TextFileHandler();
		this.localInput();
	}
	
	public void localInput () {
		this.parallelAlgorithm.levenshteinDistance(
				textHandler.load("sequence1.txt"),
				textHandler.load("sequence2.txt"));
	}
	
	public int getMinimum (int a, int b, int c) {
		int mi;

		mi = a;
		if (b < mi) {
			mi = b;
		}
		if (c < mi) {
			mi = c;
		}
		return mi;
	}

	public void printMatrix (int[][] printData) {
		int rowLength = printData.length;
		int columnLength = printData[0].length;
		
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < columnLength; j++) {
				System.out.print(printData[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
