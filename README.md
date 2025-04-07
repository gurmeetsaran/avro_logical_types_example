# Avro Logical Types Example

This repository demonstrates how to use [Apache Avro](https://avro.apache.org/) logical types effectively in data schemas and processing pipelines. Logical types enable more precise representation of complex data types such as timestamps, dates, decimals, and UUIDs, which are otherwise stored as primitive types in Avro.

## Features

- Examples of how to create custom logical type for Avro logical types:
- email - for masking username part of the email
- accountId - for obfuscating accountId field using [modular multiplicative inverse](https://en.wikipedia.org/wiki/Modular_multiplicative_inverse)


### Clone the Repository

```bash
git clone https://github.com/gurmeetsaran/avro_logical_types_example.git
cd avro_logical_types_example
mvn clean package
run LogicalTypeExample
