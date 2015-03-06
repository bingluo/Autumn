package cn.seu.bingluo.autumn.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.seu.bingluo.autumn.ConfigManager;

public class Driver {

	/**
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		// ����׮

		// ����IOC�����ļ�
		ConfigManager configManager = new ConfigManager("config.xml");
		// ��ȡidΪ��bean����Bean
		TestIOCBean beanTest = (TestIOCBean) configManager.getBean("bean");
		System.out.println(beanTest.getA() + ":" + beanTest.getAa() + ":"
				+ beanTest.getB());

		System.out.println(beanTest.getInnerBean().getB());
		System.out.println(beanTest.getInnerBean().getOutterBean().getB());

		String string = (String) configManager.getBean("str1");
		System.out.println(string);

		string = (String) configManager.getBean("str2");
		System.out.println(string);

		// װ�伯�ϲ���
		List<String> list = (List<String>) beanTest.getCollection();
		System.out.println(list.get(0));

		Set<String> set = (Set<String>) beanTest.getInnerBean().getCollection();
		for (Iterator it = set.iterator(); it.hasNext();) {
			System.out.println("value=" + it.next().toString());
		}

		TestIOCBean outterBeanTest = (TestIOCBean) configManager
				.getBean("outterBean");
		System.out.println(outterBeanTest.getMap().get("s1"));
		System.out.println(outterBeanTest.getProps().get("s3"));

		// ���Թ��캯��ע��
		TestIOCBean constructorBeanTest = (TestIOCBean) configManager
				.getBean("constructorInit");
		System.out.println(constructorBeanTest.getA()
				+ constructorBeanTest.getB()
				+ constructorBeanTest.getOutterBean().getAa());

		// ����AOP����
		System.out.println("\n***************** AOP���� *****************");
		ITestAOPBean testBean = (ITestAOPBean) configManager
				.getBean("shoutBean");
		testBean.shout();
	}
}
