package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2292 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int N= Integer.parseInt(br.readLine());

        int count=1;
        int range=1;

        while(N>range){
            range += 6*count;
            count++;
        }

        System.out.println(count);
    }
}
