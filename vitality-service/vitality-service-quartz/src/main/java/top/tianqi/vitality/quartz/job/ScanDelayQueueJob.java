package top.tianqi.vitality.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定期扫描数据库中的延时任务
 * 每5秒钟扫描未执行的延时队列任务
 * @Author wkh
 * @Date 2020/8/6 15:29
 */
public class ScanDelayQueueJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
