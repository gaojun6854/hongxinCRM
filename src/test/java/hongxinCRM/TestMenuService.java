package hongxinCRM;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hongxin.entity.ResourceBak;
import com.hongxin.service.MenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
public class TestMenuService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(TestMenuService.class);

	@Autowired
	private MenuService menuService;

	@Test
	public void save() {
		ResourceBak resourceBak=new ResourceBak();
		resourceBak.setSeq("1");
		resourceBak.setPicUrl("hei");
		resourceBak.setSourceUrl("/custom/baseinfo.action");
		resourceBak.setSourceCode("111");
		resourceBak.setStyle("hei");
		resourceBak.setSourceName("123");
		
		ResourceBak parentres=menuService.get(10010);
		resourceBak.setParentResourceBak(parentres);
		menuService.save(resourceBak);
	}
	
	@Test
	public void findAll() {
		List<ResourceBak> resourceBakList=menuService.findAll();
		for (ResourceBak resourceBak : resourceBakList) {
			System.out.println(resourceBak.getSourceId());
		}
	}
	
}