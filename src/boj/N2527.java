package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 내풀이: x와 y가 전부 다 그 범위에 들어있거나 겹치면 a 그런데 이때 좌표 점이 무조건 저 범위 안에 포함되고 y도 그러면
// / 높이, 밑면 둘 중에 하나가 그 범위 안에 있거나 겹치면 b
// 겹치지 않고 점만 맞으면c 그 외에는 d
public class N2527 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = 4;
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 사각형을 담을 배열
            int[] square1 = new int[4];
            int[] square2 = new int[4];
            for (int i = 0; i < square1.length; i++) {
                square1[i] = Integer.parseInt(st.nextToken());
            } // 첫번째 사각형 입력받기
            for (int j = 0; j < square2.length; j++) {
                square2[j] = Integer.parseInt(st.nextToken());
            }// 두번째 사각형 입력받기

            // 답 저장할 변수
            String result = "";
            // 8개의 좌표 값
            int x1 = square1[0], y1 = square1[1], p1 = square1[2], q1 = square1[3];
            int x2 = square2[0], y2 = square2[1], p2 = square2[2], q2 = square2[3];

            if (p1 <x2 || p2 < x1 || q1< y2 || q2 < y1) {
                result = "d";
            }

            else if ((x1 == p2) && (q1 == y2)
                    || (p1 == x2) && (q1 == y2)
                    || (x1 == p2) && (y1 == q2)
                    || (p1 == x2) && (y1 == q2)) {
                result = "c";
            }
            // 이제 두 점의 범위 안에 있으먄
            else if (p1 == x2 || x1 == p2 || y1 == q2 || q1 == y2) {
                result = "b";
            } else {
                result = "a";
            }


            bw.write(result);
            bw.newLine();
            bw.flush();
        } // 전체테케
    }
}
