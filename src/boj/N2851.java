package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class N2851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = 10;
        int[] mushroom = new int[N];
        int sum = 0;
        // 100보다 작은 것들 중 가장 높은 점수를 가질 변수
        int maxSum = 0;
        for (int i = 0; i < N; i++) {
            mushroom[i] = Integer.parseInt(br.readLine());
        } // 입력받기

        // 배열을 돎면서 점수를 누적함
        // 버섯이 100과 근접한 것이나 딱 맞다면 그 버섯 점수 출력
        // 만약에 해당 sum과 그 다음 버섯값을 더한 값과 100부터의 차이가 같거나 더 작다면 해당 값으로 출력
        // 얘는 절댓값 이용하면 될 거같음.
        int currIdx = 0;
        for (int j = 0; j < N; j++) {
            if ((sum + mushroom[j]) <= 100) {
                sum += mushroom[j];

                // 가장 인접한 값 갱신
                if (sum > maxSum) {
                    maxSum = sum;
                }
            } else {
                currIdx = j;
                break;
            }
        }

        // 10개를 다 돌아도 합이 100 이하일 때 바로 출력 후 리턴
        if (currIdx == 0) {
            bw.write(maxSum + "");
            bw.flush();
            return;
        }

        // 현재 sum의 다음 값
        int next = (sum + mushroom[currIdx]);
        // 100 이하의 근접한 값의 절댓값
        int v1 = Math.abs(100 - sum);
        // 100 이후의 근접한 값의 절댓값
        int v2 = Math.abs(100 - next);
        if (v1 >= v2) {
            maxSum = next;
        }
        bw.write(maxSum + "");
        bw.flush();


    }
}
