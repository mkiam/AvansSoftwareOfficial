
@()

@main("Smart recipes") {
<!Doctype html>
<html>
<head>
<meta charset="UTF-8">
<link href= '@routes.Assets.at("stylesheets/menu.css")'rel="stylesheet">
<title>search</title>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="Menu.html"><img class = "logo" src='@routes.Assets.at("images/logo.png")' /></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" class="form-control login"
							placeholder="Login">
					</div>
					<div class="form-group">
						<input type="password" class="form-control password"
							placeholder="Password">
					</div>
					<input type="submit" class="btn btn-success" id="signinbtn"
						value="Connexion">
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
<nav class="options">
<center><ul>
<li><b>About this site</b></li>
<li>--------------------------------</li>
<li>Help</li>
<li>Authors</li>
<li><a href="https://www.facebook.com/avans?fref=ts">Facebook</a></li>
<li><a href= "https://twitter.com/avanshogeschool">Twitter</a></li>
<li><a HREF="mailto:migan_karen@yahoo.fr">Contact us</a></li>
<li>

</li>
</ul></center>
<center><ul>
<br/>
</nav>
<nav class="options2">
<center><ul>
<li><b>Recipes</b></li>
<li>-------------------------------</li>
<li> Search recipes</li>
<li> Add recipes recipes</li>
<li> My favourites recipes</li>
<li> My account</li>
<li> Log out </li>

<br/>
<br/>
</nav>
</ul></center>
</nav>


 


<section class="corps">

<br/>
	
</head>

<body>
<script> </script>
<p> <b id ="welcome"> </b><p>
Five Little Ducks
Five little ducks went swimming one day,

Over the hill and far away.

Mother duck said, 'QUACK, QUACK, QUACK, QUACK!'

And only four little ducks came back!

 

Four little ducks went swimming one day,

Over the hill and far away.

Mother duck said, 'QUACK, QUACK, QUACK, QUACK!'

And only three little ducks came back!

 

Three little ducks went swimming one day,

Over the hill and far away.

Mother duck said, 'QUACK, QUACK, QUACK, QUACK!'

And only two little ducks came back!

 

Two little ducks went swimming one day

Over the hill and far away.

Mother duck said, 'QUACK, QUACK, QUACK, QUACK!'

And only one little duck came back!

 

One little duck went swimming one day,

Over the hill and far away.

Mother duck said, 'QUACK, QUACK, QUACK, QUACK!'

And all her five little ducks came back!
		
	
	
	<br/>
	<br/>
	<br/>
<form method= "get">
 <img src='@routes.Assets.at("images/gauche.png")'/>
<input type= "text" name= "texte" class="pictureInput" size= "53" placeholder="Type your keywords :)" required/>
<input type= "submit" value="Search"/></form>
<br/>
<br/>
<br/>
<p><b><i>Your search results:</i></b></p>

<br/>
<br/>
<br/>





























<p>All rights reserved - IT departement 2015 - Avans Hogeschool</p></center>
</section>
</body>
</html>

}
