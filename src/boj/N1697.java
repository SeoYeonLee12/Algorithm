package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

import java.util.Deque;
import java.util.StringTokenizer;

public class N1697 {
    static int N;
    static int K;
    static int[] load;
    static Deque<Integer> queue;
    // 현재 수빈의 위치
    static int X;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        load = new int[100001];
        for (int i = 0; i < load.length; i++) {
            load[i] = -1;
        }

        queue = new ArrayDeque<>();

        // 현재 수빈의 위치기록
        queue.add(N);
        load[N]=0;

        bfs();
//        System.out.println(Arrays.toString(load));
        bw.write(load[K]+"");
        bw.flush();
        bw.close();
        br.close();

    } // main

    private static void bfs() {
        while (!queue.isEmpty()) {
            int curr = queue.remove();

            // 동일하면 즉시 종료
            if(curr==K){
                return;
            }

            X = curr;
            int[] next = {X - 1, X + 1, 2 * X};

            // 노드 3개 탐색
            for (int i = 0; i < next.length; i++) {
                int nextX = next[i];

                // 배열 범위 넘는 지 체크
                if (nextX < 0 || nextX >= load.length) {
                    continue;
                }

                if (load[nextX]!=-1){
                    continue;
                }

                load[nextX]= load[curr]+1;
                queue.add(nextX);
            }

        } // while
    } // bfs
}
