package cn.seu.bingluo.autumn.test;

/**
 * 
 * 测试AOP功能 用jdk动态代理实现，故必须继承接口
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
