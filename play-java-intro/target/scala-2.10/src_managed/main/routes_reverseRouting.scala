// @SOURCE:C:/Users/MJ/AvansSoftwareOfficial/play-java-intro/conf/routes
// @HASH:61cdcb08417f6531e83d934657bfa7a825675aad
// @DATE:Sat May 09 07:43:50 CEST 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers {

// @LINE:13
class ReverseAssets {


// @LINE:13
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:8
def users(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "users")
}
                        

// @LINE:9
def newUsers(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "users")
}
                        

// @LINE:10
def deleteUsers(id:long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "users/" + implicitly[PathBindable[long]].unbind("id", id) + "/delete")
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          
}
                  


// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:13
class ReverseAssets {


// @LINE:13
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:8
def users : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.users",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users"})
      }
   """
)
                        

// @LINE:9
def newUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.newUsers",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users"})
      }
   """
)
                        

// @LINE:10
def deleteUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.deleteUsers",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + (""" + implicitly[PathBindable[long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              
}
        


// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers.ref {


// @LINE:13
class ReverseAssets {


// @LINE:13
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:8
def users(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.users(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "users", Seq(), "POST", """ Users""", _prefix + """users""")
)
                      

// @LINE:9
def newUsers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.newUsers(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "newUsers", Seq(), "POST", """""", _prefix + """users""")
)
                      

// @LINE:10
def deleteUsers(id:long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.deleteUsers(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deleteUsers", Seq(classOf[long]), "POST", """""", _prefix + """users/$id<[^/]+>/delete""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

}
                          
}
        
    