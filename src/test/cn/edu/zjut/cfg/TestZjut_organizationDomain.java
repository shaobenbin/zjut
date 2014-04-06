package test.cn.edu.zjut.cfg;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestZjut_organizationDomain extends TestCase{
	
	public void testLoad(){
		Zjut_organizationDomain zjut_organizationDomain = new Zjut_organizationDomain();
		zjut_organizationDomain.setName("college");
		zjut_organizationDomain.loadEqual();
		Assert.assertEquals(1, zjut_organizationDomain.getNewList().size());
	}
}
