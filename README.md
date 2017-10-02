# Http4s Servlet Sample

This project reappear the a weird thing about `Http4sServlet`, It can't handle the entity PUT/POST from http client 
when setting the `servletIo` to `NonBlockingServletIo`.

## ReAppear

1. Failed when using NonBlockingServletIo

  Set `servletIo` of `SampleServlet` to `BlockingServletIo`, or just delete the line 10 of `SampleServlet.scala`.
  run the service by execute the command: 
  
  ```
  sbt "tomcat:start"
  ```
  
  then, send a Http request with PUT an json entity:
  
  ```
  curl -X PUT \
  http://localhost:8080/member \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 9c1ccd0c-db1c-517a-8a9a-11de62844379' \
  -d '{"name" : "Some memberName"}'
  ```
  
  then, client would got a 400 error, and the response entity would be `The request body was malformed.`; and the server
  raised an exception, the stack trace is:
  
  ```
  2017-10-03 00:02:30,923 DEBUG [org.http4s.server.message-failures@package.scala#81] [ForkJoinPool-1-worker-9] - Message failure handling request: POST /member from 0:0:0:0:0:0:0:1
org.http4s.MalformedMessageBodyFailure: Malformed message body: Invalid JSON
	at org.http4s.circe.CirceInstances$$anonfun$org$http4s$circe$CirceInstances$$jsonDecoderByteBufferImpl$1.apply(CirceInstances.scala:31)
	at org.http4s.circe.CirceInstances$$anonfun$org$http4s$circe$CirceInstances$$jsonDecoderByteBufferImpl$1.apply(CirceInstances.scala:24)
	at cats.data.EitherT$$anonfun$flatMap$1.apply(EitherT.scala:77)
	at cats.data.EitherT$$anonfun$flatMap$1.apply(EitherT.scala:75)
	at fs2.Task$$anonfun$flatMap$1$$anonfun$1.apply(Task.scala:40)
	at fs2.Task$$anonfun$flatMap$1$$anonfun$1.apply(Task.scala:40)
	at fs2.util.Attempt$.apply(Attempt.scala:12)
	at fs2.Task$$anonfun$flatMap$1.apply(Task.scala:40)
	at fs2.Task$$anonfun$flatMap$1.apply(Task.scala:38)
	at fs2.internal.Future$$anonfun$flatMap$1.apply(Future.scala:17)
	at fs2.internal.Future$$anonfun$flatMap$1.apply(Future.scala:17)
	at fs2.internal.Future.step(Future.scala:54)
	at fs2.internal.Future.listen(Future.scala:30)
	at fs2.internal.Future$$anonfun$listen$1$$anonfun$apply$7.apply(Future.scala:34)
	at fs2.internal.Future$$anonfun$listen$1$$anonfun$apply$7.apply(Future.scala:34)
	at fs2.internal.Trampoline$$anonfun$map$1.apply(Trampoline.scala:10)
	at fs2.internal.Trampoline$$anonfun$map$1.apply(Trampoline.scala:10)
	at fs2.internal.Trampoline$.run(Trampoline.scala:31)
	at fs2.internal.Trampoline$class.run(Trampoline.scala:12)
	at fs2.internal.Trampoline$FlatMap.run(Trampoline.scala:18)
	at fs2.Task$$anonfun$async$2$$anonfun$apply$19$$anonfun$apply$2.apply$mcV$sp(Task.scala:247)
	at fs2.Strategy$$anon$3$$anon$4.run(Strategy.scala:54)
	at org.http4s.util.TrampolineExecutionContext$.execute(TrampolineExecutionContext.scala:27)
	at fs2.Strategy$$anon$3.apply(Strategy.scala:54)
	at fs2.Task$$anonfun$async$2$$anonfun$apply$19.apply(Task.scala:247)
	at fs2.Task$$anonfun$async$2$$anonfun$apply$19.apply(Task.scala:247)
	at org.http4s.servlet.NonBlockingServletIo$$anonfun$reader$1$$anonfun$1$$anon$1.onAllDataRead(ServletIo.scala:113)
	at org.apache.catalina.connector.CoyoteAdapter.asyncDispatch(CoyoteAdapter.java:213)
	at org.apache.coyote.AbstractProcessor.dispatch(AbstractProcessor.java:221)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:53)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:861)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1455)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	at org.apache.tomcat.util.net.AbstractEndpoint.processSocket(AbstractEndpoint.java:934)
	at org.apache.tomcat.util.net.SocketWrapperBase.processSocket(SocketWrapperBase.java:712)
	at org.apache.coyote.AbstractProcessor.executeDispatches(AbstractProcessor.java:672)
	at org.apache.coyote.AbstractProcessor.action(AbstractProcessor.java:464)
	at org.apache.coyote.Request.action(Request.java:424)
	at org.apache.catalina.connector.InputBuffer.setReadListener(InputBuffer.java:255)
	at org.apache.catalina.connector.CoyoteInputStream.setReadListener(CoyoteInputStream.java:281)
	at org.http4s.servlet.NonBlockingServletIo$$anonfun$reader$1$$anonfun$1.apply(ServletIo.scala:97)
	at org.http4s.servlet.NonBlockingServletIo$$anonfun$reader$1$$anonfun$1.apply(ServletIo.scala:93)
	at fs2.Task$$anonfun$async$2.apply(Task.scala:246)
	at fs2.Task$$anonfun$async$2.apply(Task.scala:246)
	at fs2.internal.Future.listen(Future.scala:34)
	at fs2.internal.Future.runAsync(Future.scala:69)
	at fs2.Task.unsafeRunAsync(Task.scala:100)
	at fs2.Task$Ref$$anonfun$set$1$$anonfun$apply$mcV$sp$1.apply$mcV$sp(Task.scala:343)
	at fs2.Strategy$$anon$3$$anon$4.run(Strategy.scala:54)
	at scala.concurrent.impl.ExecutionContextImpl$AdaptedForkJoinTask.exec(ExecutionContextImpl.scala:121)
	at scala.concurrent.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
	at scala.concurrent.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
	at scala.concurrent.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
	at scala.concurrent.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)
Caused by: jawn.IncompleteParseException: exhausted input
	at jawn.Parser.parse(Parser.scala:362)
	at jawn.SyncParser.parse(SyncParser.scala:24)
	at jawn.SupportParser$$anonfun$parseFromByteBuffer$1.apply(SupportParser.scala:27)
	at scala.util.Try$.apply(Try.scala:192)
	at jawn.SupportParser$class.parseFromByteBuffer(SupportParser.scala:27)
	at io.circe.jawn.CirceSupportParser$.parseFromByteBuffer(CirceSupportParser.scala:6)
	at io.circe.jawn.JawnParser.parseByteBuffer(JawnParser.scala:22)
	at org.http4s.circe.CirceInstances$$anonfun$org$http4s$circe$CirceInstances$$jsonDecoderByteBufferImpl$1.apply(CirceInstances.scala:27)
	
  ```


2. Succeed in parse the entity when using `BlockingServletIo`

  set `servletIo` to `SampleServlet`, relaunch server. resend the request, and will get the correct response:
  
  ```
  {
    "name": "some memberName"
  }
  ```
