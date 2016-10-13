package hongxinCRM;

import java.util.ArrayList;
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
		resourceBak.setSeq(1);
		resourceBak.setPicUrl("hei");
		resourceBak.setSourceUrl("/custom/baseinfo.action");
		resourceBak.setSourceCode("111");
		resourceBak.setStyle("hei");
		resourceBak.setSourceName("123");
		
		ResourceBak parentres=menuService.get(10010);
		//resourceBak.setParentResourceBak(parentres);
		menuService.save(resourceBak);
	}
	
	@Test
	public void findAll() {
		//一级菜单
		List<ResourceBak> parentResourceBakList=menuService.getSubMenuList(null);
		for (ResourceBak resourceBak : parentResourceBakList) {
			System.out.println("--一级菜单开头--"+resourceBak.getSeq().toString()+resourceBak.getSourceName()+"--一级菜单开头--");
			//二级菜单
			List<ResourceBak> resourceBakList=menuService.getSubMenuList(Integer.toString(resourceBak.getSourceId()));
			resourceBak.setChildMenus(resourceBakList);
			
			for (int i = 0; i < resourceBakList.size(); i++) {
				System.out.println("--菜单--"+resourceBakList.get(i).getSeq().toString()+resourceBakList.get(i).getSourceName());
			}
			System.out.println("--一级菜单结束--"+resourceBak.getSeq().toString()+resourceBak.getSourceName()+"--一级菜单结束--");
		}
		
		List lists=new ArrayList();
		
		
		
	}
	
}