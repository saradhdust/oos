package com.yuzi.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.xml.validation.TypeInfoProvider;
import java.time.LocalDate;

/**
 * 消息
 *
 * @author 星涯
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {
    private String from;
    private String to;
    private String fromNickName;
    private String content;
    private LocalDate date;
}
