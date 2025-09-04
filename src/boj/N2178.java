package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class N2178 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 미로 배열 선언
        int[][] maze = new int[n][m];
        // 미로 입력받기
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        // 4방향 탐색을 위한 배열 선언
        int[] dr = {-1, 0, 0, 1};
        int[] dc = {0, -1, 1, 0};

        // 큐 선언
        Deque<int[]> queue = new ArrayDeque<>();
        // 시작 좌표 넣기
        queue.addLast(new int[] {0, 0});


        while (!queue.isEmpty()) {
            // 탐색하기 위해 큐를 좌표에서 꺼냄
            int[] curr = queue.remove();
            int r = curr[0];
            int c = curr[1];

            // 4방향 탐색
            for (int i = 0; i < dr.length; i++) {

                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                // 좌표가 배열 범위를 넘어가면 다음 범위 탐색
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                // maze 배열에 거리를 누적 기록할 것이므로, 아직 방문하지 않은 길은 값이 1임.
                // 즉, 1이 아니면 이미 방문한 것임.
                if (maze[nr][nc] != 1) {
                    continue;
                }

                // 위의 조건에 걸리지 않으면 탐색하는 것임.
                // 큐에 다음 좌표를 넣기 전에, 먼저 다음 칸까지의 거리를 maze 배열에 기록해야 함.
                // 다음 칸의 거리는 현재 칸의 거리 + 1임.
                maze[nr][nc]= maze[r][c]+1;
                queue.addLast(new int[] {nr, nc});

            }
        }

        // 결과 출력
        bw.write(Integer.toString(maze[n-1][m-1]));
        bw.flush();
        bw.close();
        br.close();
    }
}