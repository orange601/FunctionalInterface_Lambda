package com.date.javafunc;

import java.util.function.Function;

// 자바에서 기본으로 제공하는 함수형 인터페이스
public class Plus10 implements Function<Integer, Integer>{
	@Override
	public Integer apply(Integer t) {
		return t + 10;
	}
}
