package cn.seu.bingluo.autumn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ���ڴ���AOP������ʱ����װ�����Ϣ��Ϊͳһ�������д���
 * 
 * @author tanchengwei
 * 
 */
public class ProceedingJoinPoint {
	private Object object;// ������Ķ���
	private Method method;// ������ķ���
	private Object[] args;// ������Ӧ�Ĳ���

	public ProceedingJoinPoint(Object object, Method method, Object[] args) {
		this.object = object;
		this.method = method;
		this.args = args;
	}

	// ִ��Ŀ�꺯��
	public Object excute() {
		Object result = null;
		try {
			result = method.invoke(object, args);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
}
