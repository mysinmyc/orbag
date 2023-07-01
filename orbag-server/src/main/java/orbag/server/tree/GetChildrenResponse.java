package orbag.server.tree;

import java.util.List;

public class GetChildrenResponse {

    List<TreeElement> children;

    public List<TreeElement> getChildren() {
        return children;
    }

    public void setChildren(List<TreeElement> children) {
        this.children = children;
    }
}
