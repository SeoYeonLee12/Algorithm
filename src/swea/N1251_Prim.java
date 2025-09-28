package swea;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N1251_Prim {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 전체 테스트 케이스의 수를 입력받습니다.
        int T = Integer.parseInt(br.readLine());

        // 2. 테스트 케이스 수만큼 반복문을 실행합니다.
        for (int tc = 1; tc <= T; tc++) {

            // -- 각 테스트 케이스 초기화 --
            // 3. 섬의 개수 N을 입력받습니다.
            int N = Integer.parseInt(br.readLine());

            // 4. 섬들의 X좌표와 Y좌표를 2차원 배열이나 Point 클래스 배열에 저장합니다.
            int[] xArr = new int[N];
            int[] yArr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                xArr[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                yArr[i] = Integer.parseInt(st.nextToken());
            }


            // 5. 환경 부담 세율 E를 입력받습니다.
            double E = Double.parseDouble(br.readLine());

            // -- 프림 알고리즘 준비 --
            // 6. 각 섬이 MST(최소 신장 트리)에 포함되었는지 여부를 저장할 boolean 배열 visited를 생성하고 false로 초기화합니다.
            boolean[] visited = new boolean[N];

            // 7. 각 섬이 MST에 포함된 정점들과 연결될 때의 최소 비용을 저장할 long 배열 minEdge를 생성합니다.
            //    - 모든 값을 아주 큰 값(Long.MAX_VALUE)으로 초기화합니다.
            long[] key = new long[N];
            for (int i = 0; i < N; i++) {
                key[i] = Long.MAX_VALUE;
            }

            // 8. 임의의 시작점(예: 0번 섬)을 정하고, 해당 섬의 minEdge 값을 0으로 설정합니다.
            key[0] = 0;

            // 9. MST의 총 비용을 저장할 변수(long totalCost)를 0으로 초기화합니다.
            long totalCost = 0;

            // -- 프림 알고리즘 실행 --
            // 10. 총 N개의 섬을 모두 연결해야 하므로, N번 반복하는 루프를 시작합니다.
            for (int i = 0; i < N; i++) {

                // 11. (가장 가까운 새 섬 찾기) 아직 방문하지 않은(visited가 false인) 섬들 중에서,
                //     key 값이 가장 작은 섬을 찾습니다. 해당 섬의 인덱스와 비용을 변수에 저장합니다.

                // 가장 최소 비용을 가진 섬의 인덱스를 저장하는 변수
                int minIdx = -1;
                // 이번 턴에서 찾은 최소 비용을 저장하는 변수
                long min = Long.MAX_VALUE;
                for (int j = 0; j < N; j++) {
                    if (!visited[j] && key[j] < min) {
                        min = key[j];
                        minIdx = j;
                    }
                }

                // 12. 위에서 찾은 섬을 방문 처리(visited = true)하고, 해당 섬의 key 비용을 totalCost에 더합니다.
                visited[minIdx] = true;
                totalCost += min;

                // 13. (연결 비용 갱신) 새로 방문한 섬을 기준으로, 아직 방문하지 않은 다른 모든 섬들과의 거리를 계산합니다.
                //     - 계산된 거리(L^2)가 해당 섬의 기존 minEdge 값보다 작다면, minEdge 값을 더 작은 값으로 갱신합니다.
                //     - 이 과정을 통해 minEdge 배열에는 항상 MST에 포함된 섬들과 연결되는 가장 짧은 간선 비용 정보가 유지됩니다.
                for (int j = 0; j < N; j++) {
                    if (!visited[j]) {
                        long dx = (long) xArr[minIdx] - xArr[j];
                        long dy = (long) yArr[minIdx] - yArr[j];
                        long cost = dx * dx + dy * dy;

                        if (cost < key[j]) {
                            key[j] = cost;
                        }
                    }
                }

                // 14. N번의 반복이 끝나면 모든 섬이 MST에 포함됩니다.
            }

            // -- 결과 계산 및 출력 --
            // 15. 최종 환경 부담금(totalCost * E)을 계산합니다.
            double finalCost= totalCost*E;

            // 16. 계산된 값을 소수 첫째 자리에서 반올림하여 정수 형태로 만듭니다. (Math.round() 사용)
            long result= Math.round(finalCost);

            // 17. 형식에 맞게 테스트 케이스 번호와 계산된 최종 비용을 출력합니다.
            bw.write("#"+tc+" "+result);
            bw.newLine();
            bw.flush();
        }
    }
}