package com.oracle.Angbit.service.myInfo;

import com.oracle.Angbit.dao.invest.InvestDao;
import com.oracle.Angbit.dao.myInfo.myInfoDao;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.service.invest.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class myInfoServiceImpl implements myInfoService {
	
	@Autowired
	private myInfoDao midao;

	@Override
	public int loginTest(Model model) {
		MemberInfo mi = new MemberInfo();
		mi.setId((String) model.getAttribute("id"));
		mi.setPassword((String) model.getAttribute("pw"));
		int result = midao.loginTest(mi);
		return result;
	}
}
