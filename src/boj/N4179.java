package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class N4179 {
    static int R, C;
    static char[][] maze;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] jihun;
    static int[][] fire;
    static int result;
    static Deque<int[]> queueFire;
    static Deque<int[]> queueJihun;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 배열 초기화
        maze = new char[R][C];
        jihun = new int[R][C];
        fire = new int[R][C];

        // 미로 입력받기
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            maze[i] = line.toCharArray();

            // 미로 초기화
            for (int j = 0; j < C; j++) {
                fire[i][j] = -1;
                jihun[i][j] = -1;
            }
        }

        queueFire = new ArrayDeque<>();
        queueJihun = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maze[i][j] == 'F') {

                    fire[i][j] = 0;
                    queueFire.add(new int[] {i, j});
                }
                if (maze[i][j] == 'J') {

                    jihun[i][j] = 0;
                    queueJihun.add(new int[] {i, j});
                }
            }
        }

        // 1. 불의 확산 시간을 먼저 계산합니다.
        fireBfs();
//        for (int i = 0; i < fire.length; i++) {
//            System.out.println(Arrays.toString(fire[i]));
//        }

        // 2. 지훈이의 탈출 시간을 계산합니다.
        //    아래에 만들 jihun_bfs 메서드를 호출하고, 반환되는 결과(탈출 시간 또는 실패 여부)를 변수에 저장합니다.
        jihunBfs();

        // 3. 위에서 저장한 변수 값을 확인합니다.
        //    만약 탈출에 성공했다면 해당 시간을 출력하고, 실패했다면 "IMPOSSIBLE"을 출력합니다.
        if (result == -1) {
            bw.write("IMPOSSIBLE");
        } else {
            bw.write(result + "");
        }

        bw.flush();
        bw.close();
        br.close();

    }// main

    // [새로운 메서드 1] 불의 확산 시간을 계산하는 BFS
    private static void fireBfs() {
        // 큐가 빌 때까지 반복합니다.
        while (!queueFire.isEmpty()) {

            // 큐에서 위치를 하나 꺼냅니다.
            int[] curr = queueFire.remove();
            int r = curr[0];
            int c = curr[1];

            // 네 방향을 탐색합니다.
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 다음 위치가 미로 범위를 벗어나는지 확인합니다.
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }

                // 다음 위치가 벽이거나 이미 불이 방문했던 곳인지 확인합니다.
                if (maze[nr][nc] == '#' || fire[nr][nc] != -1) {
                    continue;
                }

                // 위 조건들에 걸리지 않았다면,
                // fire 배열에 불이 도달한 시간을 기록하고(현재 시간 + 1)
                // 큐에 다음 위치를 추가합니다.
                fire[nr][nc] = fire[r][c] + 1;
                queueFire.add(new int[] {nr, nc});
            }


        }


    }

    // [새로운 메서드 2] 지훈이의 탈출 경로를 찾는 BFS
    private static void jihunBfs() {
        // 이 메서드는 탈출하면 '시간'을, 못하면 '-1' 같은 실패 값을 반환해야 합니다.
        result = -1;

        // 큐가 빌 때까지 반복합니다.
        while (!queueJihun.isEmpty()) {

            // 큐에서 현재 지훈이의 위치를 꺼냅니다.
            int[] curr = queueJihun.remove();
            int r = curr[0];
            int c = curr[1];

            // 네 방향으로 다음 위치를 탐색합니다.
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // ★★ 가장 먼저, 다음 위치가 미로 범위를 벗어나는지 확인합니다.
                //    만약 벗어난다면, 탈출에 성공한 것입니다!
                //    현재 위치까지 걸린 시간 + 1을 반환하고 즉시 메서드를 종료합니다.
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    result = jihun[r][c] + 1;
                    return;
                }

                // --- 아래는 미로 범위 안에서 움직일 때의 로직입니다. ---

                // 다음 위치가 벽이거나, 이미 지훈이가 방문했던 곳인지 확인합니다.
                if (maze[nr][nc] == '#' || jihun[nr][nc] != -1) {
                    continue;
                }

                // (핵심) 다음 위치에 불이 지훈이보다 먼저 도착하는지 확인합니다.
                // fire 배열에 기록된 시간과, 지훈이가 다음 위치에 도착할 시간을 비교해야 합니다.
                // 불이 전혀 도달하지 못하는 곳(-1)은 안전한 곳으로 간주합니다.
                if (fire[nr][nc] <= jihun[r][c] + 1 && fire[nr][nc] != -1) {
                    continue;
                }


                // 위 조건들에 모두 걸리지 않았다면, 지훈이가 이동할 수 있는 곳입니다.
                // jihun 배열에 이동 시간을 기록하고(현재 시간 + 1)
                {
                    jihun[nr][nc] = jihun[r][c] + 1;
                }
                // 큐에 다음 위치를 추가합니다.
                queueJihun.add(new int[] {nr, nc});
            }
        }

    }
}