package BarkingDog.recursion;



public class SumRecursion {

    public static void main(String[] args) {

        recur1(3);
    }

    // 재귀로 N~1까지 출력
    static void recur1(int num){
        // 종료부
        if(num ==0){
            return;
        }
        System.out.print(num+" ");
        recur1(num-1);

    }


    // 재귀로 N부터 1까지 합을 구하는 함수
    static int recur2(int num){
        // 종료부
        if(num==0){
            return 0;
        }

        return num+recur2(num-1);
    }
}
