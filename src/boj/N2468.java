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

// DFS, BFS 가능
public class N2468 {

    private static class Pos {
        int r, c, area;

        public Pos(int r, int c, int area) {
            this.r = r;
            this.c = c;
            this.area = area;
        }
    } // pos

    static int N;
    static int[][] rainMap;
    static boolean[][] visited;
    static Deque<Pos> queue;
    // 델타 배열
    static int[] dr= {-1, 1, 0, 0};
    static int[] dc= {0, 0, 1, -1};
    static int maxArea= 1;


    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        N= Integer.parseInt(br.readLine());
        rainMap= new int[N][N];
        queue= new ArrayDeque<>();

        // 배열 입력받기
        for(int i=0; i<N; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                rainMap[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        // 시작 높이와 끝 높이 찾기
        int start=Integer.MAX_VALUE;
        int end= Integer.MIN_VALUE;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                start= Math.min(start, rainMap[i][j]);
                end= Math.max(end, rainMap[i][j]);
            }
        }

        // bfs 탐색
        for(int i=start; i<end; i++){
            visited= new boolean[N][N];

            int currAreaCnt=0;

            // 지도 순회
            for(int j=0; j<N; j++){
                for(int k=0; k<N; k++){
                    if(!visited[j][k] && rainMap[j][k]>i){
                        currAreaCnt+=1;
                        queue.add(new Pos(j, k, i));
                        visited[j][k]= true;
                        bfs();
                    }
                }
            }

            maxArea= Math.max(maxArea, currAreaCnt);
        }
        bw.write(Integer.toString(maxArea));
        bw.flush();

    } // main

    private static void bfs() {
        while(!queue.isEmpty()){
            Pos curr= queue.remove();
            int r= curr.r;
            int c= curr.c;
            int area= curr.area;

            for(int i=0; i<dr.length; i++){
                int nr= r+dr[i];
                int nc= c+dc[i];

                if(nr<0 || nr>=N || nc<0 || nc>=N){
                    continue;
                }

                if(visited[nr][nc]||rainMap[nr][nc]<=area){
                    continue;
                }

                queue.add(new Pos(nr, nc, area));
                visited[nr][nc]= true;
            }
        }

    } // bfs

} // class
