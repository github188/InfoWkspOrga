package com.sgu.infowksporga.jfx.gen.tools;

import java.io.File;

public final class FlagsI18nPropertiesGen {

  private FlagsI18nPropertiesGen() {
  }

  public static void main(String[] args) {
    File folder = new File("G:\\Projects\\400-InfoWkspOrga\\10-Application\\Application\\InfoWkspOrga-APP\\src\\main\\resources\\icons\\flags");
    String flagsRelativeDir = "/icons/flags/";

    File[] flags = folder.listFiles();
    for (int i = 0; i < flags.length; i++) {
      String country = "";
      String fileName = flags[i].getName();

    }

  }

}
