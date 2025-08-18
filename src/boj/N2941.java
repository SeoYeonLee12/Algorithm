package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class N2941 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String line= br.readLine();

        int count=0;
        for(int i=0; i<line.length(); i++){
            int temp= i;
            int next= i+1;

            // 알파벳 2개를 찾았을 경우
            if((next)<line.length()
                    && ((line.charAt(temp)=='c' && line.charAt(next)=='=')
                    || (line.charAt(temp)=='c' && line.charAt(next)=='-')
                    || (line.charAt(temp)=='d' && line.charAt(next) =='-')
                    || (line.charAt(temp)=='l' && line.charAt(next)=='j')
                    || (line.charAt(temp)=='n' && line.charAt(next)=='j')
                    || (line.charAt(temp)=='s' && line.charAt(next)=='=')
                    || (line.charAt(temp)=='z' && line.charAt(next)=='='))
            ){
                count+=1;
                i+=1;
            }
            // 알파벳 3개를 찾았을 경우
            else if((next+1)<line.length() && (line.charAt(temp)=='d' && line.charAt(next)=='z' && line.charAt(next+1)=='=')){
                count+=1;
                i+=2;
            }
            // 없는 알파벳일 경우
            else{
                count+=1;
            }

        }
        bw.write(count+"");
        bw.flush();
    }
}
