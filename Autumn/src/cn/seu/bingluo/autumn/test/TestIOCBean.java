package cn.seu.bingluo.autumn.test;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * By Bingluo 2012.8.17 cose.seu.edu.cn 测试Bean
 * 
 * @author tanchengwei
 * 
 */
public class TestIOCBean {
	private int a;
	private Integer aa;
	private String b;

	private TestIOCBean innerBean;
	private TestIOCBean outterBean;

	private Collection<String> collection;
	private Map<String, String> map;
	private Properties props;

	public TestIOCBean() {

	}

	public TestIOCBean(int a, String b, TestIOCBean oBeanTest) {
		this.setA(a);
		this.setB(b);
		this.setOutterBean(oBeanTest);
	}

	public Integer getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public Integer getAa() {
		return aa;
	}

	public void setAa(Integer aa) {
		this.aa = aa;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public TestIOCBean getInnerBean() {
		return innerBean;
	}

	public void setInnerBean(TestIOCBean innerBean) {
		this.innerBean = innerBean;
	}

	public TestIOCBean getOutterBean() {
		return outterBean;
	}

	public void setOutterBean(TestIOCBean outterBean) {
		this.outterBean = outterBean;
	}

	public Collection<String> getCollection() {
		return collection;
	}

	public void setCollection(Collection<String> collection) {
		this.collection = collection;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
}
