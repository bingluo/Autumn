package cn.seu.bingluo.autumn.test;

/**
 * 
 * ����AOP���� ��jdk��̬����ʵ�֣��ʱ���̳нӿ�
 * 
 * @author tanchengwei
 * 
 */
public class TestAOPBean implements ITestAOPBean {
	private String name = "aop-test";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int shout() {
		System.out.println("Hello, My name is " + name);
		return 1;
	}
}
