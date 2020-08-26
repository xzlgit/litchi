package cn.litchi.upload.scheduled;

import cn.litchi.model.mapper.LzNodeDataDao;
import cn.litchi.model.model.DBLzNodeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Random;


@Component
public class FakeDataScheduled {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LzNodeDataDao dataDao;

    @Scheduled(fixedRate = 60000)
    public void testTasks() {
        Random random = new Random();
        DBLzNodeData data = DBLzNodeData.builder()
                .nodeId(Long.valueOf(random.nextInt(3)))
                .limitId(1L)
                .temp(random.nextDouble() * 10)
                .tlx(random.nextDouble() * 10)
                .water(random.nextDouble() * 10)
                .windDirection(Byte.valueOf(String.valueOf(random.nextInt(4))))
                .co2(random.nextDouble() * 10)
                .humi(random.nextDouble() * 10)
                .lx(random.nextDouble() * 10)
                .rainfall(random.nextDouble() * 10)
                .volt(random.nextDouble() * 10)
                .windStrength(random.nextDouble() * 10)
                .time(Instant.now())
                .build();
        dataDao.insert(data);
        logger.info("[insert fake nodeData] :" + data.toString());
    }
}
