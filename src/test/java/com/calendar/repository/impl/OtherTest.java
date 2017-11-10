package com.calendar.repository.impl;

import com.calendar.utils.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OtherTest {

    @Test
    public void testSet(){
        Date date1 = DateUtils.getDate("2017-02-05");
        Date date2 = DateUtils.getDate("2017-02-05");
        String aaa = date1.toString();
        String bbb = date2.toString();
        Set<String> set = new HashSet<String>();
        set.add(aaa);
        set.add(bbb);
        System.out.println(set.size());
    }

}
