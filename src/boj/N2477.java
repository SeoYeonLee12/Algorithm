package boj;

// 내 풀이: 큰 변을 기준으로 직사각형의 넓이를 구하고 부족한 부분의 넓이만큼 뺀다.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N2477 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());
        // 변과 길이를 담을 리스트 선언
        int[][] extent = new int[6][2];

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int pos = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            extent[i][0] = pos;
            extent[i][1] = length;


        }// 입력받기

//        for (int i = 0; i < 6; i++) {
//            System.out.println(Arrays.toString(extent[i]));
//        }

        // 전체 직사각형의 크기를 담을 변수
        int totalExtent = 0;
        int maxHeight = 0;
        int maxWidth = 0;
        // 비어있는 직사각형의 크기를 담을 변수
        int emptyExtent = 0;
        int emptyHeight = 0;
        int emptyWidth = 0;
        // 전체 넓이에서 비어있는 부분을 뺀 넓이를 담을 변수
        int entireExtent = 0;
        int len = extent.length;

//        System.out.println("size: " + extent.length);
        for (int i = 0; i < len; i++) {

            // maxwidth 찾기
            if (extent[i][0] == 1 || extent[i][0] == 2) {
                int current = extent[i][1];
//                System.out.println("width current: "+current);
                maxWidth = Math.max(current, maxWidth);
            }

            // maxHeight 찾기
            if (extent[i][0] == 3 || extent[i][0] == 4) {
                int current = extent[i][1];
                maxHeight = Math.max(current, maxHeight);
            }

        }

        // 수정된 emptyWidth, emptyHeight를 찾는 로직
        for (int i = 0; i < len; i++) {
            // i의 이전 인덱스와 다음 인덱스를 안전하게 계산
//            int preIndex = (i - 1) < 0 ? i - 1 + len : i - 1; // i-1 대신 사용
            int nextIndex = (i + 1) == len ? 0 : i + 1;

            int preIndex=0;
            if((i-1)==-1){
               preIndex= len-1 ;
            }
            else{
                preIndex=i-1;
            }


            int preDir = extent[preIndex][0];
            int nextDir = extent[nextIndex][0];

            // 첫번째 조건은 2을 기준으로 남이 2번 나올 때, 두번쨰 조건은 1을 기준으로 북이 2번 나올때
            if ((preDir + nextDir == 6) || (preDir + nextDir == 8)) {
                emptyWidth = extent[i][1];
//                System.out.println("7" + i);

            }
            // 1->북을 기준으로 동이 2번 2-> 남을 기준으로 서가 2번
            else if ((preDir + nextDir == 2) || (preDir + nextDir == 4)) {
                emptyHeight = extent[i][1];
//                System.out.println("3" + i);

            }
        }

//        System.out.println("maxHeight: " + maxHeight + " maxWeight: " + maxWidth);
//        System.out.println("height: " + emptyHeight + " width: " + emptyWidth);

        totalExtent = maxWidth * maxHeight;
        emptyExtent = emptyWidth * emptyHeight;
        entireExtent = totalExtent - emptyExtent;
        int answer = entireExtent * K;

        bw.write(answer + "");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
// 160*50= 8000-1200= 6800 *7