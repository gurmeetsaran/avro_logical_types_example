package org.example.customtypes;

import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;

public class AccountIdLogicalType extends LogicalType {
    public static final String ACCOUNT_ID_LOGICAL_TYPE_NAME = "accountId";
    public static class TypeFactory implements LogicalTypes.LogicalTypeFactory {
        private final LogicalType accountIdLogicalType = new AccountIdLogicalType();
        @Override
        public LogicalType fromSchema(Schema schema) {
            return accountIdLogicalType;
        }

        @Override
        public String getTypeName() {
            return accountIdLogicalType.getName();
        }
    }
    public AccountIdLogicalType() {
        super(ACCOUNT_ID_LOGICAL_TYPE_NAME);
    }

    public void validate(Schema schema) {
        super.validate(schema);
        if (schema.getType() != Schema.Type.LONG) {
            throw new IllegalArgumentException("Logical type 'accountId' must be long");
        }
    }
}

