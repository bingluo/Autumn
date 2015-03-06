package cn.seu.bingluo.autumn.test;

import cn.seu.bingluo.autumn.ProceedingJoinPoint;

/**
 * 
 * ��ǿ�� ���ڲ���AOP����
 * 
 * @author tanchengwei
 * 
 */
public class Advice {
	public void Before() {
		System.out.println("Before...");
	}

	public Object Around(ProceedingJoinPoint joinPoint) {
		System.out.println("Before-around...");
		Object object = joinPoint.excute();
		System.out.println("After-around...");
		return object;
	}

	public void After() {
		System.out.println("After...");
	}
}
