package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N11728 {

    static int N, M;
    static int[] a = new int[1000000 + 5];
    static int[] b = new int[1000000 + 5];
    static int[] sortArr = new int[2000000 + 5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort();
        br.close();
    }


    // 각각 다른 배열을 정렬하고 합치기
    private static void mergeSort() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 정렬
//        selectionSort(a);
//        selectionSort(b);

        // 합치기
        // 두 배열의 가장 앞 값을 비교해서 하나씩 채우기
        int aIdx = 0, bIdx = 0;
        for (int i = 0; i < N + M; i++) {
            if (bIdx == M) {
                sortArr[i] = a[aIdx++];
            } else if (aIdx == N) {
                sortArr[i] = b[bIdx++];
            } else if (a[aIdx] <= b[bIdx]) {
                sortArr[i] = a[aIdx++];
            } else {
                sortArr[i] = b[bIdx++];
            }
        }


        // 정답 출력
        for (int j = 0; j < N + M; j++) {
            bw.write(sortArr[j] + " ");
        }
        bw.flush();
        bw.close();

    }

//    private static void selectionSort(int[] arr) {
//
//        for (int i = 0; i < N - 1; i++) {
//            int minIdx = i;
//            for (int j = i + 1; j < N; j++) {
//                if (arr[minIdx] > arr[j]) {
//                    minIdx = j;
//                }
//            }
//            if (minIdx != i) {
//                int temp = arr[i];
//                a[i] = a[minIdx];
//                a[minIdx] = temp;
//            }
//        }


}
