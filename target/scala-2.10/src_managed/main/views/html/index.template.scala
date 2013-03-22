
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[List[Project],List[Task],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(projects: List[Project], todoTasks: List[Task]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.50*/("""

"""),_display_(Seq[Any](/*3.2*/main("Welcome to Play 2.0")/*3.29*/ {_display_(Seq[Any](format.raw/*3.31*/("""

    <header>
        <hgroup>
            <h1>Dashboard</h1>
            <h2>Tasks over all projects</h2>
        </hgroup>
    </header>

    <article  class="tasks">
        """),_display_(Seq[Any](/*13.10*/todoTasks/*13.19*/.groupBy(_.getProject()).map/*13.47*/ {/*14.13*/case (project, tasks) =>/*14.37*/ {_display_(Seq[Any](format.raw/*14.39*/("""
                <div class="folder" data-folder-id=""""),_display_(Seq[Any](/*15.54*/project/*15.61*/.getId())),format.raw/*15.69*/("""">
                    <header>
                        <h3>"""),_display_(Seq[Any](/*17.30*/project/*17.37*/.getName())),format.raw/*17.47*/("""</h3>
                    </header>
                    <ul class="list">
                        """),_display_(Seq[Any](/*20.26*/tasks/*20.31*/.map/*20.35*/ { task =>_display_(Seq[Any](format.raw/*20.45*/("""
                            <li data-task-id=""""),_display_(Seq[Any](/*21.48*/task/*21.52*/.getId())),format.raw/*21.60*/("""">
                                <h4>"""),_display_(Seq[Any](/*22.38*/task/*22.42*/.getTitle())),format.raw/*22.53*/("""</h4>
                                """),_display_(Seq[Any](/*23.34*/if(task.getDueDate() != null)/*23.63*/ {_display_(Seq[Any](format.raw/*23.65*/("""
                                    <time datetime=""""),_display_(Seq[Any](/*24.54*/task/*24.58*/.getDueDate())),format.raw/*24.71*/("""">
                                        """),_display_(Seq[Any](/*25.42*/task/*25.46*/.getDueDate().format("MMM dd yyyy"))),format.raw/*25.81*/("""</time>
                                """)))})),format.raw/*26.34*/("""

                                """),_display_(Seq[Any](/*28.34*/if(task.getAssignedTo() != null && task.getAssignedTo().getEmail() != null)/*28.109*/ {_display_(Seq[Any](format.raw/*28.111*/("""
                                    <span class="assignedTo">"""),_display_(Seq[Any](/*29.63*/task/*29.67*/.getAssignedTo().getEmail())),format.raw/*29.94*/("""</span>
                                """)))})),format.raw/*30.34*/("""
                            </li>
                        """)))})),format.raw/*32.26*/("""
                    </ul>
                </div>
            """)))}})),format.raw/*36.10*/("""
    </article>

""")))})))}
    }
    
    def render(projects:List[Project],todoTasks:List[Task]): play.api.templates.Html = apply(projects,todoTasks)
    
    def f:((List[Project],List[Task]) => play.api.templates.Html) = (projects,todoTasks) => apply(projects,todoTasks)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Mar 13 19:14:49 CET 2013
                    SOURCE: D:/Programme/playprojects/zentasks/app/views/index.scala.html
                    HASH: d12c8ad32f436c2feeda078b905d5efe1f43f97d
                    MATRIX: 741->1|866->49|903->52|938->79|977->81|1192->260|1210->269|1247->297|1258->312|1291->336|1331->338|1421->392|1437->399|1467->407|1564->468|1580->475|1612->485|1747->584|1761->589|1774->593|1822->603|1906->651|1919->655|1949->663|2025->703|2038->707|2071->718|2146->757|2184->786|2224->788|2314->842|2327->846|2362->859|2442->903|2455->907|2512->942|2585->983|2656->1018|2741->1093|2782->1095|2881->1158|2894->1162|2943->1189|3016->1230|3108->1290|3204->1363
                    LINES: 26->1|29->1|31->3|31->3|31->3|41->13|41->13|41->13|41->14|41->14|41->14|42->15|42->15|42->15|44->17|44->17|44->17|47->20|47->20|47->20|47->20|48->21|48->21|48->21|49->22|49->22|49->22|50->23|50->23|50->23|51->24|51->24|51->24|52->25|52->25|52->25|53->26|55->28|55->28|55->28|56->29|56->29|56->29|57->30|59->32|62->36
                    -- GENERATED --
                */
            