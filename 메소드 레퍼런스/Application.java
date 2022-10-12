package com.date.methodreferences;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Application {

	public static void main(String[] args) {
		UnaryOperator<String> hi = (s) -> "hi " + s;
		System.out.println(hi.apply("jun"));
		
		// 메서드 레퍼런스 ( static 메서드 )
		UnaryOperator<String> hi2 = Greeting::hi;
		System.out.println(hi2.apply("jun"));
		
		// 일반 메서드 ( static 메서드 X ) 
		Greeting greeting = new Greeting();
		UnaryOperator<String> hollo = greeting::hello;
		System.out.println(hollo.apply("orange"));
		
		// 인자가 없는 생성자 레퍼런스
		Supplier<Greeting> newgreeting = Greeting::new;
		Greeting gre = newgreeting.get();
		
		// 인자를 받는 생성자 래퍼런스
		Function<String, Greeting> myGreeting = Greeting::new;
		Greeting gre2 = myGreeting.apply("me name");
		System.out.println(gre2.getName());
		
		
		// 임의 객체의 인스턴스 메소드 참조
		String[] names = {"test", "jun", "tom"};
		Arrays.sort(names, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		});
		// 이렇게 변경가능
		Arrays.sort(names, (String o1, String o2) -> 0);
		System.out.println(Arrays.toString(names)); // 정렬안됨
		// 혹은 
		Arrays.sort(names, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(names)); // 정렬 됨
	}

}
