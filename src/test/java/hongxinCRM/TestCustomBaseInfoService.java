package hongxinCRM;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fuiou.data.QueryBalanceReqData;
import com.fuiou.data.QueryBalanceRspData;
import com.fuiou.service.FuiouService;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.service.CheckInfoService;
import com.hongxin.service.CheckReceiptsService;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.service.CustomStatusService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
public class TestCustomBaseInfoService {

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

	@Test
	public void save() {
		CustomBaseInfo customBaseInfo=new CustomBaseInfo();
		customBaseInfo.setId("xihuodong");
		customBaseInfo.setCustname("xiaodong");
		customBaseInfo.setPapertype("1");
		customBaseInfo.setPapernum("yy78754758724285");
		customBaseInfo.setPhonenum("2334234354");
		String id=customBaseInfoService.save4StrId(customBaseInfo);
		System.out.println(id);
	}
	
	@Test
	public void findAll() {
		List<CustomBaseInfo>customBaseInfos=customBaseInfoService.findAll();
		for (CustomBaseInfo customBaseInfo : customBaseInfos) {
			//customBaseInfo.setCheckInfo(checkInfoService.getByCustomId(customBaseInfo.getId()));
			customBaseInfo.setCheckReceipts(checkReceiptsService.getByStrId(customBaseInfo.getId()));
			customBaseInfo.setCustomStatus(customStatusService.getByStrId(customBaseInfo.getId()));
			customBaseInfo.setCustomAccount(customAccountService.getStrId(customBaseInfo.getId()));
		}
	}
	@Test
	public void findAllFirstAudit(){
		/*List<CustomBaseInfo>customBaseInfos=customBaseInfoService.findAllFirstAudit();
		System.out.println(customBaseInfos.size());*/
		QueryBalanceReqData qb=new QueryBalanceReqData();
		try {
			qb.setMchnt_cd("0002900F0096235");
			qb.setMchnt_txn_ssn("fasd541545asdaa");
			qb.setMchnt_txn_dt("20160518");
			qb.setCust_no("15210147466");
			QueryBalanceRspData result = FuiouService.balanceAction(qb);
			System.out.println(result.getResults().get(0).getCf_balance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}