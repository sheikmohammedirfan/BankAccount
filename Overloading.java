class A {
int disp(int a, int b){
return a+b;
}
int disp(int a, int b , int c){
return a-b-c;
}
}
class B{

}
public class Overloading {
    public static void main(String[] args) {
        A objA = new A();
        System.out.println(objA.disp(2, 4));
    }
    
}
