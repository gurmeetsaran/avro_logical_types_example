package org.example.customtypes;

import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;

public class EmailLogicalType extends LogicalType {
    public static final String EMAIL_LOGICAL_TYPE_NAME = "email";
    public static class TypeFactory implements LogicalTypes.LogicalTypeFactory {
        private final LogicalType emailLogicalType = new EmailLogicalType();
        @Override
        public LogicalType fromSchema(Schema schema) {
            return emailLogicalType;
        }

        @Override
        public String getTypeName() {
            return emailLogicalType.getName();
        }
    }
    public EmailLogicalType() {
        super(EMAIL_LOGICAL_TYPE_NAME);
    }

    public void validate(Schema schema) {
        super.validate(schema);
        if (schema.getType() != Schema.Type.STRING) {
            throw new IllegalArgumentException("Logical type 'email' must be string");
        }
    }
}
