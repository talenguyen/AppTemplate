package vn.tiki.appid.developer_settings;

class NoOpLeakCanaryProxy implements LeakCanaryProxy {
    @Override
    public void init() {
        // no-op.
    }
}