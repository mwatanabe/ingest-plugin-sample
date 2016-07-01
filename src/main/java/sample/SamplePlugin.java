package sample;

import org.elasticsearch.node.NodeModule;
import org.elasticsearch.plugins.Plugin;

public class SamplePlugin extends Plugin {

    public void onModule(final NodeModule nodeModule) {
        nodeModule.registerProcessor(TypoReplaceProcessor.TYPE, (registry) -> new TypoReplaceProcessor.Factory());
    }
}
