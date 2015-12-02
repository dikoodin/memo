package com.memo.interpolator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageInterpolator {

    private static Logger log = LoggerFactory.getLogger(MessageInterpolator.class);

    private final Map<Locale, ResourceBundle> cache = new HashMap<Locale, ResourceBundle>();

    protected MessageInterpolator() {
        super();
    }

    public String interpolate(String key) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = cache.get(locale);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("messages", locale);
            cache.put(locale, bundle);
        }
        String value = key;
        try {
            value = bundle.getString(key);
        } catch (MissingResourceException e) {
            log.warn("Missing message value for key {}.", key);
        }
        return value;
    }

    public static MessageInterpolator instance() {
        return MessageInterpolatorHelper.INSTANCE;
    }

    private static final class MessageInterpolatorHelper {
        private static MessageInterpolator INSTANCE = new MessageInterpolator();
    }

    protected static void setInstance(MessageInterpolator interpolator) {
        MessageInterpolatorHelper.INSTANCE = interpolator;
    }

}
