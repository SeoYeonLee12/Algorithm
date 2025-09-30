package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N11729 {

    public  static StringBuilder sb= new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        System.out.println((int)Math.pow(2, k) - 1);

        hannoi(1, 3, k);

        System.out.println(sb.toString());
    }

    // 1. 함수의 정의
    // 원판 n개를 a번 기둥에서 b번 기둥으로 옮기는 방법을 출력하는 함수
    public static void hannoi(int start, int end, int num) {

        // base condition
        if (num == 1) {
            sb.append(start + " " + end+"\n");
            return;
        }

        // 1번에서 2번으로
        hannoi(start, 6 - start - end, num - 1);

        // 이동 경로 출력
        sb.append(start + " " + end+"\n");
        // 2에서 3으로
        hannoi(6 - start - end, end, num - 1);
    }
}
