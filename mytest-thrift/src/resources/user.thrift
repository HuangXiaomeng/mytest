namespace java org.armon.test.service.thrift

struct User {
1: required i32 uid,
2: required string name,
}

service UserService {
  void store(1: User user),
  User retrieve(1: i32 uid)
}
