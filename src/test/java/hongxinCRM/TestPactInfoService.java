package hongxinCRM;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.entity.TPactInfo;
import com.hongxin.service.CheckInfoService;
import com.hongxin.service.CheckReceiptsService;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.service.CustomStatusService;
import com.hongxin.service.PactInfoService;
import com.hongxin.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
public class TestPactInfoService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(TestMenuService.class);

	@Autowired
	private CustomBaseInfoService customBaseInfoService;
	@Autowired
	private CheckInfoService checkInfoService;
	@Autowired
	private CheckReceiptsService checkReceiptsService;
	@Autowired
	private CustomAccountService customAccountService;
	@Autowired
	private CustomStatusService customStatusService;
	@Autowired
	private PactInfoService pactInfoService;
	@Autowired
	private ProductService productService;
	
	@Test
	public void findAll(){
		List<TPactInfo>pactInfos=pactInfoService.findAll();
		System.out.println(pactInfos.size());
	}
	@Test
	public void save(){
		TPactInfo entity =new TPactInfo();
		entity.setCustId("9389d621-e4a9-4692-91a6-1179aeeb19b9");
		entity.setProductId("1007");
		entity.setAmount(new Double(111.00));
		entity.setContractNumber("1111111111");
		entity.setManagerNum(Integer.toString(1111));
		entity.setManagerName("hfjashdfjk");
		pactInfoService.save(entity);
	}
	@Test
	public void getInfo(){
		TPactInfo pactInfo=new TPactInfo();
		pactInfo=pactInfoService.get("09422336-74c3-4f58-9ef4-a889f0895bd9");
		pactInfo.setProductInfo(productService.getStrId(pactInfo.getProductId()));
		pactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(pactInfo.getCustId()).get(0));
		List<CheckReceipts>receipts=checkReceiptsService.getByStrIdType("09422336-74c3-4f58-9ef4-a889f0895bd9","3");
		pactInfo.setReceipts(receipts);
		System.out.println(receipts.size());
	}
	
	
	@Test
	public void findCheckReceipts(){
		List<CheckReceipts>receipts=checkReceiptsService.getByStrIdType("bef775a5-0e4d-40e0-ae4b-8ade20fe28c9","2");
		
		for (CheckReceipts checkReceipt : receipts) {
			System.out.println(checkReceipt.getRecFile());
		}
	}
}