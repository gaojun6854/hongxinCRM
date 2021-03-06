package hongxinCRM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.entity.UserInfo;
import com.hongxin.service.CheckReceiptsService;
import com.hongxin.service.UserInfoService;
import com.hongxin.utils.Constants;
import com.hongxin.utils.Date2String8;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
public class TestUserService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(TestUserService.class);

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CheckReceiptsService checkReceiptsService;

	@Test
	public void save() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName("name");
		userInfo.setAge(235634635);
		userInfo.setTelephone("1321233");
		//Integer id = userInfoService.save(userInfo);
	//	JSON.toJSONString(id);
	}
	
	@Test
	public void findAll() {
		List<UserInfo> userInfos=new ArrayList<UserInfo>();
		//userInfos = userInfoService.findAll();
		JSON.toJSON(userInfos.get(0));
	}
	
	@Test
	public void addcheckreceipts(){
		 CheckReceipts checkReceipts=new CheckReceipts();
		checkReceipts.setRecNum("4444444444444");//合同编号
		checkReceipts.setRecId("1");
		checkReceipts.setRecSer(1);//循环picList
		checkReceipts.setRecDesc("签约客户信息图片");
		checkReceipts.setRecType("1");
		checkReceipts.setRecInfo("签约");
		checkReceipts.setRecManDesc("签约");
		checkReceipts.setRecPath(Constants.FILE_SAVE_ADDRESS);
		checkReceipts.setRecFile("shabi");
		checkReceipts.setCreateDate(Date2String8.date2String(new Date()));
		checkReceipts.setCreateTime(Date2String8.time2String());
		checkReceiptsService.save4StrId(checkReceipts);
	}
	
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
}