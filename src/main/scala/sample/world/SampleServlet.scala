package sample.world

import org.http4s._
import org.http4s.servlet._
import org.http4s.server._
import sample.service._

class SampleServlet
    extends Http4sServlet(
      service = MemberService.memberService,
      //servletIo = NonBlockingServletIo(4096),
      serviceErrorHandler = SampleServlet.serviceErrorHandler
    )

object SampleServlet {
  val serviceErrorHandler: ServiceErrorHandler = req => {
    case x: Throwable =>
      x.printStackTrace()
      DefaultServiceErrorHandler(req).apply(x)
  }
}
