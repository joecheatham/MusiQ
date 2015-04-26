<?php


?>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="MusiQ">
    <meta name="author" content="Joe Cheatham">

    <title>MusiQ - Web Interface</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootplus.css" rel="stylesheet">
    <link href="css/darkstrap.css" rel="stylesheet">
    <link href="css/bootstrap.css.map" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/audio.css" rel="stylesheet">

    <!-- Firebase JS -->
    <script src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
</head>

<body>

<nav class="navbar navbar-default">
  	<div class="container-fluid">
    	<div class="navbar-header">
      		<a class="navbar-brand" href="#">MusiQ</a>
    	</div>
    	<div class="navbar-header" style="float: right;">
      		<a id="room_code" class="navbar-brand" href="#">Get a room code!</a>
    	</div>
  	</div>
</nav>

<div id="container" class="container">
  
	<div class="text-center">
    	<h1>MusiQ</h1>
    	<p class="lead">All the music.</p>
  	</div>
  	<div id="queue">
  	</div>

 	
  	<div id="qr_room_code" style="display:none;">
  		<h1 class="center-text-audio">Rainbow Unicorn Ninja wants to party!</h1>
  		<img src="img/unicorn.gif"/>
  		<span id="code"></span>
  		<h1 class="center-text-audio">Do you?</h1>
  	</div>
</div><!-- /.container -->

<footer>
    <div class="navbar navbar-default navbar-fixed-bottom">
        <div class="container-fluid">
    		<div id="audio-player" class="row-audio clearfix center-block text-center">
				<audio id="playa" controls autoplay>
					<source id='playa-song' src="" type="audio/mp3" />
				</audio>
			</div> 		
  		</div>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bpopup.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
</body>

</html>
