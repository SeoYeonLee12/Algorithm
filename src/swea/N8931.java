package swea;
import java.io.*;
import java.util.*;

public class N8931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수 입력 받음
        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스 만큼 반복
        for (int tc = 1; tc <= T; tc++) {
            // 몇 개의 정수를 내놓을 건지 입력받음
            int K = Integer.parseInt(br.readLine());

            // 스택 선언
            Deque<Integer> stack = new ArrayDeque<>();

//            K만큼 반복문을 돌면서 원소를 순회함.
            for (int i = 0; i < K; i++) {
                // 하나씩 입력방음
                int num = Integer.parseInt(br.readLine());

                // num이 0이면
                if (num == 0) {
                    // 스택이 비어있지 않으면
                    if (!stack.isEmpty()) {
                        // 탑 값 삭제
                        stack.pop();
                    }
                    // 그 외에는 삽입
                } else {
                    stack.push(num);
                }
            }

            // 스택에 저장된 값을 누적할 변수 선언
            int sum = 0;
            // 스택이 비어있지 않을 때만 돌고 pop한 값에 sum 누적
            while (!stack.isEmpty()) {
                sum += stack.pop();
            }

            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
