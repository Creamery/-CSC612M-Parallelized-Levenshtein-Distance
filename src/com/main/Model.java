package com.main;

import com.model.Algorithm;
import com.model.ParallelAlgorithm;
import com.model.TextFileHandler;

public class Model {
	private Algorithm algorithm;
	private ParallelAlgorithm parallelAlgorithm;
	private TextFileHandler textHandler;
	
	public Model () {
		this.parallelAlgorithm = new ParallelAlgorithm(this);
		this.algorithm = new Algorithm(this);
		this.textHandler = new TextFileHandler();
		this.localInput();
	}
	
	public void localInput () {
		String stringS = textHandler.load("sequence1.txt");
		String stringT = textHandler.load("sequence2.txt");
		
		System.out.println(stringS.length()+": "+stringS);
		System.out.println(stringT.length()+": "+stringT);
		
		System.out.println();
		this.parallelAlgorithm.levenshteinDistance(stringS, stringT);
		System.out.println();
		this.algorithm.levenshteinDistance(stringS, stringT);
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
