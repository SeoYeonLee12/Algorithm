package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1780 {

    static int N;
    static int[][] paper= new int[2200][2200];
    // 정답 카운트 변수
    static int[] ans= new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        N= Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                paper[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        // 재귀 함수 호출
        // 처음 전체 범위 확인
        recur(0, 0, N);

        for(int n: ans){
            System.out.println(n);
        }

    }

    // 재귀 함수
    private static void recur(int r, int c, int n) {
        // base condition
        if(check(r, c, n)){
            ans[paper[r][c]+1]++;
            return;
        }

        // 재귀부
        n /= 3;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                recur(r+i*n, c+j*n, n);
            }
        }
    }

    // 같은 숫자로 이루어진건지 체크하는 함수
    private static boolean check(int r, int c, int n) {
        // 범위를 다 돌았을 때 같은 색이 아닌 경우
        for(int i=r; i<r+n; i++) {
            for(int j=c; j<c+n; j++) {
                if (paper[r][c] != paper[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
