package com.atjl.util.common;

/**
 * 分页辅助类
 */
public class PageUtil {
    private PageUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取 页数
     *
     * @param totalCount
     * @param pageSize
     * @return
     */
    public static int getPageCount(int totalCount, int pageSize) {
        if (totalCount <= 0) {
            return 0;
        }
        if (pageSize <= 0) {
            return 0;
        }
//        double pgeCntD = Math.ceil((double) actualCount / (double) pageSize);
//        return Math.toIntExact(Math.round(pgeCntD));
        return (totalCount + pageSize - 1) / pageSize;
    }


    public static long getPageCountLong(long totalCount, long pageSize) {
//        double pgeCntD = Math.ceil((double) actualCount / (double) pageSize);
        return (totalCount + pageSize - 1) / pageSize;
    }

    /**
     * 根据 当前对象数量， 获取第几页
     * @param itemNum
     * @param pageSize
     * @return
     */
    public static long getPageIdxLong(long itemNum, long pageSize) {
        return itemNum / pageSize + 1;
    }
}
