import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

public class Test {
        char ch;
        String token;
        public String[] Reserve;
        public String[] out_Reserve;
        String str;
        int index1;
        int err_num;
        public Test(){
                Reserve = new String[6];
                out_Reserve = new String[6];
                Reserve[0]="BEGIN";
                out_Reserve[0]="Begin";
                Reserve[1]="END";
                out_Reserve[1]="End";
                Reserve[2]="FOR";
                out_Reserve[2]="For";
                Reserve[3]="IF";
                out_Reserve[3]="If";
                Reserve[4]="THEN";
                out_Reserve[4]="Then";
                Reserve[5]="ELSE";
                out_Reserve[5]="Else";
                ch=' ';
                str="";
                token="";
                index1=0;
                err_num=0;
        }
        public void getChar(){
                ch = str.charAt(index1++);
        }
        public void filter_blank_ch(){
                while(ch == ' '|| ch == '\n' || ch =='\t' || ch == '\r'){
                        getChar();
                }
        }
        public void catToken(){
                token+=ch;
        }
        public int reserver(){
                for(int i=0;i<Reserve.length;i++){
                        if(token.equals(Reserve[i]))
                                return i;
                }
                return -1;
        }
        public int transNum(){
                return Integer.parseInt(token);
        }
        public void retract(){
                ch=' ';
                index1--;
        }
        public void clearToken(){
                token="";
        }
        public void error(){
                err_num=1;
                System.out.println("Unknown");
        }
        public void lexical_analysis(){
                clearToken();
                getChar();
                filter_blank_ch();
                if(index1>=str.length())
                        return;
                //保留字和标识符
                if(Character.isLetter(ch)){
                        while(Character.isLetterOrDigit(ch)){
                                catToken();
                                getChar();
                        }
                        retract();
                        //是保留字
                        if(reserver()!=-1){
                                System.out.println(out_Reserve[reserver()]);
                        }
                        else
                                System.out.println("Ident("+token+")");
                }
                else if(Character.isDigit(ch)){
                        int flag=0;
                        int cnt=0;
                        while(Character.isDigit(ch)){
                                if(ch != '0'){
                                        flag=1;
                                        catToken();
                                        getChar();
                                }
                                else if(ch =='0'&&flag==1){
                                        catToken();
                                        getChar();
                                }
                                else if(ch =='0' && cnt==0){
                                        catToken();
                                        getChar();
                                }
                                else{
                                        getChar();
                                }
                                cnt++;
                        }
                        retract();
                        System.out.println("Int("+transNum()+")");
                }
                else if(ch == ':'){
                        getChar();
                        if(ch == '='){
                                System.out.println("Assign");
                        }
                        else{
                                retract();
                                System.out.println("Colon");
                        }
                }
                else if(ch == '+') System.out.println("Plus");
                else if(ch == '*') System.out.println("Star");
                else if(ch == ',') System.out.println("Colon");
                else if(ch == '(') System.out.println("LParenthesis");
                else if(ch == ')') System.out.println("RParenthesis");
                else error();
        }
        public void get_file(String fileName) throws Exception{
                File file = new File(fileName);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuilder = new StringBuilder();
                String s = "";
                while ((s=bufferedReader.readLine())!=null){
                        stringBuilder.append(s+"\n");
                }
                bufferedReader.close();
                str = stringBuilder.toString();
        }
        public static void main(String[] args) throws Exception{
                Test test = new Test();
                test.get_file(args[0]);
                while (test.index1<test.str.length()){
                        test.lexical_analysis();
                }
        }
}
