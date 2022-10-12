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

## 람다 자세히 ##
- (인자 리스트) -> {바디}

1. 인자 리스트
	- 인자가 없을 때: ()
	- 인자가 한개일 때: (one) 또는 one
	- 인자가 여러개 일 때: (one, two)
	- 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)

2. 바디
	- 화상표 오른쪽에 함수 본문을 정의한다.
	- 여러 줄인 경우에 { }를 사용해서 묶는다.
	- 한 줄인 경우에 생략 가능, return도 생략 가능

3. 변수 캡처 (Variable Capture)
	- 로컬 변수 캡처
		+ final이거나 effective final 인 경우에만 참조할 수 있다.
		+ 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일가 방지한다.
		
	- effective final
		+ 이것도 역시 자바 8부터 지원하는 기능으로 “사실상" final인 변수.
		+ final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
	
	- 익명 클래스 구현체와 달리 ‘쉐도윙’하지 않는다.
		+ 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.

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
	// 혹은 구현 class를 만들지 않고, 바로 사용가능하다.
	Function<Integer, Integer> plus8 = (i) -> i + 8;
	System.out.println(plus8.apply(1));
	````
	````java
	Function<Integer, Integer> plus8 = (i) -> i + 8;
	Function<Integer, Integer> multiply2 = (i) -> i * 2;
	
	// 곱하기 후 더하기 // compose 
	System.out.println(plus8.compose(multiply2).apply(2));	
	
	// 더하기 후 곱하기 // andThen
	System.out.println(plus8.andThen(multiply2).apply(2));
	````
	
	
	- ### BiFunction<T, U, R> ###
	````
	- 두 개의 값(T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
		* R apply(T t, U u)
	````
	````java
	// Function과 같지만 인자를 2개 받는다.
	BiFunction<Integer, Integer, Integer> plusplus = (n, m) -> n + m;
	System.out.println(plusplus.apply(1, 2));
	````

	
	- ### Consumer<T> ###
	````
	- T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
		* void Accept(T t)
	- 함수 조합용 메소드
		* andThen
	````
	````java
	// void
	Consumer<String> consumer = s -> System.out.println(s.toUpperCase());
	consumer.accept("hello world");
	````
	
	
	- ### Supplier<T> ###
	````
	- T 타입의 값을 제공하는 함수 인터페이스
		* T get()
	````
	````java
	// 인자를 받지않고, 리털할 타입만 결정한다.
        Supplier<String> supplier= ()-> "HelloWorld";
        System.out.println(supplier.get());
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
	````java
	// true or false 리턴
	Predicate<String> startsWithJun = (s) -> s.startsWith("jun");
	System.out.println(startsWithJun.test("junAndJhon"));
	````
	
	- ### UnaryOperator<T> ###
	````
	- Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
	````
	````java
	// Function에서 인자 타입과 리턴타입이 같을때 사용한다.
	UnaryOperator<Integer> plus1 = (i) -> i + 1;
	UnaryOperator<Integer> multiply4 = (i) -> i * 4;
	System.out.println(plus1.apply(2));
	System.out.println(multiply4.apply(2));
	````
	
	- ### BinaryOperator<T> ###
	````
	- BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입렵값 두개를 받아 리턴하는 함수 인터페이스
	````
	````java
	// BiFunction에서 인자타입2개와 리턴타입 모두가 같을때 사용
	BinaryOperator<Integer> binaryOperator = (n1, n2) -> n1 + n2;
	Integer sum = binaryOperator.apply(10, 100);
	System.out.println(sum);	
	````

## 메소드 레퍼런스 ##
- 람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있다.
- 메소드 또는 생성자의 매개변수로 람다의 입력값을 받는다.
- 리턴값 또는 생성한 객체는 람다의 리턴값이다.

참조이름 | 형태 |
---- | ---- | 
스태틱 메소드 참조 | 타입::스태틱 메소드 |
특정 객체의 인스턴스 메소드 참조 | 객체 레퍼런스::인스턴스 메소드 |
임의 객체의 인스턴스 메소드 참조 | 타입::인스턴스 메소드 |
생성자 참조 | 타입::new |

### 1. 스태틱 메소드 참조 ###
````java
UnaryOperator<String> hi2 = Greeting::hi;
System.out.println(hi2.apply("jun"));

public class Greeting {
	public static String hi(String name) {
		return "hi " + name;
	}
}
````

### 2. 특정 객체의 인스턴스 메소드 참조 ###
````java
Greeting greeting = new Greeting();
UnaryOperator<String> hollo = greeting::hello;
System.out.println(hollo.apply("orange"));
	
public class Greeting {
	public String hello(String name) {
		return "name " + name;
	}
}
````

### 4. 생성자 참조 ###
````java
// 인자가 없는 생성자 레퍼런스
Supplier<Greeting> newgreeting = Greeting::new;
Greeting gre = newgreeting.get();

// 인자를 받는 생성자 래퍼런스
Function<String, Greeting> myGreeting = Greeting::new;
Greeting gre2 = myGreeting.apply("me name");
System.out.println(gre2.getName());

public class Greeting {
	private String name;
	public Greeting() {
		
	}
	
	public Greeting(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}	
````
