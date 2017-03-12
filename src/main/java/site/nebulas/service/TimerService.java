package site.nebulas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import site.nebulas.util.GugujiUtil;

@Service 
public class TimerService {
	 private Logger log = LoggerFactory.getLogger(TimerService.class);

	 @Scheduled(cron="0 0 7 * * ?")   //每天7点执行一次
     public void printWeather(){
         log.info("进入测试");
         GugujiUtil.scripPrint(GugujiUtil.getWeather());
     }
    @Scheduled(cron="0 */1 * * * ?")
    public void test(){
        log.info("进入测试");
    }
}
