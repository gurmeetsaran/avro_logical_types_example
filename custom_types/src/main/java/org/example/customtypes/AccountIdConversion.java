package org.example.customtypes;

import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class AccountIdConversion  extends Conversion<Long> {
    private static final long PRIME_NUMBER = 64185959L;
    private static final long PRIME_NUMBER_INVERSE = 1703179806106473815L;
    private static final AccountIdConversion INSTANCE = new AccountIdConversion();
    private static AccountIdConversion get() { return INSTANCE; }

    public AccountIdConversion() { super(); }
    @Override
    public Class<Long> getConvertedType() {
        return Long.class;
    }

    @Override
    public String getLogicalTypeName() {
        return AccountIdLogicalType.ACCOUNT_ID_LOGICAL_TYPE_NAME;
    }

    @Override
    public Long fromLong(Long value, Schema schema, LogicalType type) {
        return (value * PRIME_NUMBER_INVERSE) & Long.MAX_VALUE;
    }

    @Override
    public Long toLong(Long value, Schema schema, LogicalType type) {
        return (value * PRIME_NUMBER) & Long.MAX_VALUE;
    }
}
