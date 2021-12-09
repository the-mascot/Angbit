package com.oracle.Angbit.dao.rank;

import com.oracle.Angbit.model.common.MemberInfo;

import java.util.ArrayList;

public interface RankDao {
    ArrayList<MemberInfo> getRank(int startRow, int endRow);
    int getTotalCnt();
}
