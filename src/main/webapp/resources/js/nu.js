// JavaScript Document
/*
sfHover = function() {
	var sfEls = document.getElementById("Menu").getElementsByTagName("LI");
	for (var i=0; i<sfEls.length; i++) {
		sfEls[i].onmouseover=function() {
			this.className+=" hover";
		}
		sfEls[i].onmouseout=function() {
			this.className=this.className.replace(new RegExp(" hover\\b"), "");
		}
	}	
}
if (window.attachEvent) window.attachEvent("onload", sfHover);*/

function resizeFrame() {
	$('childframe').style.height = $('childframe').contentWindow.document.body.scrollHeight + 60 + "px";
}