package BarkingDog.LinkedList;

import java.util.Arrays;

public class LinkedList {

    static final int MX = 1000005;
    static int[] dat = new int[MX];
    static int[] pre = new int[MX];
    static int[] nxt = new int[MX];
    static int unused = 1;

    // addr은 이 위치의 '뒤에' num을 삽입하라는 의미입니다.
    public static void insert(int addr, int num) {
        // 1. 새 노드에 대한 정보 저장
        dat[unused] = num;
        pre[unused] = addr;
        nxt[unused] = nxt[addr];

        // 2. 원래 있던 노드들의 연결 정보 수정
        // addr의 다음 노드의 pre를 새 노드로 변경
        if (nxt[addr] != -1) {
            pre[nxt[addr]] = unused;
        }
        // addr의 nxt를 새 노드로 변경
        nxt[addr] = unused;

        // 3. 다음 사용 가능한 주소 증가
        unused++;
    }

    // addr은 이 위치의 노드를 '삭제'하라는 의미입니다.
    public static void erase(int addr) {
        // 삭제할 노드의 이전 노드와 다음 노드를 서로 연결
        nxt[pre[addr]] = nxt[addr];
        if (nxt[addr] != -1) {
            pre[nxt[addr]] = pre[addr];
        }
    }

    // 연결 리스트의 모든 노드를 순회하며 출력
    public static void traverse() {
        int cur = nxt[0]; // 0번은 더미 노드이므로, 0번의 다음 노드부터가 실제 시작
        while (cur != -1) {
            System.out.print(dat[cur] + " ");
            cur = nxt[cur];
        }
        System.out.println("\n");
    }

    public static void insert_test() {
        System.out.println("****** insert_test *****");
        insert(0, 10); // 10(address=1)
        traverse();
        insert(0, 30); // 30(address=2) 10
        traverse();
        insert(2, 40); // 30 40(address=3) 10
        traverse();
        insert(1, 20); // 30 40 10 20(address=4)
        traverse();
        insert(4, 70); // 30 40 10 20 70(address=5)
        traverse();
    }

    public static void erase_test() {
        System.out.println("****** erase_test *****");
        erase(1); // 30 40 20 70
        traverse();
        erase(2); // 40 20 70
        traverse();
        erase(4); // 40 70
        traverse();
        erase(5); // 40
        traverse();
    }

    public static void main(String[] args) {
        // Java에서는 Arrays.fill()을 사용해 배열을 특정 값으로 초기화할 수 있습니다.
        Arrays.fill(pre, -1);
        Arrays.fill(nxt, -1);

        insert_test();
        erase_test();
    }
}