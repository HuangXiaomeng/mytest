namespace java org.armon.test.service.thrift

struct Topic {
1: required i32 uid,
2: required string name,
3: required string content
}

service TopicService {
  void store(1: Topic topic),
  Topic retrieve(1: i32 uid)
}
