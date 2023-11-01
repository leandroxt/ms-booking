## MS-BOOKING

How to run:
    
- Create envvar with any value `DB_PASSWD=SomePassword0`
- In the project root folder, run: `DB_PASSWD=SomePassword0 ./mvnw spring-boot:run`

### APIs

Payload for booking creation

```
{
    "propertyId": 1,
    "startDate":"2023-11-20T00:00:00.000000000",
    "finalDate":"2023-11-25T00:00:00.000000000",
    "guest": {
        "name": "Guest name",
        "email": "guest.email@gmail.com",
        "phone": "+15678901234"
    }
}
```

Payload for block creation

```
{
    "propertyId": 3,
    "startDate":"2023-11-20T00:00:00.000000000",
    "finalDate":"2023-11-25T00:00:00.000000000",
    "description": "Pool and garden maintenance"
}
```

### DB console

`http://localhost:8080/api/h2-console`

### architecture

client -> entry point (controllers) -> use case -> gateway.

- Entrypoint: controllers have their on response classes to deal with json serialization.
- UseCase: Classes that deal with only one task. Also validate business rules. They have their own domain classes with rules in it.
Every use case must have only one public `execute()` method. This way we enforce mixing different responsibilities in one class.
- Gateway is a package that is the other end of the api. Can interact with DB, 3rd party APIs, messages...

Between all layers, mappers are being used to map domains to the entry and gateway packages.

## Improvements

- Avoid code duplication in domain classes and use cases.