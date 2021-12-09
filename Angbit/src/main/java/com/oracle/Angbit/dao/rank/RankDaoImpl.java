package com.oracle.Angbit.dao.rank;

import com.oracle.Angbit.model.common.MemberInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RankDaoImpl implements RankDao{

    @Autowired
    private SqlSession session;

    @Override
    public ArrayList<MemberInfo> getRank(int startRow, int endRow) {
        Map vo = new HashMap();
        vo.put("startRow", startRow);
        vo.put("endRow", endRow);
        List<MemberInfo> list = new ArrayList<>();
        list = session.selectList("getRank", vo);
        return (ArrayList<MemberInfo>) list;
    }

    @Override
    public int getTotalCnt() {
        int totCnt = session.selectOne("getTotCntMember");
        return totCnt;
    }
}
