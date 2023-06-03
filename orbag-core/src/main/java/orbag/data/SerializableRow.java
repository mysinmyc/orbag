package orbag.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SerializableRow {

    HashMap<String,Object> fields;

    public HashMap<String, Object> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, Object> fields) {
        this.fields = fields;
    }

    HashSet<String> tags;

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }
}
