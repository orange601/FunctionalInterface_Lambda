package com.date.functional;

public class Application {

	public static void main(String[] args) {
		RunSomething rs = new RunSomething() {
			@Override
			public void doit() {
				System.out.println("Hello, World");
			}
		};
		
		RunSomething rs2 = () -> System.out.println("Hello, World");
		rs2.doit();
		
		// 익명 내부클래스
		Calc cal = new Calc() {
			@Override
			public int plus(int number) {
				return number + 1;
			}
		};
		
		System.out.println(cal.plus(10)); // 같은 값을 넣으면 같은 값이 나와야 한다.
		System.out.println(cal.plus(10));
		

		Calc ccc = new Calc() {
			int num = 10;
			@Override
			public int plus(int number) {
				num++;
				return num + number;
			}
		};
		
		System.out.println(ccc.plus(10)); // 같은 값을 넣으면 같은 값이 나와야 한다.
		System.out.println(ccc.plus(10)); // 값이 다르므로 순수 함수 가 아니다
		
		
	}

}
