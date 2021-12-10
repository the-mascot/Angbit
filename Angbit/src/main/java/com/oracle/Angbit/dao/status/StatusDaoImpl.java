package com.oracle.Angbit.dao.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.Angbit.model.common.Coin;
import com.oracle.Angbit.model.common.CoinInfo;
import com.oracle.Angbit.model.common.MemberInfo;
import com.oracle.Angbit.model.common.Trade;
import com.oracle.Angbit.model.status.CoinCoinInfo;
import com.oracle.Angbit.model.status.TradeCoinInfo;

@Repository
public class StatusDaoImpl implements StatusDao {

	@Autowired
	private SqlSession session;
	
//	투자현황
	@Override
	public List<CoinCoinInfo> listStatus(String id) {
		System.out.println("StatusDaoImpl listStatus start...");
		List<CoinCoinInfo> listStatus = null;
		listStatus = session.selectList("StatusList", id);
		return listStatus;
	}

	@Override
	public List<MemberInfo> listKrw(String id) {
		System.out.println("StatusDaoImpl listKrw start...");
		List<MemberInfo> krwList = null;
		krwList = session.selectList("KrwList", id);
		return krwList;
	}
	
	@Override
	public int priceTot(String id) {
		System.out.println("StatusDaoImpl priceTot start...");
		int result = session.selectOne("TotPrice", id);
		System.out.println("statusDaoImpl priceTot result -> "+result);
		return result;
	}

//	체결내역(전체)
	@Override
	public List<TradeCoinInfo> listY(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusDaoImpl listY start...");
		
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> yList = null;
		yList = session.selectList("YList", param);
		return yList;
	}
	
	@Override
	public List<TradeCoinInfo> allDateSort7(TradeCoinInfo trdCoin, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> allDateSort7 = session.selectList("AllDateSort7", param);
		System.out.println("StatusDaoImpl allDateSort7.size() -> "+allDateSort7.size());
		return allDateSort7;
	}

	@Override
	public List<TradeCoinInfo> allDateSort30(TradeCoinInfo trdCoin, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> allDateSort30 = session.selectList("AllDateSort30", param);
		System.out.println("StatusDaoImpl allDateSort30.size() -> "+allDateSort30.size());
		return allDateSort30;
	}

	@Override
	public List<TradeCoinInfo> allDateSort90(TradeCoinInfo trdCoin, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> allDateSort90 = session.selectList("AllDateSort90", param);
		System.out.println("StatusDaoImpl allDateSort90.size() -> "+allDateSort90.size());
		return allDateSort90;
	}

	@Override
	public List<TradeCoinInfo> allDateSort180(TradeCoinInfo trdCoin, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> allDateSort180 = session.selectList("AllDateSort180", param);
		System.out.println("StatusDaoImpl allDateSort180.size() -> "+allDateSort180.size());
		return allDateSort180;
	}

//	체결내역(매수)
	@Override
	public List<TradeCoinInfo> buyComList(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusDaoImpl buyComList start...");
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> buycList = session.selectList("CBuyList", param);
		return buycList;
	}
	
