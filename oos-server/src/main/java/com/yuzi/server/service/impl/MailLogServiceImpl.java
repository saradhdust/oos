package com.yuzi.server.service.impl;

import com.yuzi.server.pojo.MailLog;
import com.yuzi.server.mapper.MailLogMapper;
import com.yuzi.server.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
