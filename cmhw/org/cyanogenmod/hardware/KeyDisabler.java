
package org.cyanogenmod.hardware;

import org.cyanogenmod.hardware.util.FileUtils;

public class KeyDisabler {
    private static final String CONTROL_FILE = "/sys/devices/platform/s3c2440-i2c.2/i2c-2/2-004a/buttons_enabled";

    public static boolean isSupported() {
        return true;
    }

    public static boolean isActive() {
        return FileUtils.readOneLine(CONTROL_FILE).equals("0");
    }

    public static boolean setActive(boolean state) {
        return FileUtils.writeLine(CONTROL_FILE, (state ? "0" : "1"));
    }
}
