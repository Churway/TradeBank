package com.churway.entity;

import com.churway.service.ItemService;
import com.churway.utils.ObtainBeanUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/11/18
 * @since 1.0.0
 */
public class ReadyToActionJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Long id = (Long) jobExecutionContext.getMergedJobDataMap().get("id");
        ItemService itemService = ObtainBeanUtils.getBean(ItemService.class);
        itemService.startAction(id);
        System.out.println("job-------------------------------------->");
    }
}
