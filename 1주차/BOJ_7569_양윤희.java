package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_7569_양윤희 {

	static class XY {
		int x;
		int y;
		int h;
		int day;

		public XY(int y, int x, int h, int day) {
			this.x = x;
			this.y = y;
			this.h = h;
			this.day = day;
		}
	}

	public static int N; // y
	public static int M; // x
	public static int H; // h
	public static int tomato;
	public static int result;
	public static int[] x = { 1, 0, -1, 0 };
	public static int[] y = { 0, 1, 0, -1 };
	public static int[][][] arr = new int[101][101][101];
	public static int[][][] visited = new int[101][101][101];
	public static Queue<XY> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		int height = 0;
		int yy = 0;
		int index = 1;
		for (int i = 0; i < N * H; i++) {
			if (H != 1 && i == 	N * index) {
				index++;
				yy = 0;
				height++;
			}
			for (int j = 0; j < M; j++) {
				arr[height][yy][j] = sc.nextInt();
				if (arr[height][yy][j] == 0)
					tomato++;
				else if (arr[height][yy][j] == 1) {
					visited[height][yy][j] = 1;
					q.add(new XY(yy, j, height, 0));
				}
			}
			yy++;
		}
		if(tomato ==0) {
			System.out.println(0);
			return;
		}
		while (!q.isEmpty()) {
			XY xy = q.poll();

			for (int i = 0; i < 4; i++) { // 동서남북
				int ny = xy.y + y[i];
				int nx = xy.x + x[i];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[xy.h][ny][nx] == 1 || arr[xy.h][ny][nx] == -1)
					continue;
				tomato--;
				q.add(new XY(ny, nx, xy.h, xy.day + 1));
				visited[xy.h][ny][nx] = 1;
				arr[xy.h][ny][nx] = 1;

			}
			if (xy.h + 1 < H && visited[xy.h + 1][xy.y][xy.x] != 1 && arr[xy.h + 1][xy.y][xy.x] != -1) { // 아래
				tomato--;
				q.add(new XY(xy.y, xy.x, xy.h + 1, xy.day + 1));
				visited[xy.h + 1][xy.y][xy.x] = 1;
				arr[xy.h + 1][xy.y][xy.x] = 1;
			}
			if (xy.h - 1 >= 0 && visited[xy.h - 1][xy.y][xy.x] != 1 && arr[xy.h - 1][xy.y][xy.x] != -1) { // 위
				tomato--;
				q.add(new XY(xy.y, xy.x, xy.h - 1, xy.day + 1));
				visited[xy.h - 1][xy.y][xy.x] = 1;
				arr[xy.h - 1][xy.y][xy.x] = 1;
			}

			if (tomato <= 0) { // 전부 숙성완
				result = xy.day + 1;
				break;
			}
		}
		if (tomato > 0)
			System.out.println(-1);
		else
			System.out.println(result);
	}
}
