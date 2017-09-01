package com.atjl.util.config;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.CovertUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.config.util.ConfigPropCommonUtil;
import com.atjl.util.file.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ConfigModelUtil {
    private ConfigModelUtil() {
        super();
    }

    private static final Logger logger = LoggerFactory.getLogger(ConfigModelUtil.class);

    /**
     *
     * @param filePath
     * @param type
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T generateConfigModel(String filePath, int type, Class<T> clz) {
        return generateConfigModel(filePath, type, clz, null, null);
    }

    /**
     * auto generateConfigModel config object
     *
     * @param clz config model class
     * @return generated config model object
     */
    public static <T> T generateConfigModel(String filePath, int type, Class<T> clz, String prefixKey, String[] modelExecludeFields) {
        T res = null;
        try {
            res = clz.newInstance();
            if (!ConfigPropUtil.init(filePath, type)) {
                logger.error("init prop fail,prop path {},{}", filePath, type);
                return res;
            }
            String fileName = filePath;
            if (type == ConfigPropConstant.TP_FILE) {
                fileName = PathUtil.getFileName(filePath);
            }

            List<Field> fields = ReflectUtil.getFields(clz, ReflectUtil.GetClzOpt.ALL, modelExecludeFields, null);
            if (CollectionUtil.isEmpty(fields)) {
                logger.warn("generateConfigModel,field empty");
                return res;
            }
            String prefix = ConfigPropUtil.get(fileName, prefixKey);


            List<String> keys = ReflectUtil.filed2string(fields);
            if (!StringCheckUtil.isEmpty(prefix)) {
                if (!CollectionUtil.isEmpty(keys)) {
                    List<String> keyWithPrefix = new ArrayList<>();
                    for (String key : keys) {
                        keyWithPrefix.add(ConfigPropCommonUtil.filterPrefix(prefix, key));
                    }
                    keys = keyWithPrefix;
                }
            }
            Map<String, String> vals = ConfigPropUtil.getBatch(fileName, keys);

            for (Field field : fields) {
                Object val = null;
                Class<?> fieldType = field.getType();
                field.setAccessible(true);
                Object defaultVal = field.get(res);
                String propStrVal = vals.get(ConfigPropCommonUtil.filterPrefix(prefix, field.getName()));
                vals.get(field.getName());
                if (propStrVal == null) {
                    if (defaultVal != null) {
                        val = defaultVal;
                    }
                } else {
                    val = CovertUtil.covert(propStrVal, fieldType);
                }
                if (val != null) {
                    ReflectUtil.setter(res, field.getName(), fieldType, val);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("generateConfigModel {}", e);
        }
        return res;
    }
}
