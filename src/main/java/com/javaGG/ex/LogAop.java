package com.javaGG.ex;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
	
//	@Pointcut("execution(public void get*(..))") //public void인 모든 get메서드
//	@Pointcut("execution(* com.javaGG.ex.*.*())") //com.javaGG.ex 패키지에 파라미터가 없는 모든 메서드	
//	@Pointcut("execution(* com.javaGG.ex..*.*())") //com.javaGG.ex 패키지 & com.javaGG.ex 하위 패키지에 파라미터가 없는 모든 메서드 
//	@Pointcut("execution(* com.javaGG.ex.Worker.*())") //com.javaGG.ex.Worker 클래스안에 있는 모든 메서드
		
//	@Pointcut("within(com.javaGG.ex.*)") //com.javaGG.ex 패키지 안에 있는 모든 메서드
//	@Pointcut("within(com.javaGG.ex..*)") //com.javaGG.ex 패키지 & 하위 패키지 안에 있는 모든 메서드
//	@Pointcut("within(com.javaGG.ex.Worker)") //com.javaGG.ex.Worker 클래스 안에 있는 모든 메서드
	
//	@Pointcut("bean(student)") //student 빈에만 적용
	@Pointcut("bean(*ker)") //*ker로 끝나는 이름의 빈에만 적용
	
	private void pointcutMethod() {
		
	}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint jointpoint) throws Throwable { 
		// around 대상 객체의 메소드가 실행 전,후 또는 익셉션 발생시점에서 공통기능 실행
		
		String signatureStr = jointpoint.getSignature().toShortString();
		System.out.println(signatureStr + "메서드가 시작 되었습니다!");
		long st = System.currentTimeMillis();
				
		try {
			Object obj = jointpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + "메서드가 종료 되었습니다!");
			System.out.println(signatureStr + "경과시간 : " + (et-st));
		}
	}
	
	@Before("within(com.javaGG.ex.*)")
	public void beforeAdvice(JoinPoint joinPoint) { //before 대상 객체의 메소드 호출 전에 공통기능 실행
		System.out.println("before Advice가 실행됨");
	}
	
}