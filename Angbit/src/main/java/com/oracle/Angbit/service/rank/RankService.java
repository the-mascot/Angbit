package com.oracle.Angbit.service.rank;

import com.oracle.Angbit.model.common.MemberInfo;

import java.util.ArrayList;

public interface RankService {

    ArrayList<MemberInfo> getRank(int startRow, int endRow);
    int getTotalCnt();
}
