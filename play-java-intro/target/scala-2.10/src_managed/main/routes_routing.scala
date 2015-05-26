// @SOURCE:C:/Users/MJ/AvansSoftwareOfficial/play-java-intro/conf/routes
// @HASH:61cdcb08417f6531e83d934657bfa7a825675aad
// @DATE:Sat May 09 07:43:50 CEST 2015


import scala.language.reflectiveCalls
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String): Unit = {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:8
private[this] lazy val controllers_Application_users1_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users"))))
private[this] lazy val controllers_Application_users1_invoker = createInvoker(
controllers.Application.users(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "users", Nil,"POST", """ Users""", Routes.prefix + """users"""))
        

// @LINE:9
private[this] lazy val controllers_Application_newUsers2_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users"))))
private[this] lazy val controllers_Application_newUsers2_invoker = createInvoker(
controllers.Application.newUsers,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "newUsers", Nil,"POST", """""", Routes.prefix + """users"""))
        

// @LINE:10
private[this] lazy val controllers_Application_deleteUsers3_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_Application_deleteUsers3_invoker = createInvoker(
controllers.Application.deleteUsers(fakeValue[long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deleteUsers", Seq(classOf[long]),"POST", """""", Routes.prefix + """users/$id<[^/]+>/delete"""))
        

// @LINE:13
private[this] lazy val controllers_Assets_at4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at4_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users""","""controllers.Application.users()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users""","""controllers.Application.newUsers"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/$id<[^/]+>/delete""","""controllers.Application.deleteUsers(id:long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index())
   }
}
        

// @LINE:8
case controllers_Application_users1_route(params) => {
   call { 
        controllers_Application_users1_invoker.call(controllers.Application.users())
   }
}
        

// @LINE:9
case controllers_Application_newUsers2_route(params) => {
   call { 
        controllers_Application_newUsers2_invoker.call(controllers.Application.newUsers)
   }
}
        

// @LINE:10
case controllers_Application_deleteUsers3_route(params) => {
   call(params.fromPath[long]("id", None)) { (id) =>
        controllers_Application_deleteUsers3_invoker.call(controllers.Application.deleteUsers(id))
   }
}
        

// @LINE:13
case controllers_Assets_at4_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at4_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     