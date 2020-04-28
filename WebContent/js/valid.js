function validateForm() {
    var elements = document.getElementsByTagName("input");
    for(var i=0; i<elements.length; i++) {
        if (elements[i].value == ""){
        	alert("Fields has not be empty");
        	return false;
        }
    }
} 