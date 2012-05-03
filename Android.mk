ifeq ($(TARGET_BOOTLOADER_BOARD_NAME),s5pc110)
    include $(all-subdir-makefiles)
endif

ifneq ($(findstring $(strip $(TARGET_DEVICE)),p1 p1l p1n),)

include $(CLEAR_VARS)

ifneq ($(findstring $(strip $(TARGET_DEVICE)),p1l p1n),)
  LOCAL_MACHINE_START := p1
else
  LOCAL_MACHINE_START := $(TARGET_DEVICE)
endif

# ----
# generate init.<device>.rc
# ----

LOCAL_INIT_FILE := $(TARGET_ROOT_OUT)/init.$(LOCAL_MACHINE_START).rc

$(LOCAL_INIT_FILE): $(TARGET_INITRC_FILES) $(foreach file,$(TARGET_INITRC_IMPORT), $(TARGET_ROOT_OUT)/$(file))
	$(hide) echo > $@
	$(hide) $(foreach file,$(TARGET_INITRC_IMPORT),echo "import $(file)" >> $@;)
	$(hide) echo >> $@
	$(hide) cat $(TARGET_INITRC_FILES) >> $@

ALL_GENERATED_SOURCES += $(LOCAL_INIT_FILE)

# ----
# generate ueventd.<device>.rc
# ----

LOCAL_UEVENTD_FILE := $(TARGET_ROOT_OUT)/ueventd.$(LOCAL_MACHINE_START).rc

$(LOCAL_UEVENTD_FILE): $(TARGET_UEVENTDRC_FILES)
	$(hide) cat $^ > $@

ALL_GENERATED_SOURCES += $(LOCAL_UEVENTD_FILE)

endif
