package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


// 내 풀이: 삼중 반복문을 돌려서 가장 근접한 숫자를 찾으면 그걸 출력
// 우선 2가지 조건 필수 M보다 작거나 같아야함/ 해당 sum 중에서도 가장 커야 함. 이 두가지 조건을 만족하는 합이 답
public class N2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }// 카드 입력

        int maxSum=0; // 가장 근접한 합을 가진 변수
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int sum=0; // 3개의 카드 저장할 변수
                    sum= cards[i]+cards[j]+cards[k];

                    if(sum<=M && sum>maxSum){
                        maxSum=sum;
                    }
                }
            }
        }

        bw.write(maxSum+"");
        bw.newLine();
        bw.flush();

    }
}
