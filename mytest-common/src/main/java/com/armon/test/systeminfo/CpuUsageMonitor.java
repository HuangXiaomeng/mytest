package com.armon.test.systeminfo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.google.common.io.Files;

public class CpuUsageMonitor {

  private static volatile double cpuUsage = 0;

  private CpuUsageMonitor() {
  }

  static {
    Thread t = new CpuUsageThread();
    t.start();
  }

  public static double getCpuUsage() {
    return cpuUsage;
  }

  private static class CpuUsageThread extends Thread {

    private long idleCpuTime1 = 0;
    private long totalCpuTime1 = 0;
    private long idleCpuTime2 = 0;
    private long totalCpuTime2 = 0;
    private static final int SLEEP_SECONDS = 1;

    private void collect() {
      idleCpuTime1 = idleCpuTime2;
      totalCpuTime1 = totalCpuTime2;

      try {
        String line = Files.readFirstLine(new File("/proc/stat"), StandardCharsets.UTF_8);
        String[] tokens = line.split("\\s+");
        idleCpuTime2 = Long.parseLong(tokens[4]);
        totalCpuTime2 = 0;
        for (int i = 1, len = tokens.length; i < len; i++) {
          totalCpuTime2 += Long.parseLong(tokens[i]);
        }

        cpuUsage = 1 - (double) (idleCpuTime2 - idleCpuTime1) / (totalCpuTime2 - totalCpuTime1);
      } catch (IOException e) {
      }
    }

    @Override
    public void run() {
      while (true) {
        collect();
        try {
          Thread.sleep(SLEEP_SECONDS * 1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

  }

}
