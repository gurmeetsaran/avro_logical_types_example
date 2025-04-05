package org.example;

import com.example.avro.customer.UserProfile;
import org.apache.avro.LogicalTypes;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.example.customtypes.AccountIdLogicalType;
import org.example.customtypes.EmailLogicalType;

import java.io.File;
import java.io.IOException;

public class LogicalTypeExample {
    public static void main(String[] args) {
        LogicalTypes.register(EmailLogicalType.EMAIL_LOGICAL_TYPE_NAME, new EmailLogicalType.TypeFactory());
        LogicalTypes.register(AccountIdLogicalType.ACCOUNT_ID_LOGICAL_TYPE_NAME, new AccountIdLogicalType.TypeFactory());
        UserProfile testUserProfile = UserProfile.newBuilder()
                .setId(100)
                .setUserEmail("hello@gmail.com")
                .setAccountId(23L)
                .build();
        final DatumWriter<UserProfile> userProfileDatumWriter = new SpecificDatumWriter<>(UserProfile.class);
        File f = new File("query.avro");
        try (DataFileWriter<UserProfile> dataFileWriter = new DataFileWriter<>(userProfileDatumWriter)) {
            dataFileWriter.create(testUserProfile.getSchema(), f);
            dataFileWriter.append(testUserProfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Written to " + f.getAbsolutePath());

        final DatumReader<UserProfile> userProfileDatumReader = new SpecificDatumReader<>(UserProfile.class);
        try (DataFileReader<UserProfile> userProfileDataFileReader = new DataFileReader<>(f, userProfileDatumReader)) {
            while (userProfileDataFileReader.hasNext()) {
                UserProfile record = userProfileDataFileReader.next();
                System.out.println("Id : " + record.getId());
                System.out.println("Email : " + record.getUserEmail());
                System.out.println("AccountId : " + record.getAccountId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
