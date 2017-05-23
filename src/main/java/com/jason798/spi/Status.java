package com.jason798.spi;

/**
 * common dev enum,life cycle
 * STOPED->INIT->RUNNING->STOPING->STOPED...
 */
public enum Status {
    INIT,RUNNING,STOPING,STOPED
}
