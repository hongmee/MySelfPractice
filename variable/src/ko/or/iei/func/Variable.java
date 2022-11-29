package ko.or.iei.func;

public class Variable {

	public void var1() {
		//변수선언하기 -> 자료형 변수이름;
		
		boolean bool; //1byte
		char ch; //2byte
		
		byte bNum;
		short sNum;
		int iNum = 100;
		long lNum;
		
		float fNum;
		double dNum;
		
		
		System.out.println("iNum : "+iNum);
		
		dNum = 100.1;
		System.out.println("dNum : "+dNum);
		
		ch = 'A';
		System.out.println("ch : "+ch);
		
		bool = true;
		System.out.println("bool : "+bool);
		
		bNum = 100;
		sNum = 200;
		lNum = 100000000000l;
		
		fNum = 3.14f;
		
		//문자열은 참조형이기 때문에 자료형이 대문자로 시작
		String str = "abcd";
		System.out.println("str : "+str);
		
		final double PI = 3.14;
	}
	
	
	public void var2() {
		int num = 2147483647;
		long result = (long)num+1; // (long)num + (int)1 이기 때문에 1이 num이랑 같은 자료형으로 자동형변환 된다
		
		System.out.println("result : "+result);
	}
	
	public void print() {
		System.out.print("메시지 출력1"); //출력 하고 끝
		System.out.println("메시지 출력2"); //출력 후 다음 줄로 넘어가기
		System.out.printf("메시지 출력3"); //print와 유사
		System.out.println("--------------------");
		//안녕하세요 제 이름은 김홍미입니다 나이는 20살입니다 ^^
		
		String name = "김홍미";
		int age = 30;
		System.out.println("안녕하세요 제 이름은 "+name+"입니다 나이는"+age+"살입니다^^");
		System.out.print("안녕하세요 제 이름은 ");
		System.out.print(name);
		System.out.print("이고 나이는 ");
		System.out.print(age);
		System.out.println("살입니다~ㅎㅎ");
		
		System.out.printf("안녕하세요 제 이름은 %s입니다. 나이는 %d살입니다.",name,age);
		System.out.println("");
		double dNum = 1.125456;
		System.out.println("dNum : "+dNum+"\n");
		System.out.printf("printf를 사용한 dNum : %f \n",dNum);
		System.out.printf("printf와 소숫점을 사용한 dNum %.3f\n",dNum);
		
	}
	
	
	public void exam1() {
		int iNum = 100;
		long lNum = 999999999999l;
		float fNum = 486.520f;
		double dNum = 5697.890123;
		char ch = 'A';
		String str = "Hello JAVA";
		boolean bool = true;
		
		System.out.println(iNum);
		System.out.println(lNum);
		System.out.printf("%.3f\n",fNum); //소수점 0까지 표시
		System.out.println(dNum);
		System.out.println(ch);
		System.out.println(str);
		System.out.println(bool);
	}
	
	
	public void exam2() {
		String name = "김홍미";
		int age = 30;
		char gender = 'F';
		String address = "인천";
		String phoneNumber = "01096418864";
		String email = "janet195@naver.com";
		
		String name2 = "두번째";
		int age2 = 20;
		char gender2 = 'M';
		String address2 = "서울";
		String phoneNumber2 = "01012345678";
		String email2 = "hello@naver.com";
		
		
		System.out.println("이름\t나이\t성별\t지역\t전화번호\t\t이메일");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%s\t%d\t%c\t%s\t%s\t%s\n",name,age,gender,address,phoneNumber,email);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%s\t%d\t%c\t%s\t%s\t%s\n",name2,age2,gender2,address2,phoneNumber2,email2);

	}
	
}
