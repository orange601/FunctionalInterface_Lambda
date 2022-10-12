# 함수형 인터페이스와 람다 표현식
함수형 인터페이스와 람다 표현식

## 함수형 인터페이스 (Functional Interface) ##
- 추상 메소드를 딱 하나만 가지고 있는 인터페이스, SAM (Single Abstract Method) 인터페이스
````java
public interface RunSomething {
	abstract void doit(); // 추상 메서드가 하나인 함수형 인터페이스 // abstract 생략가능
	// abstract void doit2(); // 추상 메서드가 2개 이상이면 함수형 인터페이스가 아니다.
}
````
- 혹은 @FunctionalInterface 애노테이션을 가지고 있는 인터페이스
````java
@FunctionalInterface // 함수형 인터페이스를 더 견고하게 만들기 위해서 사용한다.
public interface RunSomething {
	abstract void doit();
	// abstract void doit2(); 추상 메서드가 2개 이상이면 ERROR가 발생한다.
}
````

## 람다 표현식 (Lambda Expressions) ##
- 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있다.
- 코드를 줄일 수 있다.
- 메소드 매개변수, 리턴 타입, 변수로 만들어 사용할 수도 있다.
- 인터페이스 구현 ( 람다 X )
````java
// 익명 내부클래스
RunSomething rs = new RunSomething() {
	@Override
	public void doit() {
		System.out.println("Hello, World!");
	}
};
````
- 람다식을 사용한 인터페이스 구현
````java
// 함수명 생략 // 중괄호 { 생략
RunSomething rs = () -> System.out.println("Hello, World!");
````
````java
RunSomething rs = new RunSomething() {
	@Override
	public int doit(int num) {
		return num + 1;
	}
};
// 파라미터가 있다면 이런식으로 줄일 수 있다.
RunSomething rs2 = n -> n + 1;
````

## 자바에서 함수형 프로그래밍 ##
- 함수를 First class object로 사용할 수 있다.
- 순수 함수 (Pure function)
    + 같은 값을 넣으면 같은 값이 나와야 한다.
    + 사이드 이팩트가 없다. (함수 밖에 있는 값을 변경하지 않는다.)
    + 상태가 없다. (함수 밖에 있는 값을 사용하지 않는다.)
````java
RunSomething rs = new RunSomething() {
	int num = 10;
	@Override
	public int doit(int number) {
		num++;
		return num + number;
	}
};

System.out.println(rs.doit(10)); // 같은값을 넣으면 같은값이 나와야 하는데
System.out.println(rs.doit(10)); // 값이 다르므로 순수 함수 가 아니다
````

- 고차 함수 (Higher-Order Function)
    + 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
- 불변성


## 자바에서 제공하는 함수형 인터페이스 ##
1. [함수형인터페이스 목록(JAVA DOCS)](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
2. 자바에서 미리 정의해둔 자주 사용할만한 함수 인터페이스
	
	- ### Function<T, R> ###
	````
	- T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
		* R apply(T t)
	- 함수 조합용 메소드
		* andThen
		* compose
	````
	````java
	// 자바에서 기본으로 제공하는 함수형 인터페이스
	public class Plus10 implements Function<Integer, Integer>{
		@Override
		public Integer apply(Integer t) {
			return t + 10;
		}
	}
	
	public class Application {
		public static void main(String[] args) {
			Plus10 plus10 = new Plus10();
			System.out.println(plus10.apply(1));
		}
	}
	````
	````java
	Function<Integer, Integer> plus8 = (i) -> i + 8; // 따로 class를 만들지 않아도 된다.
	System.out.println(plus8.apply(1));
	````
	
	- ### BiFunction<T, U, R> ###
	````
	- 두 개의 값(T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
		* R apply(T t, U u)
	````
	
	- ### Consumer<T> ###
	````
	- T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
		* void Accept(T t)
	- 함수 조합용 메소드
		* andThen
	````
	
	- ### Supplier<T> ###
	````
	- T 타입의 값을 제공하는 함수 인터페이스
		* T get()
	````
	
	- ### Predicate<T> ###
	````
	- T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
		* boolean test(T t)
	- 함수 조합용 메소드
		* And
		* Or
		* Negate
	````
	
	- ### UnaryOperator<T> ###
	````
	- Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
	````
	
	- ### BinaryOperator<T> ###
	````
	- BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입렵값 두개를 받아 리턴하는 함수 인터페이스
	````
