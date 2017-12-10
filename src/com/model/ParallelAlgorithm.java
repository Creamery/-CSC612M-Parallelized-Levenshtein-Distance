package com.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import com.main.Model;
import com.main.ComputeDistance;

/**
 * Algorithm referenced from:
 * 	Levenshtein Distance, in Three Flavors
 * 	by Michael Gilleland, Merriam Park Software
 * 	https://people.cs.pitt.edu/
 * 
 * @author Candy
 *
 */
public class ParallelAlgorithm {

	private Model model;
	private int[][] matrix;
	private String stringS;
	private String stringT;
	private int n;
	private int m;
	
	private int i;
	private int j;
	
	
	public ParallelAlgorithm(Model model) {
		this.model = model;
	}
	
	
	public int levenshteinDistance(String s, String t) {		
		this.stringS = s;
		this.stringT = t;
		
	    // Step 1
		n = s.length();
		m = t.length();
		
		if(n == 0) {
			return m;
		}
		
		if(m == 0) {
			return n;
		}
		
		this.matrix = new int [n+1][m+1];

		// Step 2
		for(i = 0; i <= n; i++) {
			matrix[i][0] = i;
		}

		for(j = 0; j <= m; j++) {
			matrix[0][j] = j;
		}

		// Step 2.1 Initialize other values as -1, which will indicate that it's not yet finished
		for(i = 1; i <= n; i++) {
			for(j = 1; j <= m; j++) {
				matrix[i][j] = -1;
			}
		}
		
		
		// Step 3
		ArrayList<ComputeDistance> listComputeDistance = new ArrayList<ComputeDistance>();
		ComputeDistance computeDistance;
		
		for(i = 1; i <= n; i++) {
			for(j = 1; j <= m; j++) {
				computeDistance = new ComputeDistance(this, i, j);
				listComputeDistance.add(computeDistance);
			}
		}
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, r -> {
		    Thread thread = Executors.defaultThreadFactory().newThread(r);
		    thread.setDaemon(true);
		    return thread;
		});

		Long executionTime = 0l;
		try {
			
			List<Future<Long>> timeList = executorService.invokeAll(listComputeDistance);
			executorService.shutdown();
			executorService.shutdownNow();
			
			for (Future<Long> time : timeList) {
				executionTime += time.get();
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
//		model.printMatrix(this.matrix);
		System.out.println("PARALLEL: "+matrix[n][m]);
		double seconds = (double)executionTime / 1000000000.0;
		NumberFormat formatter = new DecimalFormat("#0.0000000000");
		System.out.println("Execution Time (ns): "+executionTime);
		System.out.println("Execution Time (sec): "+formatter.format(seconds));
		return matrix[n][m];
	}


	public int[][] getMatrix() {
		return matrix;
	}


	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}


	public String getStringS() {
		return stringS;
	}


	public String getStringT() {
		return stringT;
	}


	public void setStringS(String stringS) {
		this.stringS = stringS;
	}


	public void setStringT(String stringT) {
		this.stringT = stringT;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}
}
