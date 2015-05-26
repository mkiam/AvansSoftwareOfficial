
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""

"""),_display_(/*3.2*/main("Welcome to Play")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
	"""),format.raw/*4.2*/("""<script type='text/javascript' src='"""),_display_(/*4.39*/routes/*4.45*/.Assets.at("javascripts/index.js")),format.raw/*4.79*/("""'></script>

	<ul id="persons"></ul>

	<form method="POST" action=""""),_display_(/*8.31*/routes/*8.37*/.Application.addPerson()),format.raw/*8.61*/("""">
		<input type="text" name="name"/>
		<button>Add Person</button>
	</form>
""")))}),format.raw/*12.2*/("""
"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat May 09 07:30:37 CEST 2015
                  SOURCE: C:/Users/MJ/AvansSoftwareOfficial/play-java-intro/app/views/index.scala.html
                  HASH: 8ed6f590d91c699bfa7a8da87751ba049d8eafd6
                  MATRIX: 716->1|805->3|833->6|864->29|903->31|931->33|994->70|1008->76|1062->110|1156->178|1170->184|1214->208|1322->286
                  LINES: 26->1|29->1|31->3|31->3|31->3|32->4|32->4|32->4|32->4|36->8|36->8|36->8|40->12
                  -- GENERATED --
              */
          