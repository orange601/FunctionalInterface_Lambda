package com.date.javafunc;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Application {

	// 자바에서 기본으로 제공하는 함수형 인터페이스
	public static void main(String[] args) {
		Function<Integer, Integer> plus8 = (i) -> i + 8;
		Function<Integer, Integer> multiply2 = (i) -> i * 2;
		// 곱하기 후 더하기
		System.out.println(plus8.compose(multiply2).apply(2));
		// 더하기 후 곱하기
		System.out.println(plus8.andThen(multiply2).apply(2));
		
		// Function과 같지만 인자를 2개 받는다.
		BiFunction<Integer, Integer, Integer> plusplus = (n, m) -> n + m;
		System.out.println(plusplus.apply(1, 2));
		
		// void
		Consumer<String> consumer = s -> System.out.println(s.toUpperCase());
        consumer.accept("hello world");
        
        // 인자를 받지않고, 리털할 타입만 결정한다.
        Supplier<String> supplier= ()-> "HelloWorld";
        System.out.println(supplier.get());
        
		// true or false 리턴
		Predicate<String> startsWithJun = (s) -> s.startsWith("jun");
		System.out.println(startsWithJun.test("junAndJhon"));
		
		// Function에서 인자 타입과 리턴타입이 같을때 사용한다.
		UnaryOperator<Integer> plus1 = (i) -> i + 1;
		UnaryOperator<Integer> multiply4 = (i) -> i * 4;
		System.out.println(plus1.apply(2));
		System.out.println(multiply4.apply(2));
		
		// BiFunction에서 인자타입2개와 리턴타입 모두가 같을때 사용
		BinaryOperator<Integer> binaryOperator = (n1, n2) -> n1 + n2;
		Integer sum = binaryOperator.apply(10, 100);
	    System.out.println(sum);
	}

}
