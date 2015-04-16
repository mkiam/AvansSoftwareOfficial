function addFile(){
	var divNode = document.createElement('div');
	var fileNode = document.createElement('input');
	fileNode.setAttribute('type', 'file');
	fileNode.setAttribute('name', 'fichier[]');
	fileNode.onchange = function(event){ fileChange1(this);}
	divNode.appendChild(fileNode);
	
	var lkRmvNode = document.createElement('input');
	lkRmvNode.setAttribute('type', 'button');
	lkRmvNode.setAttribute('value', 'Supprimer');
	lkRmvNode.setAttribute('style', 'color:blue; border-style:outset;font-family:Arial;  text-decoration: underline;border: none; border-width:1px; background:#FFFFFF;position:relative;float:right;margin-right:51.15%')
	lkRmvNode.onclick = function(event){ removeFileNode(this);}
	divNode.appendChild(lkRmvNode);
	document.getElementById('files').appendChild(divNode);
}
 function removeFileNode( node){
	document.getElementById('files').removeChild(node.parentNode);
}
	
//verifier type et taille	
var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 
function fileChange1(target,id) { 
	var fileSize = 0; 
	var filetypes =[".pdf"];
	var filepath = target.value; 
	var filemaxsize = 1024*5;//5M 
	if(filepath){ 
	var isnext = false; 
	var fileend = filepath.substring(filepath.indexOf(".")); 
	if(filetypes && filetypes.length>0){ 
	for(var i =0; i<filetypes.length;i++){ 
	if(filetypes[i]==fileend){ 
	isnext = true; 
	break; 
	} 
	} 
	} 
	if(!isnext){ 
	alert("You cannot add this file! You can just add 'pdf' files.)"); 
	target.value =""; 
	return false; 
	} 
	}else{ 
	return false; 
	} 
	if (isIE && !target.files) { 
	var filePath = target.value; 
	var fileSystem = new ActiveXObject("Scripting.FileSystemObject"); 
	if(!fileSystem.FileExists(filePath)){ 
	alert("Ce fichier n'existe pas, insérer une fois encore !"); 
	return false; 
	} 
	var file = fileSystem.GetFile (filePath); 
	fileSize = file.Size; 
	} else { 
	fileSize = target.files[0].size; 
	} 

	var size = fileSize / 1024; 
	if(size>filemaxsize){ 
	alert("Ce fichier ne peut pas dépasser 5Mo!"); 
	target.value =""; 
	return false; 
	} 
	if(size<=0){ 
	alert("la taille de ce fichier ne peut pas être 0Mo!"); 
	target.value =""; 
	return false; 
	} 
} 


