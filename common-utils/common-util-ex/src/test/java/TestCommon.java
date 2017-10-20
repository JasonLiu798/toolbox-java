import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

public class TestCommon {

    @Test
    public void test() {
        MemoryUsage usage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        System.out.println("Max: " + usage.getMax() / 1000 / 1000);
        System.out.println("Init: " + usage.getInit() / 1000 / 1000);
        System.out.println("Committed: " + usage.getCommitted() / 1000 / 1000);
        System.out.println("Used: " + usage.getUsed() / 1000 / 1000);
    }
}
