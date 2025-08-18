package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class N2999 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String message = br.readLine();

        int r = 0;
        int c = 0;
        int maxR = 0;

        int len = message.length();

        for (int i = 1; i <= len; i++) {
            if (len % i == 0) {
                r = i;
                c = len / r;
                if (c >= r) {
                    maxR = r;
                } // 가장 큰 r 찾기
            }
        }

        c = len / maxR;

        char[][] replaceArr = new char[maxR][c];
        int idx = 0;

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < maxR; j++) {
                replaceArr[j][i] = message.charAt(idx++);
            }
        }// 배열에 값 넣기

        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < c; j++) {
               bw.write(replaceArr[i][j]);
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

}
