namespace java org.armon.test.service.thrift

struct TMytestStatus {
1: required i32 value,
// message will be set to empty string when status is OK
2: optional string message
}

struct THelloWorldRequest {
1: required string data
}
struct THelloWorldResponse {
1: required TMytestStatus status
}

service HelloWorldService
{
  THelloWorldResponse sayHello(1:THelloWorldRequest request)
}