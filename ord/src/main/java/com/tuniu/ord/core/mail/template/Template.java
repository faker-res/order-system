package com.tuniu.ord.core.mail.template;

import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;

import com.tuniu.ord.core.mail.template.config.ITemplateConfig;
import com.tuniu.ord.core.mail.template.context.IContext;
import com.tuniu.ord.core.mail.template.handler.IPlacehoderHandler;

public class Template implements ITemplate {

    private String title;

    private String content;

    private ITemplateConfig templateConfig;

    private final PropertyPlaceholderHelper helper;

    public Template() {
        helper = new PropertyPlaceholderHelper(PLACEHODER_ITEM_PREFIX, PLACEHODER_ITEM_SUFFIX);
    }

    @Override
    public ITemplateConfig getConfig() {
        return null;
    }

    @Override
    public String render(ProcessTarget target, IContext context) {
        String result = null;
        if (target == ProcessTarget.TITLE) {
            result = helper.replacePlaceholders(this.title, new TemplatePlacceholderResolver(context));
        } else if (target == ProcessTarget.CONTENT) {
            result = helper.replacePlaceholders(this.content, new TemplatePlacceholderResolver(context));
        }

        return result;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @param templateConfig
     *            the templateConfig to set
     */
    public void setTemplateConfig(ITemplateConfig templateConfig) {
        this.templateConfig = templateConfig;
    }

    class TemplatePlacceholderResolver implements PlaceholderResolver {

        private final IContext context;

        public TemplatePlacceholderResolver(IContext context) {
            this.context = context;
        }

        @Override
        public String resolvePlaceholder(String placeholderName) {
            IPlacehoderHandler handler = Template.this.templateConfig.getHandler(placeholderName);
            if (null != handler) {
                return handler.handle(placeholderName, context);
            }
            return null;
        }

    }
}
