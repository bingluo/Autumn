package cn.seu.bingluo.autumn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * ������ ����jdk��̬����ʵ�֣�Ҫ��ǰ���Ǳ��������ʵ�ֽӿ�
 * 
 * @author tanchengwei
 * 
 */
public class ProxyHandler implements InvocationHandler {
	// �洢��������
	private static HashMap<String, AspectInfo> aspectInfos = new HashMap<String, AspectInfo>();

	// ������Ķ���
	private Object target = null;

	public ProxyHandler(Object target) {
		this.target = target;
	}

	public static void addAspectInfo(AspectInfo aspectInfo) {
		aspectInfos.put(aspectInfo.getExpression(), aspectInfo);
	}

	// ��ȡ����ʵ��
	public Object getProxyInstance() {
		if (target == null) {
			return null;
		}
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	// ��ȡ����ʵ��
	public Object getProxyInstance(Object target) {
		if (target == null) {
			return null;
		}
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		ArrayList<AspectInfo> aspects = new ArrayList<AspectInfo>();
		Set<Entry<String, AspectInfo>> entrySet = aspectInfos.entrySet();
		Object result = null;

		// ���������б��ҵ���Ӧ������
		for (Entry<String, AspectInfo> entry : entrySet) {
			AspectInfo aspectInfo = entry.getValue();
			Object adviceBean = aspectInfo.getAdviceBean();
			String expression = aspectInfo.getExpression();

			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(target.getClass().getName() + "."
					+ method.getName());
			if (matcher.find()) {
				AspectInfo aspect = new AspectInfo();
				aspect.setAdviceBean(adviceBean);
				aspect.setBeforeMethod(aspectInfo.getBeforeMethod());
				aspect.setAroundMethod(aspectInfo.getAroundMethod());
				aspect.setAfterMethod(aspectInfo.getAfterMethod());
				aspects.add(aspect);
			}
		}

		// ִ��before��ǿ
		for (AspectInfo aspect : aspects) {
			Object adviceBean = aspect.getAdviceBean();
			if (aspect.getBeforeMethod() != null) {
				aspect.getBeforeMethod().invoke(adviceBean, new Object[] {});
			}
		}

		// ִ��around��ǿ
		Object aroundAdviceBean = target;
		Method aroundAdviceMethod = method;
		Object[] aroundAdviceArgs = args;
		for (AspectInfo aspect : aspects) {
			Object adviceBean = aspect.getAdviceBean();
			if (aspect.getAroundMethod() != null) {
				aroundAdviceArgs = new Object[] { new ProceedingJoinPoint(
						aroundAdviceBean, aroundAdviceMethod, aroundAdviceArgs) };
				aroundAdviceBean = adviceBean;
				aroundAdviceMethod = aspect.getAroundMethod();
			}
		}
		result = aroundAdviceMethod.invoke(aroundAdviceBean, aroundAdviceArgs);

		// ִ��After��ǿ
		for (AspectInfo aspect : aspects) {
			Object adviceBean = aspect.getAdviceBean();
			if (aspect.getAfterMethod() != null) {
				aspect.getAfterMethod().invoke(adviceBean, new Object[] {});
			}
		}
		return result;
	}

}
