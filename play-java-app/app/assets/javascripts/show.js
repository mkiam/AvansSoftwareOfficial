function showDiv() { 
    if (document.getElementById) { // DOM3 = IE5, NS6 
        document.getElementById('hideshow').style.visibility = 'visible'; 
    } else { 
        if (document.layers) { // Netscape 4 
            document.hideshow.visibility = 'visible'; 
        } else { // IE 4 
            document.all.hideshow.style.visibility = 'visible'; 
        } 
    } 
}