class A{
    private int a ;
    String name ;
public void setA(int a){
    this.a = a;
if(a >= 18){
System.out.println(a);
System.out.println(" ur are eligible to vote ");
}
else{
    System.out.println(" not valid ");
}
}
public int getA(){
    return a;
}
}
public class Oops {
    public static void main(String[] args) {
        A obj = new A();
obj.setA(-44444);
obj.getA();
    }
}
