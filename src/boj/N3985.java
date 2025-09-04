package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N3985 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        // L 받기
        int L = Integer.parseInt(br.readLine());
        // N 받기
        int N = Integer.parseInt(br.readLine());

        // 방청객이 받고 싶은 조각의 첫 값과 끝 값을 받기 위해 배열 선언
        int[][] want = new int[N][2];
        int maxIdx = 0;
        // N만큼 돌면서 따로 떼어서 배열에 저장함.
        for (int i = 0; i < want.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            want[i][0] = Integer.parseInt(st.nextToken());
            want[i][1] = Integer.parseInt(st.nextToken());

            int minus = want[i][0] - want[i][1];
            int maxMinus = Integer.MIN_VALUE;
            maxIdx = i + 1;

            if (minus > maxMinus) {

                maxIdx++;
            }

        }
        // 앞 값과 뒷 값을 뺀 값이 가장 큰 인덱스를 출력 또는 변수 하나 선언해서 해당 인덱스를 갖게함.
        bw.write(maxIdx + " ");
        bw.newLine();

        // 배열을 돌면서 해당 원소 값에 번호를 씀.
        int[] cake = new int[L];

        for (int j = 0; j < want.length; j++) {
            for (int k = want[j][0]; k <= want[j][1]; k++) {
                cake[k] += 1;
            }
        }

        System.out.println(Arrays.toString(cake));

        // 새 배열을 만들어서 해당 원소의 갯수를 카운팅함.
        int[] countArr = new int[cake.length];
        for (int i = 0; i < cake.length; i++) {
            if(countArr[cake[i]] ==0)
                countArr[cake[i]] += 1;
        }

        // maxCount를 계속해서 갱신함
        int maxCount = 0;
        int maxIdx2 = 0;
        for (int j = 0; j < countArr.length; j++) {
            int count = countArr[j];
            if (count > maxCount) {
                maxCount = count;
                maxIdx2 = j + 1;
            }
        }
        // 이 경우 동일하면 번호가 작은 것을 갖게함.
        bw.write(maxIdx2 + "");
        bw.flush();


    }
}
