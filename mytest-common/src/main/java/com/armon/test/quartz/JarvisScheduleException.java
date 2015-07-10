package com.armon.test.quartz;

public class JarvisScheduleException extends Exception {
	private static final long serialVersionUID = -509272052478195320L;

	public JarvisScheduleException() {
        super();
    }

    public JarvisScheduleException(String msg) {
        super(msg);
    }

    public JarvisScheduleException(Throwable cause) {
        super(cause);
    }

    public JarvisScheduleException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
