package com.oracle.Angbit.dao.rank;

import com.oracle.Angbit.model.common.MemberInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RankDaoImpl implements RankDao{

    @Autowired
    private SqlSession session;

    @Override
    public ArrayList<MemberInfo> getRank() {
        List<MemberInfo> list = new ArrayList<>();
        list = session.selectList("getRank");
        return (ArrayList<MemberInfo>) list;
    }
}
