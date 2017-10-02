package sample.service

import org.http4s._
import org.http4s.dsl._
import org.http4s.circe._

import io.circe._
import io.circe.generic.auto._
import io.circe.generic._
import io.circe.syntax._

import sample.data._

trait MemberService {
  val memberService = HttpService {
    case GET -> Root / "_healthcheck" => Ok("ok")

    case req @ PUT -> Root / "member" =>
      for {
        member <- req.as(jsonOf[Member])
        x      <- Ok(member.asJson)
      } yield x

    case req @ POST -> Root / "member" =>
      for {
        member <- req.as(jsonOf[Member])
        x      <- Ok(member.asJson)
      } yield x
  }
}

object MemberService extends MemberService
