package hongxinCRM;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hongxin.entity.Organization;
import com.hongxin.service.EmployStaffService;
import com.hongxin.service.OrganizationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
public class TestEmployStaffService {

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private EmployStaffService employStaffService;

	@Test
	public void findAll(){
		List<Organization> orgs=organizationService.findAll();
		System.out.println(orgs.size());
	}
	
	
}