package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


//아직 다 안품


public class BOJ_14502_양윤희 {

	static class xy {
		int x;
		int y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int M;
	static int[][] arr = new int[10][10];

	static int maxSize = 0;
	static List<xy> z = new ArrayList<>();
	static Queue<xy> q = new LinkedList<>();

	static int[] y = { 1, 0, -1, 0 };
	static int[] x = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		boolean b = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 0) {
					z.add(new xy(j, i));
				}
			}
		}
		for (int i = 0; i < z.size() - 2; i++) {
			xy firstzero = z.get(i);
			arr[firstzero.y][firstzero.x] = 1;
			for (int j = i + 1; j < z.size() - 1; j++) {
				xy scdzero = z.get(j);
				arr[scdzero.y][scdzero.x] = 1;
				for (int k = j + 1; k < z.size(); k++) {
					xy thdzero = z.get(k);
					arr[thdzero.y][thdzero.x] = 1;
					virus();
					arr[thdzero.y][thdzero.x] = 0;
				}
				arr[scdzero.y][scdzero.x] = 0;
			}
			arr[firstzero.y][firstzero.x] = 0;
		}

		System.out.println(maxSize);
	}

	public static void virus() {
		Queue<xy> qq = new LinkedList<>();
		int[][] cpyarr = new int[10][10];
		cpyarr = arr.clone();
		qq = q;
		int safe = 0;

		while (!qq.isEmpty()) {
			xy virus = qq.poll();
			for (int a = 0; a < 4; a++) {
				int ny = virus.y + y[a];
				int nx = virus.x + x[a];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M || cpyarr[ny][nx] == 2 || cpyarr[ny][nx] == 1)
					continue;
				if (cpyarr[ny][nx] == 0) {
					cpyarr[ny][nx] = 2;
					qq.add(new xy(ny, nx));
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cpyarr[i][j] == 0)
					safe++;
			}
		}
		if (safe > maxSize)
			maxSize = safe;
	}
}