	@Override
	public List<TradeCoinInfo> buyDateSort7(TradeCoinInfo trdCoin, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> buyDateSort7 = session.selectList("buyDateSort7", param);
		System.out.println("StatusDaoImpl buyDateSort7.size() -> "+buyDateSort7.size());
		return buyDateSort7;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort30(TradeCoinInfo trdCoin, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> buyDateSort30 = session.selectList("buyDateSort30", param);
		System.out.println("StatusDaoImpl buyDateSort30.size() -> "+buyDateSort30.size());
		return buyDateSort30;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort90(TradeCoinInfo trdCoin, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> buyDateSort90 = session.selectList("buyDateSort90", param);
		System.out.println("StatusDaoImpl buyDateSort90.size() -> "+buyDateSort90.size());
		return buyDateSort90;
	}

	@Override
	public List<TradeCoinInfo> buyDateSort180(TradeCoinInfo trdCoin, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> buyDateSort180 = session.selectList("buyDateSort180", param);
		System.out.println("StatusDaoImpl buyDateSort180.size() -> "+buyDateSort180.size());
		return buyDateSort180;
	}

//	체결내역(매도)
	@Override
	public List<TradeCoinInfo> sellComList(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusDaoImpl sellComList start...");
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> sellList = session.selectList("CSellList", param);
		return sellList;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort7(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusDaoImpl sellDateSort7 start...");
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> sellDateSort7 = session.selectList("sellDateSort7", param);
		System.out.println("StatusDaoImpl sellDateSort7.size()->" + sellDateSort7.size());
		return sellDateSort7;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort30(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusDaoImpl sellDateSort30 start...");
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> sellDateSort30 = session.selectList("sellDateSort30", param);
		System.out.println("StatusDaoImpl sellDateSort30.size()->" + sellDateSort30.size());
		return sellDateSort30;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort90(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusDaoImpl sellDateSort90 start...");
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> sellDateSort90 = session.selectList("sellDateSort90", param);
		System.out.println("StatusDaoImpl sellDateSort90.size()->" + sellDateSort90.size());
		return sellDateSort90;
	}

	@Override
	public List<TradeCoinInfo> sellDateSort180(TradeCoinInfo trdCoin, String id) {
		System.out.println("StatusDaoImpl sellDateSort180 start...");
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("id", id);
		List<TradeCoinInfo> sellDateSort180 = session.selectList("sellDateSort180", param);
		System.out.println("StatusDaoImpl sellDateSort180.size()->" + sellDateSort180.size());
		return sellDateSort180;
	}

	@Override
	public List<TradeCoinInfo> listN(String id) {
		System.out.println("StatusDaoImpl listN start...");
		List<TradeCoinInfo> nList = null;
		nList = session.selectList("NList", id);
		return nList;
	}

	@Override
	public int total(String id) {
		int tot = 0;
		tot = session.selectOne("StatusTotal", id);
		return tot;
	}

	@Override
	public int total7(String id) {
		int tot = 0;
		tot = session.selectOne("StatusTotal7", id);
		return tot;
	}

	@Override
	public int total30(String id) {
		int tot = 0;
		tot = session.selectOne("StatusTotal30", id);
		return tot;
	}

	@Override
	public int total90(String id) {
		int tot = 0;
		tot = session.selectOne("StatusTotal90", id);
		return tot;
	}

	@Override
	public int total180(String id) {
		int tot = session.selectOne("StatusTotal180", id);
		return tot;
	}

	@Override
	public int buyTotal(String id) {
		int tot = session.selectOne("StatusBuyTotal", id);
		return tot;
	}

	@Override
	public int buyTotal7(String id) {
		int tot = session.selectOne("StatusBuy7", id);
		return tot;
	}

	@Override
	public int buyTotal30(String id) {
		int tot = session.selectOne("StatusBuy30", id);
		return tot;
	}

	@Override
	public int buyTotal90(String id) {
		int tot = session.selectOne("StatusBuy90", id);
		return tot;
	}

	@Override
	public int buyTotal180(String id) {
		int tot = session.selectOne("StatusBuy180", id);
		return tot;
	}

	@Override
	public int sellTotal(String id) {
		int tot = session.selectOne("StatusSellTotal", id);
		return tot;
	}

	@Override
	public int sellTotal7(String id) {
		int tot = session.selectOne("StatusSell7", id);
		return tot;
	}

	@Override
	public int sellTotal30(String id) {
		int tot = session.selectOne("StatusSell30", id);
		return tot;
	}

	@Override
	public int sellTotal90(String id) {
		int tot = session.selectOne("StatusSell90", id);
		return tot;
	}

	@Override
	public int sellTotal180(String id) {
		int tot = session.selectOne("StatusSell180", id);
		return tot;
	}

	@Override
	public List<TradeCoinInfo> searchList(TradeCoinInfo trdCoin, String search, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("search", search);
		param.put("id", id);
		System.out.println("daoImpl -> "+trdCoin.getStart());
		System.out.println("daoImpl -> "+trdCoin.getEnd());
		System.out.println("daoImpl -> "+search);
		System.out.println("daoImpl -> "+id);
		List<TradeCoinInfo> searchList = session.selectList("Statussearch", param);
		return searchList;
	}

	@Override
	public int searchTotal(String id, String search) {
		System.out.println("dao - "+search);
		System.out.println("dao - "+id);
		Map<String, String> param = new HashMap<String, String>();
		param.put("search", search);
		param.put("id", id);
		System.out.println("search -> "+param.get("search"));
		System.out.println("id -> "+param.get("id"));
		int tot = session.selectOne("searchTotal", param);
		System.out.println("result->"+tot);
		return tot;
	}

	@Override
	public List<TradeCoinInfo> searchBuyList(TradeCoinInfo trdCoin, String search, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("search", search);
		param.put("id", id);
		List<TradeCoinInfo> searchBuyList = session.selectList("StatusSearchBuy", param);
		return searchBuyList;
	}

	@Override
	public int searchBuy(String id, String search) {
		System.out.println("1111-> "+id);
		System.out.println("1111-> "+search);
		Map param = new HashMap();
		param.put("id", id);
		param.put("search", search);
		int tot = session.selectOne("searchBuy", param);
		return tot;
	}

	@Override
	public int searchSell(String id, String search) {
		Map param = new HashMap();
		param.put("id", id);
		param.put("search", search);
		int tot = session.selectOne("searchSell", param);
		return tot;
	}

	@Override
	public List<TradeCoinInfo> searchSellList(TradeCoinInfo trdCoin, String search, String id) {
		Map param = new HashMap();
		param.put("trdCoin", trdCoin);
		param.put("search", search);
		param.put("id", id);
		List<TradeCoinInfo> searchSellList = session.selectList("StatusSearchSell", param);
		return searchSellList;
	}




}
