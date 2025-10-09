package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N9663 {

    static int N;
    // 열 검사
    static boolean[] isUsed1= new boolean[40];
    // 좌 하단 -> 우 상단
    static boolean[] isUsed2= new boolean[40];
    // 좌 상단-> 우 하단
    static boolean[] isUsed3= new boolean[40];
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        backTrack(0);
        System.out.println(cnt);
    }

    private static void backTrack(int cur) {
        if(cur==N){
            cnt++;
            return;
        }

        for(int i=0; i<N; i++){
            // 유망성 검사
            if(isUsed1[i] || isUsed2[i+cur] || isUsed3[cur-i+N-1]){
                continue;
            }
            // 방문
            isUsed1[i]= true;
            isUsed2[i+cur]= true;
            isUsed3[cur-i+N-1]= true;
            backTrack(cur+1);
            // 후퇴
            isUsed1[i]= false;
            isUsed2[i+cur]= false;
            isUsed3[cur-i+N-1]= false;

        }
    }
}
