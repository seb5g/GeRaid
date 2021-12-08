package outils;

public class OutilsJavaScript
{
  public static String getJavaScriptDefilementParPage()
  {
    StringBuffer retour = new StringBuffer("\n<script language='JavaScript'>\n");
    retour.append("function lancerScroll()\n" +
"{\n" +
"  if ((document.body.scrollTop + document.body.clientHeight) >= document.body.scrollHeight - 3) \n" +
"  {\n" +
"    document.body.scrollTop = 0;\n" +
"    window.location.reload();\n" +
"  }\n" +
"  var top = document.body.scrollTop;\n" +
"  scrollAuto(top);\n" +
"}\n" +
"\n" +
"function scrollAuto(arg)\n" +
"{\n" +
"  window.scrollBy(0,20);\n" +
"  if (document.body.scrollTop < (arg + document.body.clientHeight - document.body.clientHeight/5)) \n" +
"  {\n" +
"    setTimeout(scrollAuto,100, arg);\n" +
"  }\n" +
"}\n" +
    "</script>\n");
    return retour.toString();
  }
  
  public static String getJavaScriptLancementDefilementParPage(int seconde)
  {
    return "\n<script type='text/javascript'>var myVar = setInterval(lancerScroll," + seconde  + ")</script>\n";
  }
  
  public static String getJavaScriptDefilementEnContinu(int miliSeconde)
  {
    StringBuffer retour = new StringBuffer("<script language='JavaScript'>\n");
    retour.append("function scrollAuto()\n" +
    "{\n" +
      "var index = " + miliSeconde + ";\n" +
     " window.scrollBy(0,2);\n" +
      "if (document.body.scrollTop < 150) \n" +
      "{\n" +
        "index = index*3;\n" +
      "}\n" +
      "if ((document.body.scrollTop + document.body.clientHeight) >= document.body.scrollHeight - 3) \n" +
      "{\n" +
        "document.body.scrollTop = 0;\n" +
        "window.location.reload();\n" +
      "}\n" +
      "if ((document.body.scrollTop + document.body.clientHeight) > document.body.scrollHeight - 150)\n" + 
      "{\n" +
        "index = index*3;\n" +
      "}\n" +
      "setTimeout('scrollAuto()',index);\n" +
      "}\n" +
    "</script>\n");
    return retour.toString();
  }
  
  public static String getJavaScriptLancementDefilementEnContinu()
  {
    return "<script type='text/javascript'>scrollAuto();</script>";
  }
}
