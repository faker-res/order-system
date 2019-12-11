package com.tuniu.ord.core.mail.template;

import com.tuniu.ord.core.mail.template.config.ITemplateConfig;
import com.tuniu.ord.core.mail.template.context.IContext;

/**
 * @author fangzhongwei
 * 
 */
public interface ITemplate {

    String PLACEHODER_ITEM_PREFIX = "##{";

    String PLACEHODER_ITEM_SUFFIX = "}";

    ITemplateConfig getConfig();

    String render(ProcessTarget target, IContext context);

    enum ProcessTarget {
        TITLE, CONTENT
    }
}
