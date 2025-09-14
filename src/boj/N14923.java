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

public class N14923 {

    static int N, M;
    static int[] start;
    static int[] end;
    static int[][] maze;

    // 최단 시간을 기록할 변수
    // 벽 안 부수는 최단 거리 탐색
    static int[][] visited1;
    // 벽 부수는 최단 거리 탐색 배열
    static int[][] visited2;

    static Deque<int[]> queue;
    // 탈출 확인 변수
    static boolean isEscape = true;
    // 탈출 시간 저장할 변수
    static int ans1 = -1;
    static int ans2 = -1;

    // 델타 탐색 배열 정의 => 인접이 나와 맞닿아있는 모든 좌표
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int turn = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 미로 길이 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 시작 길이 입력받기
        st = new StringTokenizer(br.readLine());
        start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        // 탈출로 입력 받기
        st = new StringTokenizer(br.readLine());
        end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        // 배열 초기화
        maze = new int[N][M];
        visited1 = new int[N][M];
        visited2 = new int[N][M];

        // 큐 초기화
        queue = new ArrayDeque<>();

        // 미로 행렬 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(visited1[i], -1);
        }

        // 배열 복사
        for (int i = 0; i < N; i++) {
            visited2[i] = Arrays.copyOf(visited1[i], M);
        }
        //////////////////////////////// 입력 종료 ////////////////////////////////////

        // 시작 좌표 초기화
        visited1[start[0] - 1][start[1] - 1] = 0;
        queue.add(new int[] {start[0] - 1, start[1] - 1, 0});
        bfs();

        // ## 최종 결과 계산 및 출력 ##
        // 1. bfs 탐색이 끝난 후, 도착 지점의 두 방문 배열 값을 확인합니다.
        ans1 = visited1[end[0]-1][end[1]-1];
        ans2 = visited2[end[0]-1][end[1]-1];
        // 2. 두 배열 값 모두 방문한 적이 없다면, 탈출이 불가능한 경우입니다.
        // 3. 한쪽 배열에만 방문 기록이 있다면, 그 값이 최단 거리입니다.
        // 4. 두 배열 모두에 방문 기록이 있다면, 둘 중 더 작은 값이 최단 거리입니다.
        int D= Math.min(ans1, ans2);
        // 5. 계산된 최종 결과를 출력합니다.
        bw.write(D+"");
        bw.flush();
        bw.close();
        br.close();


    } // main

    // bfs 탐색
    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int r = curr[0];
            int c = curr[1];
            // 벽을 부수는 지도인지 아닌지 같이 저장
            int status = curr[2];

            // --- 배열 상태 출력 코드 ---
            System.out.println("\n\n<<<<<<<<<< " + (turn++) + "번째 턴 (처리 대상: " + Arrays.toString(curr) + ") >>>>>>>>>>");
            printArray(visited1, "visited1 (벽 안 부숨)");
            printArray(visited2, "visited2 (벽 부숨)");
            // --------------------------

            // 4방 탐색
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];


                // 범위 확인
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                // ## 현재 상태가 '벽을 안 부순 상태'일 때 ##
                if (status == 0) {
                    // ## 다음 칸이 '길'이라면 ##
                    if(maze[nr][nc]==0){
                        // - '벽 안 부순 상태의 방문 배열'에서 다음 칸을 방문한 적이 없는지 확인합니다.
                        if(visited1[nr][nc]==-1){
                            // - 방문한 적 없다면, 해당 배열의 다음 칸에 '현재 칸까지의 거리에 1을 더한 값'을 기록합니다.
                            visited1[nr][nc]= visited1[r][c]+1;
                            // - 큐에 '다음 칸의 좌표'와 '벽 안 부순 상태'를 넣어줍니다.
                            int[] next = new int[]{nr, nc, 0};
                            queue.add(next);

                            // --- 큐 출력 코드 ---
                            System.out.print("1 추가: " + Arrays.toString(next) + " | 현재 큐: ");
                            for(int[] item : queue) {
                                System.out.print( Arrays.toString(item) + " -> ");
                            }
                            System.out.println(); // 줄바꿈
                            // --------------------
                        }

                    }
                    // ## 다음 칸이 '벽'이라면 ##
                    else {
                        // - '벽을 부순 상태의 방문 배열'에서 다음 칸을 방문한 적이 없는지 확인합니다.
                        if(visited2[nr][nc]==-1){
                            // - 방문한 적 없다면, 해당 배열의 다음 칸에 '현재 칸까지의 거리에 1을 더한 값'을 기록합니다.
                            visited2[nr][nc]= visited1[r][c]+1;
                            // - 큐에 '다음 칸의 좌표'와 '벽을 부순 상태'를 넣어줍니다.
                            int[] next = new int[]{nr, nc, 1};
                            queue.add(next);

                            // --- 큐 출력 코드 ---
                            System.out.print("2 추가: " + Arrays.toString(next) + " | 현재 큐: ");
                            for(int[] item : queue) {
                                System.out.print(Arrays.toString(item) + " -> ");
                            }
                            System.out.println(); // 줄바꿈
                            // --------------------

                        }
                    }
                }

                // ## 현재 상태가 '이미 벽을 부순 상태'일 때 ##
                else { // status == 1
                    // ## 다음 칸이 '길'이라면 ##
                    if(maze[nr][nc]==0){
                        // - '벽을 부순 상태의 방문 배열'에서 다음 칸을 방문한 적이 없는지 확인합니다.
                        if(visited2[nr][nc]==-1){
                            // - 방문한 적 없다면, 해당 배열의 다음 칸에 '현재 칸까지의 거리에 1을 더한 값'을 기록합니다.
                            visited2[nr][nc]= visited2[r][c]+1;
                            // - 큐에 '다음 칸의 좌표'와 '벽을 부순 상태'를 그대로 넣어줍니다.
                            int[] next = new int[]{nr, nc, 1};
                            queue.add(next);

                            // --- 큐 출력 코드 ---
                            System.out.print("3 추가: " + Arrays.toString(next) + " | 현재 큐: ");
                            for(int[] item : queue) {
                                System.out.print(Arrays.toString(item) + " -> ");
                            }
                            System.out.println(); // 줄바꿈
                            // --------------------
                        }

                    }
                    // 길 아니면 안 감(이미 벽을 부숴서 더이상 못 부숨 이제 길따라 가야함..)


                }

            }// 4
        } // while

    } // bfs

    // 2차원 배열 상태를 출력하는 보조 메서드
    public static void printArray(int[][] arr, String name) {
        System.out.println("▼▼▼▼▼ " + name + " 상태 ▼▼▼▼▼");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                //-1은 .으로, 나머지는 숫자로 보기 좋게 출력
                if(arr[i][j] == -1) {
                    System.out.printf("%3s", ".");
                } else {
                    System.out.printf("%3d", arr[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
    }
}
