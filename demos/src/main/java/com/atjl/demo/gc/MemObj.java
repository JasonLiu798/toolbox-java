package com.atjl.demo.gc;

/**
 *
 */
public class MemObj {
	
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * 优先分配在eden
	 * VM参数：
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * eden=8m,s1=s2=1m
	 * 分配3个2MB大小和1个4MB大小的对象
	 * <p>
	 * [GC (Allocation Failure) [PSYoungGen: 7859K->1000K(9216K)] 7859K->5257K(19456K), 0.0219575 secs] [Times: user=0.03 sys=0.00, real=0.02 secs]
	 * Heap
	 * PSYoungGen      total 9216K, used 7383K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	 * eden space 8192K, 77% used [0x00000000ff600000,0x00000000ffc3bcd0,0x00000000ffe00000)
	 * from space 1024K, 97% used [0x00000000ffe00000,0x00000000ffefa020,0x00000000fff00000)
	 * to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
	 * ParOldGen       total 10240K, used 4257K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	 * object space 10240K, 41% used [0x00000000fec00000,0x00000000ff028768,0x00000000ff600000)
	 * Metaspace       used 3310K, capacity 4496K, committed 4864K, reserved 1056768K
	 * class space    used 359K, capacity 388K, committed 512K, reserved 1048576K
	 */
	public static void testAllocation() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
	}
	
	/**
	 * 大对象直接进入老年代
	 * VM参数：
	 * -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
	 */
	public static void testPretenureSizeThreshold() {
		byte[] allocation;
		allocation = new byte[4 * _1MB]; //直接分配在老年代中
	}
	
	/**
	 * 长期存活的对象将进入老年代
	 * -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 * <p>
	 * [GC (Allocation Failure) [DefNew
	 * Desired survivor size 524288 bytes, new threshold 1 (max 1)
	 * - age   1:    1048576 bytes,    1048576 total
	 * : 8115K->1024K(9216K), 0.0043708 secs] 8115K->5448K(19456K), 0.0044067 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
	 * [GC (Allocation Failure) [DefNew
	 * Desired survivor size 524288 bytes, new threshold 1 (max 1)
	 * : 5120K->0K(9216K), 0.0014256 secs] 9544K->5448K(19456K), 0.0014515 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
	 * Heap
	 * def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	 * eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
	 * from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
	 * to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
	 * tenured generation   total 10240K, used 5448K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	 * the space 10240K,  53% used [0x00000000ff600000, 0x00000000ffb522f0, 0x00000000ffb52400, 0x0000000100000000)
	 * Metaspace       used 3302K, capacity 4496K, committed 4864K, reserved 1056768K
	 * class space    used 359K, capacity 388K, committed 512K, reserved 1048576K
	 * <p>
	 * <p>
	 * -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
	 * <p>
	 * [GC (Allocation Failure) [DefNew
	 * Desired survivor size 524288 bytes, new threshold 1 (max 15)
	 * - age   1:    1048576 bytes,    1048576 total
	 * : 8115K->1024K(9216K), 0.0038761 secs] 8115K->5448K(19456K), 0.0039107 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
	 * [GC (Allocation Failure) [DefNew
	 * Desired survivor size 524288 bytes, new threshold 15 (max 15)
	 * : 5120K->0K(9216K), 0.0012390 secs] 9544K->5448K(19456K), 0.0012547 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
	 * Heap
	 * def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	 * eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
	 * from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
	 * to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
	 * tenured generation   total 10240K, used 5448K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	 * the space 10240K,  53% used [0x00000000ff600000, 0x00000000ffb52368, 0x00000000ffb52400, 0x0000000100000000)
	 * Metaspace       used 3302K, capacity 4496K, committed 4864K, reserved 1056768K
	 * class space    used 359K, capacity 388K, committed 512K, reserved 1048576K
	 */
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];    // 什么时候进入老年代取决于XX:MaxTenuringThreshold设置
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}
	
	
	/**
	 * 动态对象年龄判定，Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代
	 * VM参数：
	 * -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
	 * <p>
	 * [GC (Allocation Failure) [DefNew
	 * Desired survivor size 524288 bytes, new threshold 1 (max 15)
	 * - age   1:    1048576 bytes,    1048576 total
	 * : 4275K->1024K(9216K), 0.0022397 secs] 4275K->1608K(19456K), 0.0022729 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
	 * [GC (Allocation Failure) [DefNew
	 * Desired survivor size 524288 bytes, new threshold 15 (max 15)
	 * : 5120K->0K(9216K), 0.0028089 secs] 5704K->5704K(19456K), 0.0028262 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
	 * [GC (Allocation Failure) [DefNew
	 * Desired survivor size 524288 bytes, new threshold 15 (max 15)
	 * : 4096K->0K(9216K), 0.0002323 secs] 9801K->5704K(19456K), 0.0002438 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
	 * Heap
	 * def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	 * eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
	 * from space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
	 * to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
	 * tenured generation   total 10240K, used 5704K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	 * the space 10240K,  55% used [0x00000000ff600000, 0x00000000ffb923f8, 0x00000000ffb92400, 0x0000000100000000)
	 * Metaspace       used 3307K, capacity 4496K, committed 4864K, reserved 1056768K
	 * class space    used 359K, capacity 388K, committed 512K, reserved 1048576K
	 */
	public static void testTenuringThreshold2() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[_1MB / 4];    // allocation1+allocation2大于survivo空间一半
		allocation2 = new byte[_1MB / 4];
		allocation3 = new byte[4 * _1MB];
		allocation4 = new byte[4 * _1MB];
		allocation4 = null;
		allocation4 = new byte[4 * _1MB];
	}
	
	
	/**
	 * 空间分配担保
	 * VM参数：
	 * -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:HandlePromotionFailure
	 *
	 * JDK 6 Update 24之后，这个测试结果会有差异，Han-dlePromotionFailure参数不会再影响到虚拟机的空间分配担保策略
	 */
	public static void testHandlePromotion() {
		byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation1 = null;
		allocation4 = new byte[2 * _1MB];
		allocation5 = new byte[2 * _1MB];
		allocation6 = new byte[2 * _1MB];
		allocation4 = null;
		allocation5 = null;
		allocation6 = null;
		allocation7 = new byte[2 * _1MB];
	}
	
	
	public static void main(String[] args) {
//		testAllocation();//eden分配
//		testPretenureSizeThreshold();//old分配
//		testTenuringThreshold();//old
//		testTenuringThreshold2();//
		testHandlePromotion();
	}
}

