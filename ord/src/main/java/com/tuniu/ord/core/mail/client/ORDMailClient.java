/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.mail.client;

import java.util.ArrayList;
import java.util.List;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.TspUtil;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.vo.EDMInputVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhairongping
 *
 */
public class ORDMailClient {
	private static Log4jLogger logger = LogFactory.getLogger(ORDMailClient.class);


    /**
     * 
     * @param title
     *            邮件标题
     * @param content
     *            邮件内容
     */
    public static void sendMail(String title, String content) {
      
        EDMInputVo input = new EDMInputVo();
        List<String> recipientEmails = new ArrayList<String>();
        List<String> ccEmails = new ArrayList<String>();
        recipientEmails.add("zhairongping@tuniu.com");
        recipientEmails.add("meijie@tuniu.com");
        recipientEmails.add("shiheng@tuniu.com");
        recipientEmails.add("liupeng@tuniu.com");
        ccEmails.add("lingchuanzhi@tuniu.com");
        ccEmails.add("zhangerlin@tuniu.com");
        ccEmails.add("haoyabin@tuniu.com");
        input.setRecipientEmails(recipientEmails);
        input.setCcEmails(ccEmails);
        input.setSubject(title);
        input.setMsgText(content);
        sendMail(input);
    }

    /**
     * 发送给结算平台系统邮件(封装邮件消息实体)
     * 
     * @param eDMInputVo
     */
    public static void sendMailToSCS(EDMInputVo eDMInputVo) {
        sendMailToSCS(eDMInputVo.getSubject(), eDMInputVo.getMsgText());
    }

    /**
     * 发送给结算平台系统邮件
     * 
     * @param title
     * @param content
     */
    public static void sendMailToSCS(String title, String content) {
        EDMInputVo input = new EDMInputVo();
        List<String> recipientEmails = new ArrayList<String>();
        List<String> ccEmails = new ArrayList<String>();
        recipientEmails.add("zhairongping@tuniu.com");
        recipientEmails.add("fangyong@tuniu.com");
        ccEmails.add("lingchuanzhi@tuniu.com");
        input.setRecipientEmails(recipientEmails);
        input.setCcEmails(ccEmails);
        input.setSubject(title);
        input.setMsgText(content);
        sendMail(input);
    }

    /**
     * 向底层发送邮件
     * 
     * @param eDMInputVo
     * @return
     */
    public static ResponseVo sendMail(EDMInputVo eDMInputVo) {
        ResponseVo responseVo = new ResponseVo();
        String param = JsonUtil.toString(eDMInputVo);
        logger.debug(Log4jLogger.SYSTEM_LOG,
                "ORDMailClient-sendMail-input=======" + SystemConstants.EMAIL_SEND_INTERNAL_EMAIL + ";" + param);
        String result = TspUtil.getTspResp(SystemConstants.EMAIL_SEND_INTERNAL_EMAIL, param, SystemConstants.HTTP_POST);
        logger.debug(Log4jLogger.SYSTEM_LOG, "ORDMailClient-sendMail-output======" + result);
        if (null == result || "".equals(result)) {
            logger.debug(Log4jLogger.SYSTEM_LOG, "ORDMailClient-sendMail>>>>>>>>>>>>>>>>>发送邮件异常");
            responseVo.setSuccess(false);
            responseVo.setMsg("发送邮件接口异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 发邮件给对应系统的负责人
     * 
     * @param system
     */
    public static void sendMailToSystem(ISystem system) {
        EDMInputVo input = new EDMInputVo();
        input.setSubject(system.getSubject());
        input.setMsgText(system.getMsgText());
        input.setRecipientEmails(system.getRecipientEmails());
        input.setCcEmails(system.getCcEmails());
        sendMail(input);
    }

}
