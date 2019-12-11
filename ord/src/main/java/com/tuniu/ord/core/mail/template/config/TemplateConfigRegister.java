package com.tuniu.ord.core.mail.template.config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fangzhongwei
 * 
 */
public final class TemplateConfigRegister {

    private static Map<String, Class<? extends ITemplateConfig>> templateConfigMap = new LinkedHashMap<String, Class<? extends ITemplateConfig>>();

    /**
     * 注册成功邮件模板key
     */
    private static final String EMAIL_TEMPLATE_REGISTER = "email_template_register";

    private TemplateConfigRegister() {
    }

    static {
        templateConfigMap.put(EMAIL_TEMPLATE_REGISTER, EmailRegisterTemplateConfig.class);
    }

    public static Class<? extends ITemplateConfig> getTemplateConfig(String key) {
        return templateConfigMap.get(key);
    }
}
