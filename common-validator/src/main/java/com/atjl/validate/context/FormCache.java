package com.atjl.validate.context;


import com.atjl.common.api.ICache;
import com.atjl.validate.api.ValidateConstant;
import com.atjl.validate.api.ValidateForm;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class FormCache implements ICache<ValidateForm> {
    private static final Logger logger = LoggerFactory.getLogger(FormCache.class);
    private static volatile boolean init = false;
    private CacheManager manager;
    private Cache cache;

    public FormCache() {
        init();
    }

    @Override
    public void put(String key, ValidateForm data) {
        if (this.cache != null) {
            Element e = new Element(key, data);
            this.cache.put(e);
        }
    }

    public ValidateForm get(String classWholeName) {
        if (this.cache != null) {
            Element e = this.cache.get(classWholeName);
            return e == null ? null : (ValidateForm) e.getObjectValue();
        } else {
            return null;
        }
    }

    @Override
    public boolean contain(String key) {
        return this.cache.isKeyInCache(key);
    }

    @Override
    public ValidateForm remove(String key) {
        ValidateForm res = get(key);
        this.cache.remove(key);
        return res;
    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public synchronized void init() {
        if (!init) {
            URL url = getClass().getResource("/ehcache/ehcache-validate.xml");
            this.manager = CacheManager.create(url);
            this.cache = manager.getCache(ValidateConstant.CACHE_NAME);
            if (this.cache != null) {
                logger.info("init form cache success");
            } else {
                logger.info("init form cache fail");
            }
            this.init = true;
        }
    }

    @Override
    public void destroy() {
        manager.shutdown();
    }
}
