package orbag.server.impact;

import orbag.reference.ConfigurationItemReference;
import orbag.server.graph.SerializablePath;

public class GenerateReportRequest {

    ConfigurationItemReference startingCi;

    SerializablePath path;

    int maxSteps;
}
