package vn.com.fpt.foxpay.services.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private final MicroServiceApi microServiceApi = new MicroServiceApi();

    public MicroServiceApi getMicroServiceApi() {
        return microServiceApi;
    }

    public static class MicroServiceApi {
        private boolean debugging = false;

        public boolean isDebugging() {
            return debugging;
        }

        public void setDebugging(boolean debugging) {
            this.debugging = debugging;
        }
    }
}
