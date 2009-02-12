
var javaKeywords = new Array (
    "abstract"  ,
    "boolean"   ,
    "break"     ,
    "case"      ,
    "catch"     ,
    "class"     ,
    "continue"  ,
    "do"        ,
    "double"    ,
    "else"      ,
    "extends"   ,
    "float"     ,
    "for"       ,
    "foreach"   ,   
    "if"        ,
    "import"    ,
    "instanceof",
    "implements",
    "int"       ,
    "interface" ,
    "implement" , 
    "new"       ,
    "null"      ,
    "package"	,
    "private"   ,
    "protected" ,
    "public"    ,
    "return"    ,
    "static"    ,
    "super"     ,
    "switch"    ,
    "this"      ,
    "throw"     ,
    "throws"    ,
    "try"       ,
    "void"      ,
    "while"     ,
    "function"  ,

    "zzzzzzzz"
    );
var grayJavaKeywords = new Array (
	"@Override",
	"@Deprecated",
	"@SuppressWarnings"
	);
	


function paintColors() {
	keywords = "(";
	
	var keywordArray = javaKeywords;
	for ( var n = 0; n < keywordArray.length; n++)
		keywords += "\\b" + keywordArray[n] + "\\b|";

	keywords = keywords + "string)";
	// other
	otherKeywords = "(";
	var otherKeywordArray = grayJavaKeywords;
	for ( var i = 0; i < otherKeywordArray.length; i++) {
		otherKeywords += otherKeywordArray[i] + "|";
	}
	otherKeywords = otherKeywords + "string)";
	// Format pre
	var elems = document.getElementsByTagName("pre");
	for (n = elems.length - 1; n >= 0; n--) {
		if ((elems[n].className)
				&& (elems[n].className == "java" || elems[n].className == "javascript")) {
			format(elems[n], formatCs);
		}
	}


}
function format(node, func) {
	text = node.innerHTML;

	div = document.createElement("pre");
	var className = node.className;

	// remove trailing/leading lines
	while (text.charAt(0) == "\n" || text.charAt(0) == "\r")
		text = text.substr(1);

	while (text.charAt(text.length) == "\n" || text.charAt(text.length) == "\r")
		text = text.splice(0, -1);

	div.innerHTML = func(text);
	node.parentNode.replaceChild(div, node);
	div.className = className;
}         
                
function formatCs (text)
{
    var re = / /g;
    text = text.replace (re, "&nbsp;");

    // cant get this one to work in the good syntax
//  re = new RegExp ("\"((((?!\").)|\\\")*)\"","g");
    re = new RegExp ("(\"(?!\").+?\")","g");
    text = text.replace (re,"<span style='color:purple'>\$1\</span>");

    re = /\/\/(((.(?!\"\<\/span\>))|"(((?!").)*)"\<\/span\>)*)(\r|\n|\r\n)/g;
    text = text.replace (re,"<span style='color:green'>//$1</span><br/>");
    
    re = new RegExp (keywords,"g");
    text = text.replace (re,"<span style='color:blue'>$1</span>");
    re = new RegExp (otherKeywords,"g");
    text = text.replace (re,"<span style='color:gray'>$1</span>");
    re = /\t/g;
    text = text.replace (re,"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    
    re = /\n/g;
    text = text.replace (re,"<br/>");
    
    div = document.createElement("div");
    div.innerHTML = text;
    spans = div.getElementsByTagName ("span")
    for (var i = 0; i < spans.length; i++) {
        if (spans [i].parentNode.nodeName == "SPAN") {
            spans [i].style.color = "";
        }
    }
    
    return div.innerHTML;
}

window.onload = paintColors;
