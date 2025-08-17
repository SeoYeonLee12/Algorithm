package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class N2810 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String line = br.readLine();
        int LNum = 0;

        for (int i = 0; i < N; i++) {
            if(line.charAt(i)=='L'){
                LNum+=1;
            }
        } // 입력받기

        // 빠지는 컵홀더 갯수
        LNum/=2;
        int answer= (N+1) - LNum;

        if(N<answer){
            bw.write(N+"");
            bw.flush();
            return;
        }
        bw.write(answer+"");
        bw.flush();

    }
}
