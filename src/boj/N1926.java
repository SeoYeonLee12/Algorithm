package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class N1926 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 도화지(=그래프) 선언
        // 도화지의 크기 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] drawing = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                drawing[i][j] = Integer.parseInt(st.nextToken());
            }
//            System.out.println(Arrays.toString(drawing[i]));
        }

        // 방문여부 저장할 배열 선언
        boolean[][] visited = new boolean[n][m];

        // 사방향 탐색을 위한 일차원 배열 선언
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};


        // 처음 탐색을 시작
        int nr = 0;
        int nc = 0;

        // 그림의 넓이를 가질 변수

        int picCnt = 0;
        // 가장 큰 그림의 넓이를 가질 변수
        int maxExtent = 0;
        int currExtent = 0;

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {

                if (drawing[j][k] == 1 && !visited[j][k]) {

                    picCnt++;

                    // 방문 표시
                    // 스택 선언
                    Stack<int[]> stack = new Stack<>();
                    visited[j][k] = true;
                    stack.push(new int[] {j, k});

                    currExtent = 0;

                    // 도화지 탐색 시작
                    while (!stack.isEmpty()) {
                        int[] curr = stack.pop();
                        currExtent++;

                        // 4방향 탐색
                        for (int i = 0; i < dr.length; i++) {
                            nr = curr[0] + dr[i];
                            nc = curr[1] + dc[i];

                            // 배열 범위 안에 있는지 확인
                            if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                                continue;
                            }

                            // 이미 방문했거나 인접한 노드가 없는지 확인
                            if (visited[nr][nc] || drawing[nr][nc] != 1) {
                                continue;
                            }

                            // 위를 제외하면 계속 방문
                            visited[nr][nc] = true;
                            stack.push(new int[] {nr, nc});
                        }

                    }

                    maxExtent= Math.max(currExtent, maxExtent);
                }

            }
        }
        bw.write(picCnt+"\n"+maxExtent);
        bw.flush();
    }
}