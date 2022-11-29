package kr.or.iei.func;

public class Func {

	
	public void print1() {
		System.out.println(1); //정수 1 출력
		System.out.println(1.0); //실수 1 출력
		System.out.println('1'); //문자 1 출력
		System.out.println("1"); //문자열 1 출력
	}
	
	
	public void print2() {
		System.out.println(1+1); //2
		System.out.println(1.0+1.0); //2.0
		System.out.println('1'+'1'); //11..인줄 알았으나 유니코드
		System.out.println("1"+"1"); //11
		//또한 문자열은 숫자 연산이 아니므로 +이외의 연산은 불가능
	}
	
	
	public void print3() {
		System.out.println(1+1.1); //2.1
		//자바는 서로 다른 타입의 데이터를 연산하지 못한다
		//자료형이 다르다면 연산시에 범위가 작은 자료형을 큰 쪽에 맞춰서 변환 후 연산한다
		System.out.println(1+'1'); //문자 유니코드가 정수로 변환되어 연산됨
		System.out.println(1.0+'1'); // 1.0+49 -> 1.0+49.0 = 50.0
	}
	
	public void print4() {
		//문자열+다른 자료형 (문자열의 범위가 가장 크다)
		// ->다른 자료형이 문자열로 변환되고 덧셈연산이 됨
		System.out.println(1+"1"); //"11" 
		System.out.println(1.0+"1"); // "1.0"+"1" ="1.01"
		System.out.println('1'+"1"); // "1" + "1" = "11"
	}
	
	public void print5() {
		System.out.println("Hello"+10); // "Hello"+"10"="Hello10"
		System.out.println(10+"Hello"); // "10Hello"
		System.out.println(10+20+"Hello"); // "30Hello"
		System.out.println(10+(20+"Hello")); // "1020Hello"
		//일단 왼->오 방향으로 읽는데 괄호가 있으면 그거 우선으로 한다
	}
	
}
