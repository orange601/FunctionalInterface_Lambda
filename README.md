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
1. [함수형인터페이스 목록(JAVA )](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
2. 자바에서 미리 정의해둔 자주 사용할만한 함수 인터페이스
	- Function<T, R>
	- BiFunction<T, U, R>
	- Consumer<T>
	- Supplier<T>
	- Predicate<T>
	- UnaryOperator<T>
	- BinaryOperator<T>
