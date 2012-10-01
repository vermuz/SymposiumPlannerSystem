
var XMLHttpReq;
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		XMLHttpReq = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch (e) {
				}
			}
		}
	}
}
function analyse() {
	if (document.form1.oid.value == "") {
		alert("The oid cannot be null!");
		form1.oid.focus();
		return;
	}
	
	if (document.form1.sender.value == "") {
		alert("The sender cannot be null!");
		form1.sender.focus();
		return;
	}
	
	if (document.form1.ace.value == "") {
		alert("The ace text cannot be null!");
		form1.ace.focus();
		return;
	}
	var oid = document.form1.oid.value;
	var sender = document.form1.sender.value;
	var ace = document.form1.ace.value;
	var addDrs = document.form1.addDrs.value;
	var ulexfile = document.form1.ulexfile.value;
	var protocol = document.form1.protocal.value;
	createXMLHttpRequest();
	XMLHttpReq.open("GET", "ACE2RRuleMLServlet?oid=" + oid+"&sender="+sender+"&ace="+ace+"&addDrs=" + addDrs+"&ulexfile="+ulexfile+"&protocol="+protocol, true);
	XMLHttpReq.onreadystatechange = processAnalyseResponse;
	XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	XMLHttpReq.send(null);
}
function processAnalyseResponse() {
	if (XMLHttpReq.readyState == 4) {
		if (XMLHttpReq.status == 200) {
			document.getElementById("result").innerHTML = XMLHttpReq.responseText;
		} else {
			window.alert("Parsing Error!");
		}
	}
}


