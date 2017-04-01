package com.jason798.timing;

import javax.annotation.Resource;
import java.util.List;

/**
 * common functions
 */
//@Component
public class TimingCommonHelper {

    @Resource
    private GenTaskMapper genTaskMapper;

    @Resource
    private GenTaskManagerMapper genTaskManagerMapper;

    /**
     * db registe
     */
    public String registe() {
        Long ipLong = IPUtil.getOneRandomIpLong();
        String pid = SystemUtil.getPid();

        String id = String.format("ip-%s,pid-%s", ipLong, pid);
        //this.managerId = id;

        GenTaskManager m = new GenTaskManager();
        m.setName(id);
        m.setLastUpdateTm(DateUtil.getNowTS());
        genTaskManagerMapper.insertSelective(m);
        return id;
    }


    /**
     * task exist
     *
     * @param key
     * @return
     */
    public boolean taskExist(String key) {
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andValidEqualTo(CommonConstant.VALID).andTkeyEqualTo(key);
        if (genTaskMapper.countByExample(param) > 0) {
            return true;
        }
        return false;
    }


    /**
     * @param key
     * @return
     */
    public GenTask getTaskByKey(String key) {
        if (StringCheckUtil.isEmpty(key)) {
            return null;
        }
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andValidEqualTo(CommonConstant.VALID).andTkeyEqualTo(key);
        List<GenTask> l = genTaskMapper.selectByExample(param);
        if (!CollectionUtil.isEmpty(l)) {
            return l.get(0);
        }
        return null;
    }


    /**
     * generate random tie
     *
     * @return
     */
    public String genRandomTid() {
        return RandomUtil.generateMD5();
    }

}
