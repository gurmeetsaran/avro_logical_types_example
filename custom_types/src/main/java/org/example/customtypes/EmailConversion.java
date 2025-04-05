package org.example.customtypes;

import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class EmailConversion  extends Conversion<CharSequence> {
    private static final EmailConversion INSTANCE = new EmailConversion();
    private static EmailConversion get() { return INSTANCE; }

    public EmailConversion() { super(); }
    @Override
    public Class<CharSequence> getConvertedType() {
        return CharSequence.class;
    }

    @Override
    public String getLogicalTypeName() {
        return EmailLogicalType.EMAIL_LOGICAL_TYPE_NAME;
    }

    @Override
    public CharSequence fromCharSequence(CharSequence value, Schema schema, LogicalType type) {
        return value.toString();
    }

    @Override
    public CharSequence toCharSequence(CharSequence value, Schema schema, LogicalType type) {
        String domain = value.toString().split("@")[1];
        return "******@" + domain;
    }
}
