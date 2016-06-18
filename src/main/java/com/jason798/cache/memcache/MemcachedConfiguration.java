package com.jason798.cache.memcache;

import net.rubyeye.xmemcached.CommandFactory;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import java.io.Serializable;

/**
 * memcache config
 */
public final class MemcachedConfiguration implements Serializable {

    private static final long serialVersionUID = -4264156936217323855L;
    /**
	 * 缓存地址
	 */
	private String addr;
	/**
	 * 端口
	 */
	private String port;
	/**
	 * 连接池大小
	 */
	private int poolSize = 10;
	/**
	 * 有效时长
	 */
	private int effectiveTime = 0;
	/**
	 * 失败模式
	 */
	private Boolean failureMode = true;
	/**
	 * 处理模式
	 */
	private CommandFactory commandFactory = new BinaryCommandFactory();

	public MemcachedConfiguration() {
		super();
	}

	public MemcachedConfiguration(String addr, String port) {
		super();
		this.addr = addr;
		this.port = port;
	}

	public MemcachedConfiguration(String addr, String port, int poolSize, int effectiveTime) {
		super();
		this.addr = addr;
		this.port = port;
		this.poolSize = poolSize;
		this.effectiveTime = effectiveTime;
	}

	public MemcachedConfiguration(String addr, String port, int effectiveTime) {
		super();
		this.addr = addr;
		this.port = port;
		this.effectiveTime = effectiveTime;
	}

	/**
	 * 获取 addr
	 * 
	 * @return {@link String} 返回 addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * 设置 addr
	 * 
	 * @param addr
	 *            {@link String} 对addr进行赋值
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * 获取 port
	 * 
	 * @return {@link String} 返回 port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * 设置 port
	 * 
	 * @param port
	 *            {@link String} 对port进行赋值
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * 获取 poolSize
	 * 
	 * @return {@link int} 返回 poolSize
	 */
	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * 设置 poolSize
	 * 
	 * @param poolSize
	 *            {@link int} 对poolSize进行赋值
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * 获取 effectiveTime
	 * 
	 * @return {@link int} 返回 effectiveTime
	 */
	public int getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * 设置 effectiveTime
	 * 
	 * @param effectiveTime
	 *            {@link int} 对effectiveTime进行赋值
	 */
	public void setEffectiveTime(int effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 * 获取 failureMode
	 * 
	 * @return {@link Boolean} 返回 failureMode
	 */
	public Boolean getFailureMode() {
		return failureMode;
	}

	/**
	 * 设置 failureMode
	 * 
	 * @param failureMode
	 *            {@link Boolean} 对failureMode进行赋值
	 */
	public void setFailureMode(Boolean failureMode) {
		this.failureMode = failureMode;
	}

	/**
	 * 获取 commandFactory
	 * 
	 * @return {@link CommandFactory} 返回 commandFactory
	 */
	public CommandFactory getCommandFactory() {
		return commandFactory;
	}

	/**
	 * 设置 commandFactory
	 * 
	 * @param commandFactory
	 *            {@link CommandFactory} 对commandFactory进行赋值
	 */
	public void setCommandFactory(CommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}
}
