package com.oracle.Angbit.service.rank;

import com.oracle.Angbit.dao.rank.RankDao;
import com.oracle.Angbit.model.common.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RankServiceImpl implements RankService{

    @Autowired
    private RankDao rd;
    @Override
    public ArrayList<MemberInfo> getRank(int startRow, int endRow) {
        return rd.getRank(startRow, endRow);
    }

    public int getTotalCnt() {
        return rd.getTotalCnt();
    }
}
