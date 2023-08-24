package com.team.ram.dummytradefiller.dao;
import java.util.Random;

import com.team.ram.dummytradefiller.DummyTradeFillerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Repository
public class DummyTradeFiller {
    private static final Logger LOG = LoggerFactory.getLogger(DummyTradeFillerApplication.class);
    @Value("${mysql.table.name}")
    private String TABLE;

    @Value("${mysql.statuscode.colname}")
    private String STATUS_CODE;

    @Value("${percent.failures}")
    private int percentFailures = 10;

    @Autowired
    private JdbcTemplate template;

    @Scheduled(fixedRateString = "${proc.rate.ms:10000}")
    public int findTradesForProcessing() {
        String sql = "UPDATE " + TABLE + " SET " + STATUS_CODE + "=1 WHERE " + STATUS_CODE + "=0";
        int numberChanged = template.update(sql);

        LOG.debug("Updated [" + numberChanged + "] order from initialized (0) TO processing (1)");

        return numberChanged;
    }

    @Scheduled(fixedRateString = "${fill.rate.ms:15000}")
    public int findTradesForFillingOrRejecting() {
        int totalChanged = 0;
        int lastChanged = 0;

        do {
            lastChanged = 0;

            // use a random number to decide if we'll simulate success OR failure
            int randomInteger = new Random().nextInt(100);

            LOG.debug("Random number is [" + randomInteger +
                    "] , failure rate is [" + percentFailures + "]");

            if(randomInteger > percentFailures) {
                // Mark this one as success
                lastChanged = markTradeAsSuccessOrFailure(2);
                LOG.debug("Updated [" + lastChanged + "] order from processing (1) TO success (2)");
            }
            else {
                // Mark this one as failure!!
                lastChanged = markTradeAsSuccessOrFailure(3);
                LOG.debug("Updated [" + lastChanged + "] order from processing (1) TO failure (3)");
            }
            totalChanged += lastChanged;

        } while (lastChanged > 0);

        return totalChanged;
    }

    private int markTradeAsSuccessOrFailure(int successOrFailure) {
        String sql = "UPDATE " + TABLE + " SET " + STATUS_CODE + "=" +
                successOrFailure + " WHERE " + STATUS_CODE + "=1 limit 1";
        return template.update(sql);
    }

    public void setPercentFailures(int percentFailures) {
        this.percentFailures = percentFailures;
    }
}
