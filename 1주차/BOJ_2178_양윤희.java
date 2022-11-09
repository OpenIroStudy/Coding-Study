import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {

	static class XY {
		int x;
		int y;
		int add;

		public XY(int x, int y, int add) {
			this.x = x;
			this.y = y;
			this.add = add;
		}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] list = new int[N + 1][M + 1];
		int[][] visited = new int[N + 1][M + 1];
		int[] y = { 1, 0, -1, 0 };
		int[] x = { 0, 1, 0, -1 };
		int result = 10000;
		Queue<XY> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				list[i][j] = input.charAt(j) - '0';
			}
		}
		visited[0][0] = 1;
		q.add(new XY(0, 0, 1));
		while (!q.isEmpty()) {
			int xx = q.element().x;
			int yy = q.element().y;
			int add = q.element().add;
			q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = yy + y[i];
				int nx = xx + x[i];
				if (ny < 0 || ny >= N || nx < 0 || nx > M || list[ny][nx] == 0 || visited[ny][nx] == 1)
					continue;
				visited[ny][nx] = 1;
				q.add(new XY(nx, ny, add + 1));
				if (ny == N - 1 && nx == M - 1) {
					if (result > add + 1)
						result = add + 1;
				}
			}
		}
		System.out.println(result);
	}

}
