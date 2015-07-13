package com.armon.test.systeminfo;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * @author pengcqu
 *
 */
public class SystemInfo implements Comparable {

  private String id;
  private int age;

  public SystemInfo(String id, int age) {
    this.id = id;
    this.age = age;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int compareTo(Object o) {
    return this.age - ((SystemInfo) o).getAge();
  }

  /**
   * 测试方法
   *
   * @throws SigarException
   * @throws InterruptedException
   */
  public static void main(String[] args) throws SigarException,
      InterruptedException {
    // OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory
    // .getOperatingSystemMXBean();
    // System.out.println("TotalPhysicalMemorySize: " +
    // bean.getTotalPhysicalMemorySize());
    // System.out.println("TotalSwapSpaceSize: " +
    // bean.getTotalSwapSpaceSize());
    // System.out.println("FreePhysicalMemorySize: " +
    // bean.getFreePhysicalMemorySize());
    // System.out.println("FreeSwapSpaceSize: " + bean.getFreeSwapSpaceSize());
    // System.out.println("CommittedVirtualMemorySize: " +
    // bean.getCommittedVirtualMemorySize());
    // System.out.println("load: " + bean.getSystemLoadAverage());

     Sigar sigar = new Sigar();
     CpuPerc perc = sigar.getCpuPerc();
     System.out.println("整体cpu的占用情况:");
     System.out.println("system idle: " + perc.getIdle());
     //获取当前cpu的空闲率
     System.out.println("system usage: " + perc.getCombined());//获取当前cpu的占用率

     // 物理内存信息
     Mem mem = sigar.getMem();
     // 内存总量
     System.out.println("Total = " + mem.getTotal() / 1024L / 1024 + "M av");
     // 当前内存使用量
     System.out.println("Used = " + mem.getUsed() / 1024L / 1024 + "M used");
     // 当前内存剩余量
     System.out.println("Free = " + mem.getFree() / 1024L / 1024 + "M free");
     // 当前内存使用率
     System.out.println("内存使用率=" + mem.getUsedPercent());
     // 当前内存空闲率
     System.out.println("内存空闲率=" + mem.getFreePercent());

    /*
    while (true) {
      Sigar sigar = new Sigar();
      CpuPerc perc = sigar.getCpuPerc();
      System.out.println("sigar cpu usage: " + perc.getCombined());

      System.out.println("cpu monitor usage: " + CpuUsageMonitor.getCpuUsage());

      Thread.sleep(1000);
    }
    */
  }

}