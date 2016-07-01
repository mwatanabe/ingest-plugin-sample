package sample;

import java.util.Map;

import org.elasticsearch.common.Strings;
import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.AbstractProcessorFactory;
import org.elasticsearch.ingest.ConfigurationUtils;
import org.elasticsearch.ingest.IngestDocument;

public class TypoReplaceProcessor extends AbstractProcessor {
    public static final String TYPE = "typo";

    private final String field;
    private final String target;
    private final String replacement;

    public TypoReplaceProcessor(String tag, String field, String target, String replacement) {
        super(tag);
        this.field = field;
        this.target = target;
        this.replacement = replacement;
    }

    @Override
    public void execute(IngestDocument document) throws Exception {
        String value = document.getFieldValue(field, String.class);
        int count = Strings.countOccurrencesOf(value, target);
        String replaced = value.replaceAll(target, replacement);

        document.setFieldValue(field, replaced);
        document.setFieldValue("typo_count", count);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public static class Factory extends AbstractProcessorFactory<TypoReplaceProcessor> {
        @Override
        public TypoReplaceProcessor doCreate(String processorTag, Map<String, Object> config) throws Exception {
            String field = ConfigurationUtils.readStringProperty(TYPE, processorTag, config, "field");
            String target = ConfigurationUtils.readStringProperty(TYPE, processorTag, config, "target");
            String replacement = ConfigurationUtils.readStringProperty(TYPE, processorTag, config, "replacement");

            return new TypoReplaceProcessor(processorTag, field, target, replacement);
        }
    }
}
