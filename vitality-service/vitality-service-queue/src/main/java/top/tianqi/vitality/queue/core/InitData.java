package top.tianqi.vitality.queue.core;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.tianqi.vitality.tools.queue.DelayQueueManager;
import top.tianqi.vitality.tools.utils.DateUtils;
import top.tianqi.vitality.tools.utils.JsonUtil;
import top.tianqi.vitality.queue.mapper.DelayTaskMapper;
import top.tianqi.vitality.queue.entity.DelayTask;
import top.tianqi.vitality.queue.enumeration.TaskClassEnum;
import top.tianqi.vitality.queue.task.OrderTask;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 在服务启动时，加载延时队列任务
 * @Author wkh
 * @Date 2020/8/7 9:12
 */
@Component
public class InitData implements ApplicationRunner {

    @Resource
    private DelayTaskMapper delayTaskMapper;

    @Resource
    private DelayQueueManager delayQueueManager;

    @Override
    public void run(ApplicationArguments args) {
        // 根据任务类的status状态
        List<DelayTask> delayTasks = delayTaskMapper.selectList(new QueryWrapper<>());
        for (DelayTask delayTask : delayTasks) {
            if (delayTask.getClassId() == TaskClassEnum.ORDER_TASK.getCode()) {
                Date triggerTime = delayTask.getTriggerTime();
                System.out.println(DateUtils.dateFormat(triggerTime));
                Long second = DateUtils.getSecond(triggerTime);
                Long dayBeginSecond = DateUtils.getSecond(new Date());
                top.tianqi.vitality.tools.queue.DelayTask<OrderTask> task = JsonUtil.jsonToObject(delayTask.getTask(), new TypeReference<top.tianqi.vitality.tools.queue.DelayTask<OrderTask>>() {
                });
                task.setStartTime(second - dayBeginSecond);
                delayQueueManager.put(task);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DelayQueueManager delayQueueManager = new DelayQueueManager();
        Date str = DateUtils.StringToDate("2020-08-7 14:33:30");

        // regityDate -> 3
        // addDay(3).getTime

        Date dayBegin = DateUtils.getDayBegin();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(str);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());
        long result = DateUtils.getSecond(str) - DateUtils.getSecond(new Date());
        System.out.println(result);

//        OrderTask order = new OrderTask();
//        order.setId(1L);
//        order.setStatus(0);
//        DelayTask<OrderTask> task = new DelayTask<>(result, order, 1L);
//        String json = JsonUtil.toJsonString(task);
//        System.out.println(json);
//        delayQueueManager.put(task);



    }
}
