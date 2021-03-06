package edu.duke.oit.vw.models

import edu.duke.oit.vw.utils._
import edu.duke.oit.vw.solr.Vivo

case class GeographicFocus(uri:String,
                   vivoType: String,
                   label: String,
                   attributes:Option[Map[String, String]])
     extends VivoAttributes(uri, vivoType, label, attributes) with AddToJson
{

  override def uris():List[String] = {
    uri :: super.uris
  }

}

object GeographicFocus extends AttributeParams {

  def fromUri(vivo: Vivo, uriContext:Map[String, Any], templatePath: String="sparql/geographicFoci.ssp") = {
    val data  = vivo.selectFromTemplate(templatePath, uriContext)
    data.map(build(_)).asInstanceOf[List[GeographicFocus]]
  }


  def build(geographicFocus:Map[Symbol,String]) = {
    new GeographicFocus(uri         = geographicFocus('uri).stripBrackets(),
                        vivoType    = geographicFocus('type).stripBrackets(),
                        label       = geographicFocus('label),
                        attributes  = parseAttributes(geographicFocus, List('uri,'type,'label)))
  }

}
