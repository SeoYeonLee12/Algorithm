package aug.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1231_중위순회 {

    static int N;
    // 1. 정보를 저장할 세 종류의 배열을 전역으로 선언합니다.
    static char[] data;
    static int[] leftChild;
    static int[] rightChild;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            N = Integer.parseInt(br.readLine());

            // 2. 테스트 케이스마다 배열들을 새로 생성합니다.
            data = new char[N + 1];
            leftChild = new int[N + 1];
            rightChild = new int[N + 1];

            for (int i = 0; i < N; i++) {
                String[] tokens = br.readLine().split(" ");

                int nodeNum = Integer.parseInt(tokens[0]);

                // 3. 각 배열의 'nodeNum' 인덱스에 정보를 저장합니다.
                data[nodeNum] = tokens[1].charAt(0);

                if (tokens.length == 4) {
                    leftChild[nodeNum] = Integer.parseInt(tokens[2]);
                    rightChild[nodeNum] = Integer.parseInt(tokens[3]);
                } else if (tokens.length == 3) {
                    leftChild[nodeNum] = Integer.parseInt(tokens[2]);
                }
            }

            // 이제 3개의 배열에 모든 트리 정보가 저장되었습니다.
            // 이 배열들을 가지고 순회 등의 로직을 수행합니다.

            System.out.print("#" + tc + " ");
            inorder(1); // 1번 노드(루트)부터 중위 순회 시작
            System.out.println();
        }
    }

    // 4. 순회 함수는 현재 정점 번호를 매개변수로 받습니다.
    public static void inorder(int currentNodeNum) {
        // 자식이 없는 경우(0)는 재귀를 멈춥니다 (Base Case).
        if (currentNodeNum == 0) return;

        // 왼쪽 자식 탐색 -> 현재 노드 처리 -> 오른쪽 자식 탐색
        inorder(leftChild[currentNodeNum]);
        System.out.print(data[currentNodeNum]);
        inorder(rightChild[currentNodeNum]);
    }
}