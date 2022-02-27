package src.com.lxf.a;

public class TestC extends TestA{
        protected int a = 20;
        TestC(){
                System.out.println("Parent TestC contructor..");
        }
        @Override
        public void printTestA(){
                System.out.println("12");
        }
        TestA testA = new TestA();
        void printTestC(){
                //testA.printTestA();
                printTestA();
        }
        private void f(){
                System.out.println("private f()");
        }

        public static void main(String[] args) {
                TestC testC1 = new Derived();
        }
}

class Derived extends TestC {
        public void f(){
                System.out.println("public f()");
        }
        public void dute(){
                System.out.println("I am dute fangfa");
        }
}
