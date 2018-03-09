package com.sf.inv.spi;

/**
 * highlevel cache
 * K:kh
 * V:vh
 * lowlevel cache
 * K:vh
 * V:vl
 *
 * @param <L> lowlevel
 * @param <H> highlevel
 */
public interface ITwoLevelCache<L,H> extends ICache<L> {
    void putHL(String key, H data);
    void putHLAndLLValue(String key, H l2value, L l1value);
    H getHL(String key);
    L getLLByHLKey(String hlkey);
    boolean keyHLExist(String hlKey);
    L removeByHLKey(String hlkey);
}
