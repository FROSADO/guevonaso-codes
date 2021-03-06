
var javaKeywords = new Array (
    "abstract"  ,    "boolean"   ,
    "break"     ,    "case"      ,
    "catch"     ,    "class"     ,
    "continue"  ,    "do"        ,
    "double"    ,    "else"      ,
    "extends"   ,    "float"     ,
    "for"       ,    "foreach"   ,   
    "if"        ,    "import"    ,
    "instanceof",    "implements",
    "int"       ,    "interface" ,
    "implement" ,    "new"       ,
    "null"      ,    "package"	,
    "private"   ,    "protected" ,
    "public"    ,    "return"    ,
    "static"    ,    "super"     ,
    "switch"    ,    "this"      ,
    "throw"     ,    "throws"    ,
    "try"       ,    "void"      ,
    "while"     ,    "function"  ,
    "zzzzzzzz"
    );
var grayJavaKeywords = new Array (
	"@Override",	"@Deprecated",
	"@SuppressWarnings"
	);
	
var cmdKeywords = new Array (
	"rem",		"set",
	"if",		"else",
	"exist",	"errorlevel",
	"for",		"in",
	"do",		"break",
	"call",		"copy",
	"chcp",		"cd",
	"chdir",	"choice",
	"cls",		"country",
	"ctty",		"date",
	"del",		"erase",
	"dir",		"echo",
	"exit",		"goto",
	"loadfix",	"loadhigh",
	"mkdir",	"md",
	"move",		"path",
	"pause",	"prompt",
	"rename",	"ren",
	"rmdir",	"rd",
	"shift",	"time",
	"type",		"ver",
	"verify",	"vol",
	"com",		"con",
	"lpt",		"nul",
	"defined",	"not",
	"errorlevel",	"endlocal",
	"setlocal",	"cmdextversion"
);

var grayCmdKeywords = new Array(
	"%1","%2","%3","%4","%5","%0","%~dp0","%~d1","%~f0","%Date","%Time"	
);
function paintColors() {
	// Change stylesheet 
		
	
	// Format pre
	var elems = document.getElementsByTagName("pre");
	for (var n = elems.length - 1; n >= 0; n--) {
		if (elems[n].className) {
		  if(elems[n].className == "java" || elems[n].className == "javascript") {
			format(elems[n], formatJava,javaKeywords,grayJavaKeywords);
		  } else if (elems[n].className=="cmd") {
			format(elems[n],formatCmd,cmdKeywords,grayCmdKeywords);
		  } else if (elems[n].className=="xml") {
			format(elems[n],formatXML,[],[]);
		  }
		}
	}
}

function format(node, func,keywordArray,otherKeywordArray) {
	keywords = "(";
	
	for ( var n = 0; n < keywordArray.length; n++)
		keywords += "\\b" + keywordArray[n] + "\\b|";

	keywords = keywords + "string)";
	// other
	otherKeywords = "(";
	for ( var i = 0; i < otherKeywordArray.length; i++) {
		otherKeywords += otherKeywordArray[i] + "|";
	}
	otherKeywords = otherKeywords + "string)";
	text = node.innerHTML;

	div = document.createElement("pre");
	var className = node.className;

	// remove trailing/leading lines
	while (text.charAt(0) == "\n" || text.charAt(0) == "\r")
		text = text.substr(1);

	while (text.charAt(text.length) == "\n" || text.charAt(text.length) == "\r")
		text = text.splice(0, -1);

	div.innerHTML = func(text,keywords,otherKeywords);
	node.parentNode.replaceChild(div, node);
	div.className = className;
}         
        
function formatXML(text,keywords,otherKeywords) {
	var re= new RegExp ("(.*)&lt;(.*)&gt;(.*)","g");
	text = text.replace(re,"$1###SPAN-START###&lt;$2&gt;###SPAN-END###$3");
	re = new RegExp("=\"(.*)\"([. ]*)","g");
	text = text.replace(re,"=###START-QUOTES-###$1###END-QUOTES-###$2 ");
	text = text.replace(/###SPAN-START###/g,"<span class='formatted' style='color:blue'>");
	text = text.replace(/###SPAN-END###/g,"</span>");
	text = text.replace(/###START-QUOTES-###/g,"<span class='formatted' style='color:green'>\"");
	text = text.replace(/###END-QUOTES-###/g,"\"</span>");
	return text;
}
function formatJava (text,keywords,otherKeywords)
{
    var re = / /g;
    text = text.replace (re, "&nbsp;");

    // cant get this one to work in the good syntax
//  re = new RegExp ("\"((((?!\").)|\\\")*)\"","g");
    re = new RegExp ("(\"(?!\").+?\")","g");
    text = text.replace (re,"<span style='color:purple'>\$1\</span>");

    re = /\/\/(((.(?!\"\<\/span\>))|"(((?!").)*)"\<\/span\>)*)(\r|\n|\r\n)/g;
    text = text.replace (re,"<span class='formatted' style='color:green'>//$1</span><br/>");
    
    re = new RegExp (keywords,"g");
    text = text.replace (re,"<span class='formatted' style='color:blue'>$1</span>");
    re = new RegExp (otherKeywords,"g");
    text = text.replace (re,"<span class='formatted' style='color:gray'>$1</span>");
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
function formatCmd (text,keywords,otherKeywords)
{
    var re = / /g;
    text = text.replace (re, "&nbsp;");

    // cant get this one to work in the good syntax
//  re = new RegExp ("\"((((?!\").)|\\\")*)\"","g");
    re = new RegExp ("(\"(?!\").+?\")","g");
    text = text.replace (re,"<span class='formatted' style='color:purple'>\$1\</span>");
//     re = new RegExp ("(\%(?!\%).+?\%+)","g");
//    text = text.replace (re,"<span style='color:red'>\$1\</span>");
    re = new RegExp (keywords,"gi");
    text = text.replace (re,"<span class='formatted' style='color:blue'>$1</span>");
    re = new RegExp (otherKeywords,"gi");
    text = text.replace (re,"<span class='formatted' style='color:gray'>$1</span>");
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

function openPingbox (span) {
	var parent = span.parentNode;
	if (parent.tagName != "DIV") {
		openPingbox(parent);
	} else {
		parent.innerHTML = "<object id=\"pingboxisju8km88mg00\" "+
 		"type=\"application/x-shockwave-flash\" " +
		"data=\"http://wgweb.msg.yahoo.com/badge/Pingbox.swf\"" +
		" width=\"240\" height=\"420\">" +
		"<param name=\"movie\" " +
		" value=\"http://wgweb.msg.yahoo.com/badge/Pingbox.swf\" />" +
		"<param name=\"allowScriptAccess\" value=\"always\" />" +
		"<param name=\"flashvars\" value=\"wid=8ERxF6i9RnC6gELGyaQZmEjjzQDIod380pvsA6E6.rZMuD9z\" /" +
		"></object>";
	}
	
}

window.onload = paintColors;
