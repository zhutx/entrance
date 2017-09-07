package com.moredian.attence.test;

import com.xier.sesame.attence.AppStarter;
import com.xier.sesame.attence.domain.RecognizeDailyReport;
import com.xier.sesame.attence.manager.RecognizeDailyReportManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by xxu on 2017/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
public class GenerateDailyReportTest {

    @Autowired
    private RecognizeDailyReportManager recognizeDailyReportManager;

    @Test
    public void testAddSwitchSettting(){

        String startTime="2017-05-03 00:00:00";
        String endTime= "2017-05-05 00:00:00";
        Long orgId=1565717202361909248L;

        RecognizeDailyReport result =recognizeDailyReportManager.getRecordCountByTimeRangeAndOrgId(startTime,endTime,orgId);
        Assert.assertTrue(result.getTotalTimes()>0);
        System.out.println(result.getTotalTimes());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long start=null;
        Long end= null;
        try {
            end = sdf.parse(endTime).getTime();
            start=sdf.parse(startTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(start);
        System.out.println(end);


        RecognizeDailyReport result1 =recognizeDailyReportManager.getSourceRecordCountByTimeRangeAndOrgId(start,end,orgId);
        Assert.assertTrue(result1.getTotalTimes()>0);
        System.out.println(result1.getTotalTimes());

    }




}
