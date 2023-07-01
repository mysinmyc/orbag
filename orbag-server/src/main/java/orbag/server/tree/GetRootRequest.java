package orbag.server.tree;

import orbag.reference.ConfigurationItemReference;

public class GetRootRequest {

    ConfigurationItemReference ci;

    public ConfigurationItemReference getCi() {
        return ci;
    }

    public void setCi(ConfigurationItemReference ci) {
        this.ci = ci;
    }
}
