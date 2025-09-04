package BarkingDog.Array;

import java.util.Arrays; // Arrays.toString()을 사용하여 배열 내용을 쉽게 출력할 수 있습니다.

public class ArrayOperations {

    // 배열에 원소를 삽입하는 메서드 (구현 비워둠)
    public static void insert(int idx, int num, int[] arr, int[] lenRef) {
        // 1. 삽입할 위치(idx)부터 현재 배열의 끝까지 (lenRef[0]-1)
        //    모든 원소를 한 칸씩 뒤로 밀어냄.
        for (int i = lenRef[0]; i > idx; i--) {
            arr[i] = arr[i - 1];
        }
        // 2. 이제 idx 위치가 비었으므로, 새 원소를 삽입합니다.
        arr[idx] = num;
        // 3. 배열의 유효 길이를 1 증가시킵니다.
        lenRef[0]++;
    }

    // 배열에서 원소를 삭제하는 메서드 (구현 비워둠)
    public static void erase(int idx, int[] arr, int[] lenRef) {
        // 1. 삭제할 위치(idx)부터 현재 배열의 끝-1까지 (lenRef[0]-2)
        //    모든 원소를 한 칸씩 앞으로 당겨옵니다.
        //    이때 루프는 idx부터 시작하여 lenRef[0]-1까지 순방향으로 진행해야 합니다.
        for (int i = idx; i < lenRef[0] - 1; i++) {
            arr[i] = arr[i + 1];
        }
        // 2. 배열의 길이를 1 감소
        lenRef[0]--;
    }

    // 배열 내용을 출력하는 메서드
    public static void printArr(int[] arr, int[] lenRef) {
        for (int i = 0; i < lenRef[0]; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
    }

    // insert 메서드 테스트
    public static void insert_test() {
        System.out.println("***** insert_test *****");
        int[] arr = new int[10];
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;
        int[] len = {3}; // Java에서는 기본 타입(int)을 참조로 전달할 수 없으므로,
        // 길이가 1인 배열을 사용하여 'len' 값을 참조처럼 전달합니다.

        insert(3, 40, arr, len); // 10 20 30 40
        printArr(arr, len);
        insert(1, 50, arr, len); // 10 50 20 30 40
        printArr(arr, len);
        insert(0, 15, arr, len); // 15 10 50 20 30 40
        printArr(arr, len);
    }

    // erase 메서드 테스트
    public static void erase_test() {
        System.out.println("***** erase_test *****");
        int[] arr = new int[10];
        arr[0] = 10;
        arr[1] = 50;
        arr[2] = 40;
        arr[3] = 30;
        arr[4] = 70;
        arr[5] = 20;
        int[] len = {6};

        erase(4, arr, len); // 10 50 40 30 20
        printArr(arr, len);
        erase(1, arr, len); // 10 40 30 20
        printArr(arr, len);
        erase(3, arr, len); // 10 40 30
        printArr(arr, len);
    }

    public static void main(String[] args) {
        insert_test();
        erase_test();
    }
}